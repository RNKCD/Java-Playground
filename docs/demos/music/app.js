const $ = id => document.getElementById(id);
let bridge, allRows=[];
const controls = ['artist','decade','limit'];
function log(message){$('console').textContent=message;}
function setStatus(message){$('status').textContent=message;}
async function loadDataset(){
  controls.forEach(id=>$(id).disabled=true);
  setStatus('Loading album collection into Java…');
  const response=await fetch($('dataset').value);
  if(!response.ok)throw new Error('Cannot load CSV: '+response.status);
  const csv=await response.text();
  bridge=await new window.AlbumWebBridge(csv);
  const rows=String(await bridge.rows());
  allRows=rows.trim().split('\n').filter(Boolean).map(line=>{
    const [rank,year,title,artist]=line.split('\t');
    return {rank:Number(rank),year:Number(year),title,artist};
  });
  controls.forEach(id=>$(id).disabled=false);
  $('datasetLabel').textContent=$('dataset').selectedOptions[0].textContent;
  setStatus('● Java engine ready — real Java code running');
  await render();
}
async function render(){
  if(!bridge)return;
  const artist=$('artist').value.trim();
  const decade=$('decade').value;
  const limit=Number($('limit').value);
  const matches=artist?await bridge.artistCount(artist):null;
  const decadeOutput=decade==='all'?null:String(await bridge.decade(Number(decade)));
  const topOutput=String(await bridge.top(limit));
  let rows=allRows.slice(0,limit);
  if(artist)rows=rows.filter(a=>a.artist.toLowerCase().includes(artist.toLowerCase()));
  if(decade!=='all')rows=rows.filter(a=>a.year>=Number(decade)&&a.year<Number(decade)+10);
  $('shown').textContent=rows.length;
  $('matches').textContent=matches===null?'—':matches;
  $('decadeCount').textContent=decadeOutput===null?'—':decadeOutput.split(' ')[0];
  const list=$('albums');list.replaceChildren();
  if(!rows.length){const p=document.createElement('p');p.textContent='No albums match these filters.';list.append(p);}
  for(const a of rows){const row=document.createElement('div');row.className='album';
    const rank=document.createElement('span');rank.className='rank';rank.textContent='#'+a.rank;
    const info=document.createElement('div');const title=document.createElement('strong');title.textContent=a.title;
    const subtitle=document.createElement('p');subtitle.textContent=a.artist;info.append(title,subtitle);
    const year=document.createElement('span');year.className='year';year.textContent=a.year;
    row.append(rank,info,year);list.append(row);
  }
  log('GreatestAlbums.topAlbums('+limit+'):\n'+topOutput+(artist?'\nnumberOfTopAlbums("'+artist+'"): '+matches:'')+(decadeOutput?'\ntopInDecade('+decade+'):\n'+decadeOutput:''));
}
try{
  await cheerpjInit();
  const library=await cheerpjRunLibrary('/app'+new URL('../../java/music-album-explorer.jar',location.href).pathname);
  window.AlbumWebBridge=await library.AlbumWebBridge;
  $('dataset').addEventListener('change',()=>loadDataset().catch(showError));
  $('artist').addEventListener('input',()=>render().catch(showError));
  $('decade').addEventListener('change',()=>render().catch(showError));
  $('limit').addEventListener('change',()=>render().catch(showError));
  await loadDataset();
}catch(error){showError(error);}
function showError(error){setStatus('Java initialization failed');log(String(error)+'\nCheck the browser console and make sure the JAR and CSV are served by GitHub Pages.');console.error(error);}

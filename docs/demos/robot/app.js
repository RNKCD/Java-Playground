// UI only: all state transitions execute inside RobotWorld Java bytecode.
const status = document.querySelector('#status');
const log = document.querySelector('#log');
const argument = document.querySelector('#argument');
const controls = [...document.querySelectorAll('button')];
let bridge;
const colors = {P:'#b5a2f5',Y:'#f5d76d',W:'#e9eef5',R:'#f18a8a',B:'#83c9f4',G:'#8ce0b0',O:'#ffc58a'};
function append(message){log.textContent += '\n' + message;log.scrollTop=log.scrollHeight;}
function render(state){
  const lines=String(state).split('\n');
  for(let i=0;i<2;i++){
    const target=document.querySelector('#pile'+(i+1));target.replaceChildren();
    const found=lines[i]?.match(/\[(.*?)\]/);const blocks=found&&found[1].trim()?found[1].split(',').map(x=>x.trim()):[];
    if(!blocks.length){const el=document.createElement('span');el.className='empty';el.textContent='Empty pile';target.append(el);}
    for(const block of blocks){const el=document.createElement('div');el.className='block';el.textContent=block;el.style.background=colors[block.slice(1)]||'#cad5e5';target.append(el);}
  }
}
async function run(action,value){
  if(!bridge)return;
  controls.forEach(x=>x.disabled=true);
  try{
    if(action==='reset'){await bridge.reset();append('> reset');render(await bridge.state());}
    else if(action==='show'){append('> show');render(await bridge.state());}
    else if(!/^[A-Za-z]$/.test(value)){append('Use a single letter argument.');}
    else{append('> '+action+' '+value);const result=await bridge.execute(action,value);append(String(result));render(await bridge.state());}
  }catch(e){append('Java runtime error: '+e);}
  finally{controls.forEach(x=>x.disabled=false);}
}
document.querySelectorAll('[data-command]').forEach(b=>b.addEventListener('click',()=>run(b.dataset.command,argument.value.trim())));
document.querySelector('#reset').addEventListener('click',()=>run('reset'));
document.querySelector('#command-form').addEventListener('submit',e=>{e.preventDefault();const raw=document.querySelector('#command').value.trim();const parts=raw.split(/\s+/);if(parts.length===1&&['show','reset'].includes(parts[0]))run(parts[0]);else if(parts.length===2&&['move','discard','grab-color'].includes(parts[0]))run(parts[0],parts[1]);else append('Commands: move d | discard b | grab-color Y | show | reset');document.querySelector('#command').value='';});
(async()=>{try{await cheerpjInit();const library=await cheerpjRunLibrary('/app/java/robot-demo.jar');const Bridge=await library.RobotDemoBridge;bridge=await new Bridge();render(await bridge.state());log.textContent='Java linked-stack engine ready.\nTry: move d, discard b, grab-color Y';status.textContent='● Java engine ready';}catch(e){status.textContent='Java failed to load';log.textContent='Loading error: '+e+'\nUse a web server (not file://). See README.';console.error(e);}})();
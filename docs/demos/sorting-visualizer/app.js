const $=s=>document.querySelector(s);let bridge=null,frames=[],idx=0;const status=$("#status");const names=["Start","Compare","Swap","Write / Shift","Write / Shift","Done"];
function render(){if(!frames.length)return;const f=frames[idx],max=Math.max(1,...f.a.map(v=>Math.abs(v)));$("#bars").replaceChildren();f.a.forEach((v,i)=>{const b=document.createElement("div");b.className="bar "+(f.code===5?"done":i===f.x||i===f.y?"active":"");b.style.height=(20+80*Math.abs(v)/max)+"%";b.textContent=v;$("#bars").append(b)});$("#step").textContent=`Step ${idx+1}/${frames.length} — ${names[f.code]||"Update"}`;$("#log").textContent=f.a.join(", ")}
$("#start").onclick=async()=>{if(!bridge)return;try{const count=await bridge.prepare($("#algo").value,$("#numbers").value);const len=await bridge.arrayLength();frames=[];for(let i=0;i<count;i++){const a=[];for(let j=0;j<len;j++)a.push(await bridge.valueAt(i,j));frames.push({a,x:await bridge.highlightA(i),y:await bridge.highlightB(i),code:await bridge.actionCode(i)})}idx=0;render()}catch(e){console.error(e);$("#log").textContent="Java error: "+(e?.message||"Check array input")}};
$("#next").onclick=()=>{idx=Math.min(frames.length-1,idx+1);render()};$("#prev").onclick=()=>{idx=Math.max(0,idx-1);render()};$("#random").onclick=()=>{$("#numbers").value=Array.from({length:8},()=>Math.floor(Math.random()*90)+10).join(",");frames=[];$("#step").textContent="Click Generate Java steps"};

(async () => {
  try {
    status.textContent = "Loading Java engine...";

    await cheerpjInit();

    // Works on localhost and GitHub Pages
 
const basePath = window.location.hostname.endsWith("github.io")
  ? "/java-playground"
  : "";

const jarPath = `/app${basePath}/java/sorting-visualizer.jar`;

console.log("Loading Java from:", jarPath);

const lib = await cheerpjRunLibrary(jarPath);


    bridge = await new (await lib.SortingBridge)();

    status.textContent = "● Java engine ready";

    await $("#start").onclick();

  } catch (e) {
    console.error("Java loading error:", e);
    status.textContent = "Java load failed — open F12 Console";
  }
})();

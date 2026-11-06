
const $ = (selector) => document.querySelector(selector);

let bridge = null;
let frames = [];
let idx = 0;

const status = $("#status");

const names = [
  "Start",
  "Compare",
  "Swap",
  "Write / Shift",
  "Write / Shift",
  "Done"
];

// Display the current sorting step
function render() {
  if (!frames.length) return;

  const frame = frames[idx];
  const max = Math.max(
    1,
    ...frame.a.map((value) => Math.abs(value))
  );

  $("#bars").replaceChildren();

  frame.a.forEach((value, i) => {
    const bar = document.createElement("div");

    bar.className =
      "bar " +
      (frame.code === 5
        ? "done"
        : i === frame.x || i === frame.y
          ? "active"
          : "");

    bar.style.height =
      (20 + (80 * Math.abs(value)) / max) + "%";

    bar.textContent = value;
    $("#bars").appendChild(bar);
  });

  $("#step").textContent =
    `Step ${idx + 1}/${frames.length} — ` +
    (names[frame.code] || "Update");

  $("#log").textContent = frame.a.join(", ");
}

// Generate sorting steps using Java
$("#start").onclick = async () => {
  if (!bridge) {
    $("#log").textContent = "Java engine is not ready.";
    return;
  }

  try {
    const count = await bridge.prepare(
      $("#algo").value,
      $("#numbers").value
    );

    const length = await bridge.arrayLength();

    frames = [];

    for (let i = 0; i < count; i++) {
      const array = [];

      for (let j = 0; j < length; j++) {
        array.push(await bridge.valueAt(i, j));
      }

      frames.push({
        a: array,
        x: await bridge.highlightA(i),
        y: await bridge.highlightB(i),
        code: await bridge.actionCode(i)
      });
    }

    idx = 0;
    render();

  } catch (error) {
    console.error("Sorting error:", error);

    $("#log").textContent =
      "Java error: " +
      (typeof error?.message === "string"
        ? error.message
        : "Check array input");
  }
};

// Next sorting step
$("#next").onclick = () => {
  if (!frames.length) return;

  idx = Math.min(frames.length - 1, idx + 1);
  render();
};

// Previous sorting step
$("#prev").onclick = () => {
  if (!frames.length) return;

  idx = Math.max(0, idx - 1);
  render();
};

// Generate random numbers
$("#random").onclick = () => {
  $("#numbers").value = Array.from(
    { length: 8 },
    () => Math.floor(Math.random() * 90) + 10
  ).join(",");

  frames = [];
  idx = 0;

  $("#bars").replaceChildren();
  $("#step").textContent = "Click Generate Java steps";
  $("#log").textContent = "";
};

// Initialize CheerpJ and load the Java algorithm
async function initializeJava() {
  try {
    status.textContent = "Loading Java engine...";

    await cheerpjInit();

    // Resolve the JAR relative to this demo page.
    // Supports localhost and GitHub Pages.
    const jarUrl = new URL(
      "../../java/sorting-visualizer.jar",
      new URL("./", window.location.href)
    );

    const jarPath =
  "/app/java-playground/java/sorting-visualizer.jar";

    console.log("Loading Java from:", jarPath);

    const library = await cheerpjRunLibrary(jarPath);

    bridge = await new (await library.SortingBridge)();

    status.textContent = "● Java engine ready";

    // Generate the initial sorting steps
    await $("#start").onclick();

  } catch (error) {
    console.error("Java loading error:", error);

    status.textContent =
      "Java load failed — open F12 Console";
  }
}

initializeJava();

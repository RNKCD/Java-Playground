# Java Playground — clean rebuild

A complete replacement repository. **Do not copy into the old folder**: extract this ZIP to a new location and open the included `java-playground` folder in Cursor.

## Start locally (Windows PowerShell)

```powershell
npx.cmd --yes http-server docs -p 8080 -c-1
```

Open http://127.0.0.1:8080/ . Keep the terminal running. The browser needs internet to load CheerpJ.

## Java integration fix

The earlier version used `String(await bridge.method())` on CheerpJ Java string proxies, causing `Cannot convert object to primitive value`. This rebuild uses **Java primitive getters** for the Sorting, Binary Search, Matrix and Robot demos. Java performs all algorithm work; JavaScript reads only numeric results and renders the UI.

## Projects

- Sorting Visualizer — six Java algorithms, step-by-step
- Binary Search Challenge — recursive search, step-by-step
- Matrix Lab — Strassen multiplication (2x2 / 4x4)
- Robot Block Simulator — Java linked stacks
- Music Album Explorer — experimental browser demo
- Inventory Manager — Java coursework source (browser demo not yet implemented)

## Build Java JARs again

Precompiled JARs are already in `docs/java/`. Rebuilding is optional. Requires Java JDK. On Windows PowerShell:

```powershell
mkdir build\sorting-visualizer -Force
javac --release 8 -d build\sorting-visualizer projects\sorting-visualizer\src\*.java
jar cf docs\java\sorting-visualizer.jar -C build\sorting-visualizer .
```

Other build scripts are in `scripts/` (bash).

## Publish as new clean repository contents

Copy the extracted `java-playground` folder contents into your local Git repo **after backing up**, or create a fresh Git clone and copy these files over. Run `git add .`, `git commit -m "Rebuild Java Playground"`, `git push origin main`. Configure GitHub Pages to serve `main` / `docs`.

## Troubleshooting

If stuck, open DevTools (F12) > Console and send the first red error. A local server only serves the site; CheerpJ runs the Java JARs in the browser. Browser execution has not been end-to-end verified here.

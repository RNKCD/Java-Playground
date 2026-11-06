# Java Playground 🎮

A growing collection of small Java coursework projects, with one-click browser demos powered by **CheerpJ**. Original Java algorithms are preserved in `projects/`; HTML/CSS/JS are the interface only.

## Projects

| Project | Java source | Browser demo |
|---|---|---|
| Robot Block Simulator | `projects/robot-block-simulator/src` | `docs/demos/robot/` (integration needs browser verification) |
| Music Album Explorer | `projects/music-album-explorer/src` | Coming soon |
| Mini Inventory Manager | `projects/inventory-manager/src` | Coming soon |

## Build (no Gradle)

Requires Java JDK 8 or newer, plus Bash (Git Bash on Windows):

```bash
bash scripts/build-robot.sh
```

The build outputs `docs/java/robot-demo.jar`. The repository includes a prebuilt copy.

## Preview locally

From the repository root:

```bash
python -m http.server 8000
```

Open `http://localhost:8000/docs/`. The CheerpJ runtime is loaded from its CDN, so an internet connection is required.

## GitHub Pages

Repository **Settings → Pages → Deploy from a branch → main → /docs**. The site will be at `https://<username>.github.io/<repository>/`.

**Status:** Java compilation and smoke tests pass. Browser integration still requires real-browser testing. CheerpJ usage is subject to its license; check eligibility before publishing.

## Adding another project

1. Create `projects/<project-name>/src/` and put the original Java files there.
2. Add a small Java bridge only if browser interaction needs it.
3. Build a JAR into `docs/java/` with a simple script.
4. Add a page under `docs/demos/<project-name>/` and a card to `docs/index.html`.

No Gradle, no Spring Boot, and no server-side Java hosting.

## New algorithm demos

- [Sorting Visualizer](docs/demos/sorting-visualizer/) — Bubble, Selection, Insertion, Exchange, Merge, Quick.
- [Binary Search Challenge](docs/demos/binary-search-challenge/) — recursive search and visual steps.
- [Strassen Matrix Lab](docs/demos/matrix-lab/) — 2×2 and 4×4 multiplication.

Build each with `bash scripts/build-<project>.sh`. Java source is preserved in `projects/`. The browser integration requires live testing with CheerpJ.

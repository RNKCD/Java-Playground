#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
mkdir -p build/sorting-visualizer docs/java
javac -d build/sorting-visualizer projects/sorting-visualizer/src/*.java
jar cf docs/java/sorting-visualizer.jar -C build/sorting-visualizer .

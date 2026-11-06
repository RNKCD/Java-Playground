#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
mkdir -p build/matrix-lab docs/java
javac -d build/matrix-lab projects/matrix-lab/src/*.java
jar cf docs/java/matrix-lab.jar -C build/matrix-lab .

#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
mkdir -p build/robot docs/java
javac --release 8 -d build/robot projects/robot-block-simulator/src/{StackInt,LinkedStack,RobotWorld,RobotDemoBridge}.java
jar cf docs/java/robot-demo.jar -C build/robot .
echo "Built docs/java/robot-demo.jar"

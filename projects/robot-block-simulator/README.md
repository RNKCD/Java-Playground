# Robot Block Simulator

A small interactive extension of the original PA3 linked-stack assignment. Two piles of blocks are stored using the original `LinkedStack` implementation. Each block has a two-character code: its name followed by its color.

## Run

From the `PA3` directory:

```bash
javac StackInt.java LinkedStack.java RobotWorld.java RobotBlockSimulator.java P3Test.java
java RobotBlockSimulator
```

Press Enter to use the default starting piles. Example commands:

```text
move d
discard b
grab-color Y
show
exit
```

The piles are displayed after each valid action, top block first. The original file-based assignment is still available with `java P3Test` (run it from `PA3` so the action files are found).

**Notes:** `move` moves the topmost matching block from pile 1 to pile 2, or from pile 2 to pile 1 if none is found in pile 1. `discard` removes all blocks with the given name from both piles. `grab-color` removes the topmost matching-color block from each pile.

# Mini Inventory Manager (PA4)

A small console inventory project built from a Data Structures assignment. Uses Java's `HashMap` to track products, quantities, costs, selling prices, and profit.

## Run

From the `PA4` directory with a JDK installed:

```bash
javac *.java
java MiniInventoryManager
```

Choose a menu option to add products, restock, sell, remove, list stock, or view a financial report. The inventory display flags **LOW STOCK** when there are 5 or fewer units. You can also load the included `trans0.txt` transaction file using menu option 7.

To run the original file-driven test, use `java P4Test` from this folder.

## Small fixes

- Sales now decrease available stock (the original assignment omitted this update).
- Negative/zero quantities and negative/non-finite prices are rejected.
- The financial report tracks the **cost of unsold inventory** and **net realized profit after write-offs**. The sample transaction report remains `$200.00` in remaining inventory cost, with `$2.96` net profit after write-offs.

Data is stored in memory and resets when the application closes; no database or external dependencies are needed.

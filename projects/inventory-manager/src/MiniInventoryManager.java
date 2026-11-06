import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/** Small interactive front end for the original HashMap inventory assignment. */
public class MiniInventoryManager {
    public static void main(String[] args) {
        ProductControlSystem store = new ProductControlSystem();
        Scanner input = new Scanner(System.in);
        System.out.println("=== Mini Inventory Manager ===");
        while (true) {
            System.out.println("\n1. Add product  2. Restock  3. Sell  4. Remove product");
            System.out.println("5. View inventory  6. Financial report  7. Load transactions  0. Exit");
            System.out.print("Choose: ");
            String option = input.nextLine().trim();
            if (option.equals("0")) break;
            try {
                switch (option) {
                    case "1": {
                        String id = ask(input, "Product ID: ");
                        double cost = Double.parseDouble(ask(input, "Unit cost: "));
                        double price = Double.parseDouble(ask(input, "Selling price: "));
                        System.out.println(store._new(id, cost, price));
                        break;
                    }
                    case "2": System.out.println(store.buy(ask(input, "Product ID: "), Integer.parseInt(ask(input, "Quantity: ")))); break;
                    case "3": System.out.println(store.sell(ask(input, "Product ID: "), Integer.parseInt(ask(input, "Quantity: ")))); break;
                    case "4": System.out.println(store.delete(ask(input, "Product ID: "))); break;
                    case "5": showInventory(store); break;
                    case "6": System.out.println(store.report()); break;
                    case "7": System.out.println(store.processTransactions(ask(input, "File path (e.g., trans0.txt): "))); break;
                    default: System.out.println("Unknown option. Choose 0–7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        System.out.println("Goodbye!");
    }

    private static String ask(Scanner input, String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    private static void showInventory(ProductControlSystem store) {
        if (store.itemMap.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        List<String> ids = new ArrayList<>(store.itemMap.keySet());
        Collections.sort(ids);
        System.out.printf("%-18s %8s %12s %12s %s%n", "PRODUCT", "STOCK", "COST", "PRICE", "STATUS");
        for (String id : ids) {
            itemDetails item = store.itemMap.get(id);
            int stock = item.rem_quantity();
            String status = stock <= 5 ? "LOW STOCK" : "OK";
            System.out.printf("%-18s %8d $%11.2f $%11.2f %s%n", id, stock, item.get_item_cost(), item.get_item_sell(), status);
        }
        System.out.println("Low-stock threshold: 5 units.");
    }
}

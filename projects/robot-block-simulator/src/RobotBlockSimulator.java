import java.util.Scanner;

/** A small interactive console interface for the original RobotWorld assignment. */
public class RobotBlockSimulator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("=== Robot Block Simulator ===");
        System.out.println("Each block uses two characters: name + color (e.g., dP).");
        System.out.print("Initial piles [dPcYbW,fYeP]: ");
        String initial = input.nextLine().trim();
        if (initial.isEmpty()) initial = "dPcYbW,fYeP";
        if (!initial.matches("([A-Za-z]{2})+,([A-Za-z]{2})+")) {
            System.out.println("Invalid piles. Example: dPcYbW,fYeP");
            return;
        }
        RobotWorld world = new RobotWorld(initial);
        System.out.println(world.displayPiles());
        System.out.println("Commands: move <name>, discard <name>, grab-color <color>, show, help, exit");
        while (true) {
            System.out.print("> ");
            if (!input.hasNextLine()) break;
            String line = input.nextLine().trim();
            if (line.equalsIgnoreCase("exit")) break;
            if (line.equalsIgnoreCase("show")) {
                System.out.println(world.displayPiles());
                continue;
            }
            if (line.equalsIgnoreCase("help")) {
                System.out.println("move d | discard b | grab-color Y | show | exit");
                continue;
            }
            String[] parts = line.split("\\s+");
            if (parts.length != 2 || !parts[1].matches("[A-Za-z]")) {
                System.out.println("Use: move d, discard b, or grab-color Y");
                continue;
            }
            switch (parts[0].toLowerCase()) {
                case "move": world.move(parts[1]); break;
                case "discard": world.discard(parts[1]); break;
                case "grab-color": world.grab_color(parts[1]); break;
                default:
                    System.out.println("Unknown command. Type help.");
                    continue;
            }
            System.out.println(world.displayPiles());
        }
        System.out.println("Goodbye!");
    }
}

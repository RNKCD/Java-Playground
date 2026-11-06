/** Thin browser adapter. All block operations use the original RobotWorld class. */
public class RobotDemoBridge {
    private RobotWorld world;
    public RobotDemoBridge() { reset(); }
    public void reset() { world = new RobotWorld("dPcYbW,fYeP"); }
    public String state() { return world.displayPiles(); }
    public String execute(String command, String argument) {
        if (command == null || argument == null || !argument.matches("[A-Za-z]"))
            return "Invalid argument. Use a single letter.";
        switch (command) {
            case "move": world.move(argument); break;
            case "discard": world.discard(argument); break;
            case "grab-color": world.grab_color(argument); break;
            default: return "Unknown command.";
        }
        return world.displayPiles();
    }
}

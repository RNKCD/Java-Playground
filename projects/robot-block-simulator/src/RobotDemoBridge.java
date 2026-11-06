/** Thin browser adapter. All block operations use the original RobotWorld class. */
public class RobotDemoBridge {
 public int stateLength(){return state().length();}
 public int stateChar(int index){return state().charAt(index);}
 public void perform(String command,String argument){execute(command,argument);}
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

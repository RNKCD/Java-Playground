/** Small smoke test for the actual Java engine, no external libraries required. */
public class RobotDemoSmokeTest {
    public static void main(String[] args) {
        RobotDemoBridge bridge = new RobotDemoBridge();
        String initial = bridge.state();
        if (!initial.contains("PILE 1") || !initial.contains("PILE 2"))
            throw new AssertionError("Initial piles not displayed");
        bridge.execute("move", "d");
        if (initial.equals(bridge.state()))
            throw new AssertionError("Move did not update Java stack state");
        bridge.reset();
        if (!initial.equals(bridge.state()))
            throw new AssertionError("Reset did not restore the starting state");
        System.out.println("PASS: Java state, move, and reset");
    }
}

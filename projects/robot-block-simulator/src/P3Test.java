import java.io.FileNotFoundException;

/**
   Driver program to test the basic functionality of the 
   RobotWorld class.   
*/
public class P3Test
{
   public static void main(String[] args) throws FileNotFoundException
	{
		RobotWorld r2d2 = new RobotWorld("dPcYbW,fYeP");
		System.out.println(r2d2.commands("actions0.txt"));
      	RobotWorld cpU2 = new RobotWorld("aYdPcYbW,fYeP");
		System.out.println(cpU2.commands("actions1.txt"));
	}
}
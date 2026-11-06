	
	/**
    * This program can be used to test the basic functionality of the GreatestAlbums class methods.
  	 *
	 * @author Dr.C.
    */
public class P2Test
{
   public static void main(String[] args)
   {    
      GreatestAlbums goat = new GreatestAlbums("albumlist100.csv");
   		
      System.out.println("The top 5 albums are: \n" + goat.topAlbums(5));    // Displays top 5 albums
      
      String artist = "The Beatles";
      int count = goat.numberOfTopAlbums(artist);                                    // Returns 4
      System.out.println("\n " + count + " album(s) belong(s) to " + artist + "\n");
      artist = "The Rolling Stones";
      count = goat.numberOfTopAlbums(artist);                                        // Returns 1
      System.out.println("\n " + count + " album(s) belong(s) to " + artist + "\n");
      
      int decade = 1960;
      System.out.println("\nTop albums released in the " + decade + "'s\n" + goat.topInDecade(decade));
      // Returns a string containing "7 albums:" on the first line, followed by info on the 7 albums
  }
}
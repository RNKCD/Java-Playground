/**
    Represents an instance of a music album.
    Accessor methods are provided to get any of the album parameters (title, type, artist).
*/

public class Album
{
   private String title;                     // Album title
   private String artist;                    // Album artist name
   private int year;                         // Album production year   

   /**
      Constructor for Album objects.
      @param theYear the production year
      @param theTitle the album title
      @param theArtist the artist name  
   */
   public Album(String theYear, String theTitle, String theArtist)
   {
      title    = theTitle;
      artist   = theArtist;
      try
	   {
		    year = Integer.parseInt(theYear);
	   }
	   catch (Exception notAnInt)
	   {
		    year = 0;       // if year not an integer
	   }
   }

   /**
       Accessor to return the title of the album 
       @return  the title name for the album
   */
   public String getName()
   {
      return title;
   }

   /**
      Accessor to return the artist for the album
      @return  the artist for the album
   */
   public String getArtist()
   {
      return artist;
   }

   /**
      Accessor to return the production year for the album
      @return  the year the album was produced
   */
   public int getYear()
   {
      return year;
   }
}
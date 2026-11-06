/**
   Date: 10/07/2024
   Course: CSCI2073-44355- Data Structures - Fall 2024
   Description: This program reads album data from file and provide method to retrive
                  inforamtion about the albums.
   On my honor, I have neither given nor received unauthorized help while completing
   this assignment.
   */

import java.io.*;
import java.util.*;

public class GreatestAlbums {

   private ArrayList<Album> album_list;
   
   /**
    * Constructor for GreatestAlbums class
    * @param filename name of the file to be opened
    */
   public GreatestAlbums(String filename)
   {
      album_list = new ArrayList<>();
      
      //opening a file
       try{  
         File f1 = new File (filename);
         Scanner reader = new Scanner(f1);
         
         //reading the each line of the file
         while (reader.hasNextLine())
         {
            String line_info = reader.nextLine();
            
            Scanner album_info = new Scanner(line_info);
            
            //using delimiter to separate the details
            album_info.useDelimiter(",");
            
            //getting the individual data
            while(album_info.hasNext())
            {  
               String number = album_info.next();
               String date = album_info.next();
               String title = album_info.next();
               String artist = album_info.next();
               
               //adding classes in the list
               album_list.add(new Album(date,title, artist));
            }
            album_info.close();

         } 
         reader.close();
      }
      catch (FileNotFoundException e)
      {
         System.out.println( "File Not Found");
      }      
   }
   
   /**
    * Return the top n albums
    * @param n number of top albums to retrive
    * @return string containing the rank, album name and artist name of top m albums
    */
   public String topAlbums(int n)
   {  
      String topAlbum = "";
      int rank = 0;
      
      //adjusting if n exceeds size of list
      if (album_list.size() < n)
      {
         n = album_list.size();
      }

      for(int i = 0; i< n; i++)
      {
         rank +=1;
         topAlbum += rank +". " + album_list.get(i).getName()+ " - " + album_list.get(i).getArtist() + "\n";
         
      }
      return topAlbum;
   }
     
   
   /**
    * Return the number of album by an artist in the list
    * @param artist name of the artist
    * @return number of albums of the artist in the list
    */
   public int numberOfTopAlbums(String artist)
   {
      int t_num=0;
      
      for(int i= 0; i< album_list.size(); i++)
      {
         if(artist.equals(album_list.get(i).getArtist()))
         {
            t_num++;
         }
      }
      
      return t_num;
   }
   
   /** Find albums by artist (case-insensitive, partial matches allowed). */
   public String searchByArtist(String artist)
   {
      StringBuilder results = new StringBuilder();
      for (Album album : album_list)
      {
         if (album.getArtist().toLowerCase().contains(artist.toLowerCase()))
         {
            results.append(album.getName()).append(" (").append(album.getYear()).append(") - ")
                   .append(album.getArtist()).append("\n");
         }
      }
      return results.length() == 0 ? "No matching albums found.\n" : results.toString();
   }

   /**
    * Returns all the album release within a decade
    * @param decade decade to search for
    * @return  string containing the number and names of albums releases within the decade
   */
   public String topInDecade(int decade)
   {
      String topAlbum = "";
      int count = 0;
      for(int i = 0; i< album_list.size(); i++)
      {
         if(  (album_list.get(i).getYear()>=decade) && (album_list.get(i).getYear() < (decade + 10)))
         {
            count++;
            topAlbum += album_list.get(i).getName() + "\n";
         }         
      }
      return (count + " albums:\n"+topAlbum);
   }
   

}
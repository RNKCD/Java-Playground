import java.util.Scanner;

/** Small interactive interface for the existing GreatestAlbums assignment. */
public class MusicAlbumExplorer {
   public static void main(String[] args) {
      String file = args.length > 0 ? args[0] : "albumlist100.csv";
      GreatestAlbums albums = new GreatestAlbums(file);
      Scanner input = new Scanner(System.in);
      while (true) {
         System.out.println("\n=== Music Album Explorer ===");
         System.out.println("1. Show top albums");
         System.out.println("2. Search albums by artist");
         System.out.println("3. Show albums from a decade");
         System.out.println("4. Exit");
         System.out.print("Choose an option: ");
         String choice = input.nextLine().trim();
         switch (choice) {
            case "1":
               System.out.print("How many albums? ");
               try {
                  int n = Integer.parseInt(input.nextLine().trim());
                  if (n < 1) System.out.println("Enter a positive number.");
                  else System.out.print(albums.topAlbums(n));
               } catch (NumberFormatException e) {
                  System.out.println("Please enter a valid number.");
               }
               break;
            case "2":
               System.out.print("Artist name (or part of it): ");
               String artist = input.nextLine().trim();
               if (artist.isEmpty()) System.out.println("Enter an artist name.");
               else System.out.print(albums.searchByArtist(artist));
               break;
            case "3":
               System.out.print("Decade start (e.g. 1970): ");
               try {
                  int decade = Integer.parseInt(input.nextLine().trim());
                  System.out.print(albums.topInDecade(decade));
               } catch (NumberFormatException e) {
                  System.out.println("Please enter a valid year.");
               }
               break;
            case "4":
               System.out.println("Goodbye!");
               return;
            default:
               System.out.println("Choose 1, 2, 3, or 4.");
         }
      }
   }
}

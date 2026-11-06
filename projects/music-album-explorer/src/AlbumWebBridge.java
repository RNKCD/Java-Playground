/** Minimal browser adapter. All album searches are delegated to GreatestAlbums. */
public class AlbumWebBridge {
    private final GreatestAlbums albums;
    public AlbumWebBridge(String csv) { albums = new GreatestAlbums(csv, true); }
    public String rows() { return albums.albumRows(); }
    public String top(int n) { return albums.topAlbums(n); }
    public int artistCount(String artist) { return albums.numberOfTopAlbums(artist); }
    public String decade(int decade) { return albums.topInDecade(decade); }
}

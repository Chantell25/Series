package seriesmanager;

/**
 * Represents a TV series with its properties
 * Contains information about title, genre, age restriction,
 * number of episodes, and rating
 */
public class Series {
    private String title;
    private String genre;
    private int ageRestriction;
    private int episodes;
    private double rating;
    
    public Series(String title, String genre, int ageRestriction, int episodes, double rating) {
        this.title = title;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.episodes = episodes;
        this.rating = rating;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public int getAgeRestriction() {
        return ageRestriction;
    }
    
    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }
    
    public int getEpisodes() {
        return episodes;
    }
    
    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }
    
    public double getRating() {
        return rating;
    }
    
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    public String getInfo() {
        return "Title: " + title + "\n" +
               "Genre: " + genre + "\n" +
               "Age Restriction: " + ageRestriction + "\n" +
               "Episodes: " + episodes + "\n" +
               "Rating: " + rating;
    }
    
    @Override
    public String toString() {
        return title;
    }
}
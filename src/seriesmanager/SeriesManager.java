package seriesmanager;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Main application class for managing TV series
 * Provides functionality to add, search, update, delete, and generate reports
 */
public class SeriesManager {
    private ArrayList<Series> seriesList;
    
    public SeriesManager() {
        seriesList = new ArrayList<>();
        initializeSampleData();
    }
    
    // Initialize with some sample series data
    private void initializeSampleData() {
        seriesList.add(new Series("Breaking Bad", "Drama", 16, 62, 9.5));
        seriesList.add(new Series("Stranger Things", "Sci-Fi", 13, 34, 8.7));
        seriesList.add(new Series("The Crown", "Historical", 12, 40, 8.6));
    }
    
    // Add a new series to the collection
    public boolean addSeries(String title, String genre, int ageRestriction, int episodes, double rating) {
        // Check if series already exists
        if (findSeriesByTitle(title) != null) {
            return false;
        }
        
        // Create and add the new series
        Series newSeries = new Series(title, genre, ageRestriction, episodes, rating);
        seriesList.add(newSeries);
        return true;
    }
    
    // Search for a series by title
    public Series searchSeries(String title) {
        return findSeriesByTitle(title);
    }
    
    // Update an existing series
    public boolean updateSeries(String title, String genre, int ageRestriction, int episodes, double rating) {
        Series seriesToUpdate = findSeriesByTitle(title);
        
        if (seriesToUpdate == null) {
            return false;
        }
        
        seriesToUpdate.setGenre(genre);
        seriesToUpdate.setAgeRestriction(ageRestriction);
        seriesToUpdate.setEpisodes(episodes);
        seriesToUpdate.setRating(rating);
        return true;
    }
    
    // Delete a series from the collection
    public boolean deleteSeries(String title) {
        Series seriesToDelete = findSeriesByTitle(title);
        
        if (seriesToDelete != null) {
            seriesList.remove(seriesToDelete);
            return true;
        }
        return false;
    }
    
    // Generate a report of all series
    public String generateReport() {
        if (seriesList.isEmpty()) {
            return "No series available to display.";
        }
        
        StringBuilder report = new StringBuilder();
        report.append("Total series: ").append(seriesList.size()).append("\n");
        report.append("-----------------------------\n");
        
        for (Series series : seriesList) {
            report.append(series.getInfo()).append("\n");
            report.append("-----------------------------\n");
        }
        
        return report.toString();
    }
    
    // Helper method to find a series by title
    private Series findSeriesByTitle(String title) {
        for (Series series : seriesList) {
            if (series.getTitle().equalsIgnoreCase(title)) {
                return series;
            }
        }
        return null;
    }
    
    // Get the series list for testing purposes
    public ArrayList<Series> getSeriesList() {
        return seriesList;
    }
    
    // Display the main menu and handle user input
    public void displayMainMenu() {
        String[] options = {
            "Add a new series",
            "Search for a series",
            "Update a series",
            "Delete a series",
            "Generate series report",
            "Exit"
        };
        
        while (true) {
            int choice = JOptionPane.showOptionDialog(
                null,
                "=== SERIES MANAGEMENT SYSTEM ===",
                "Series Manager",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
            );
            
            switch (choice) {
                case 0:
                    addSeriesDialog();
                    break;
                case 1:
                    searchSeriesDialog();
                    break;
                case 2:
                    updateSeriesDialog();
                    break;
                case 3:
                    deleteSeriesDialog();
                    break;
                case 4:
                    generateReportDialog();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Exiting application. Goodbye!");
                    return;
                default:
                    // User closed the dialog
                    return;
            }
        }
    }
    
    // Dialog for adding a new series
    private void addSeriesDialog() {
        String title = JOptionPane.showInputDialog("Enter series title:");
        if (title == null || title.trim().isEmpty()) return;
        
        String genre = JOptionPane.showInputDialog("Enter genre:");
        if (genre == null || genre.trim().isEmpty()) return;
        
        String ageInput = JOptionPane.showInputDialog("Enter age restriction (0-21):");
        if (ageInput == null) return;
        int ageRestriction = InputValidator.validateAgeRestriction(ageInput);
        if (ageRestriction == -1) return;
        
        String episodesInput = JOptionPane.showInputDialog("Enter number of episodes:");
        if (episodesInput == null) return;
        int episodes = InputValidator.validatePositiveInt(episodesInput, "episodes");
        if (episodes == -1) return;
        
        String ratingInput = JOptionPane.showInputDialog("Enter rating (0-10):");
        if (ratingInput == null) return;
        double rating = InputValidator.validateRating(ratingInput);
        if (rating == -1) return;
        
        if (addSeries(title, genre, ageRestriction, episodes, rating)) {
            JOptionPane.showMessageDialog(null, "Series added successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "A series with this title already exists.");
        }
    }
    
    // Dialog for searching a series
    private void searchSeriesDialog() {
        String title = JOptionPane.showInputDialog("Enter series title to search:");
        if (title == null || title.trim().isEmpty()) return;
        
        Series foundSeries = searchSeries(title);
        
        if (foundSeries != null) {
            JOptionPane.showMessageDialog(null, "Series found:\n" + foundSeries.getInfo());
        } else {
            JOptionPane.showMessageDialog(null, "Series not found.");
        }
    }
    
    // Dialog for updating a series
    private void updateSeriesDialog() {
        String title = JOptionPane.showInputDialog("Enter series title to update:");
        if (title == null || title.trim().isEmpty()) return;
        
        Series seriesToUpdate = searchSeries(title);
        
        if (seriesToUpdate == null) {
            JOptionPane.showMessageDialog(null, "Series not found.");
            return;
        }
        
        String genre = JOptionPane.showInputDialog("Enter new genre (" + seriesToUpdate.getGenre() + "):", seriesToUpdate.getGenre());
        if (genre == null) return;
        
        String ageInput = JOptionPane.showInputDialog("Enter new age restriction (" + seriesToUpdate.getAgeRestriction() + "):", 
                                                     String.valueOf(seriesToUpdate.getAgeRestriction()));
        if (ageInput == null) return;
        int ageRestriction = InputValidator.validateAgeRestriction(ageInput);
        if (ageRestriction == -1) return;
        
        String episodesInput = JOptionPane.showInputDialog("Enter new number of episodes (" + seriesToUpdate.getEpisodes() + "):", 
                                                          String.valueOf(seriesToUpdate.getEpisodes()));
        if (episodesInput == null) return;
        int episodes = InputValidator.validatePositiveInt(episodesInput, "episodes");
        if (episodes == -1) return;
        
        String ratingInput = JOptionPane.showInputDialog("Enter new rating (" + seriesToUpdate.getRating() + "):", 
                                                        String.valueOf(seriesToUpdate.getRating()));
        if (ratingInput == null) return;
        double rating = InputValidator.validateRating(ratingInput);
        if (rating == -1) return;
        
        if (updateSeries(title, genre, ageRestriction, episodes, rating)) {
            JOptionPane.showMessageDialog(null, "Series updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Error updating series.");
        }
    }
    
    // Dialog for deleting a series
    private void deleteSeriesDialog() {
        String title = JOptionPane.showInputDialog("Enter series title to delete:");
        if (title == null || title.trim().isEmpty()) return;
        
        int confirm = JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to delete '" + title + "'?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (deleteSeries(title)) {
                JOptionPane.showMessageDialog(null, "Series deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Series not found.");
            }
        }
    }
    
    // Dialog for generating a report
    private void generateReportDialog() {
        String report = generateReport();
        JOptionPane.showMessageDialog(null, report, "Series Report", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Main method to start the application
    public static void main(String[] args) {
        SeriesManager manager = new SeriesManager();
        manager.displayMainMenu();
    }
}
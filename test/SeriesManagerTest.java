  
package seriesmanager;

/**
 * Unit tests for the SeriesManager application
 * Tests all major functionality to ensure proper operation
 */
public class SeriesManagerTest {
    
    public static void main(String[] args) {
        System.out.println("=== SERIES MANAGER UNIT TESTS ===");
        System.out.println("Starting tests...\n");
        
        int passed = 0;
        int total = 0;
        
        // Run all tests
        total++; if (testAddSeries()) passed++;
        total++; if (testSearchSeries()) passed++;
        total++; if (testUpdateSeries()) passed++;
        total++; if (testDeleteSeries()) passed++;
        total++; if (testGenerateReport()) passed++;
        total++; if (testInputValidator()) passed++;
        
        // Print summary
        System.out.println("\n=== TEST SUMMARY ===");
        System.out.println("Passed: " + passed + "/" + total);
        
        if (passed == total) {
            System.out.println("✓ ALL TESTS PASSED!");
        } else {
            System.out.println("✗ SOME TESTS FAILED!");
        }
    }
    
    private static boolean testAddSeries() {
        System.out.println("Test 1: Adding Series");
        SeriesManager manager = new SeriesManager();
        
        // Test adding a new series
        boolean result1 = manager.addSeries("Test Series", "Test Genre", 12, 10, 8.5);
        if (!result1) {
            System.out.println("✗ FAILED: Could not add new series");
            return false;
        }
        
        // Test adding a series with the same title
        boolean result2 = manager.addSeries("Test Series", "Different Genre", 15, 20, 9.0);
        if (result2) {
            System.out.println("✗ FAILED: Should not allow duplicate series titles");
            return false;
        }
        
        System.out.println("✓ PASSED: Add series functionality works correctly");
        return true;
    }
    
    private static boolean testSearchSeries() {
        System.out.println("Test 2: Searching Series");
        SeriesManager manager = new SeriesManager();
        
        // Test searching for an existing series
        Series found = manager.searchSeries("Breaking Bad");
        if (found == null) {
            System.out.println("✗ FAILED: Could not find existing series");
            return false;
        }
        
        // Test searching for a non-existing series
        Series notFound = manager.searchSeries("Non Existing Series");
        if (notFound != null) {
            System.out.println("✗ FAILED: Found a series that shouldn't exist");
            return false;
        }
        
        System.out.println("✓ PASSED: Search series functionality works correctly");
        return true;
    }
    
    private static boolean testUpdateSeries() {
        System.out.println("Test 3: Updating Series");
        SeriesManager manager = new SeriesManager();
        
        // Test updating an existing series
        boolean result = manager.updateSeries("Breaking Bad", "Crime Drama", 18, 62, 9.6);
        if (!result) {
            System.out.println("✗ FAILED: Could not update existing series");
            return false;
        }
        
        // Verify the update worked
        Series updated = manager.searchSeries("Breaking Bad");
        if (updated == null || !updated.getGenre().equals("Crime Drama")) {
            System.out.println("✗ FAILED: Series was not properly updated");
            return false;
        }
        
        // Test updating a non-existing series
        boolean invalidResult = manager.updateSeries("Non Existing Series", "Genre", 12, 10, 8.0);
        if (invalidResult) {
            System.out.println("✗ FAILED: Should not be able to update non-existing series");
            return false;
        }
        
        System.out.println("✓ PASSED: Update series functionality works correctly");
        return true;
    }
    
    private static boolean testDeleteSeries() {
        System.out.println("Test 4: Deleting Series");
        SeriesManager manager = new SeriesManager();
        
        // Test deleting an existing series
        boolean result = manager.deleteSeries("Breaking Bad");
        if (!result) {
            System.out.println("✗ FAILED: Could not delete existing series");
            return false;
        }
        
        // Verify the series was deleted
        Series deleted = manager.searchSeries("Breaking Bad");
        if (deleted != null) {
            System.out.println("✗ FAILED: Series was not properly deleted");
            return false;
        }
        
        // Test deleting a non-existing series
        boolean invalidResult = manager.deleteSeries("Non Existing Series");
        if (invalidResult) {
            System.out.println("✗ FAILED: Should not be able to delete non-existing series");
            return false;
        }
        
        System.out.println("✓ PASSED: Delete series functionality works correctly");
        return true;
    }
    
    private static boolean testGenerateReport() {
        System.out.println("Test 5: Generating Report");
        SeriesManager manager = new SeriesManager();
        
        String report = manager.generateReport();
        if (report == null || report.isEmpty()) {
            System.out.println("✗ FAILED: Report is null or empty");
            return false;
        }
        
        if (!report.contains("Total series:")) {
            System.out.println("✗ FAILED: Report doesn't contain expected content");
            return false;
        }
        
        System.out.println("✓ PASSED: Report generation works correctly");
        return true;
    }
    
    private static boolean testInputValidator() {
        System.out.println("Test 6: Input Validation");
        
        // Test valid age restriction
        int validAge = InputValidator.validateAgeRestriction("16");
        if (validAge != 16) {
            System.out.println("✗ FAILED: Valid age restriction not accepted");
            return false;
        }
        
        // Test invalid age restriction
        int invalidAge = InputValidator.validateAgeRestriction("25");
        if (invalidAge != -1) {
            System.out.println("✗ FAILED: Invalid age restriction not rejected");
            return false;
        }
        
        // Test non-numeric age
        int nonNumericAge = InputValidator.validateAgeRestriction("abc");
        if (nonNumericAge != -1) {
            System.out.println("✗ FAILED: Non-numeric age not rejected");
            return false;
        }
        
        // Test valid rating
        double validRating = InputValidator.validateRating("8.5");
        if (validRating != 8.5) {
            System.out.println("✗ FAILED: Valid rating not accepted");
            return false;
        }
        
        // Test invalid rating
        double invalidRating = InputValidator.validateRating("11");
        if (invalidRating != -1) {
            System.out.println("✗ FAILED: Invalid rating not rejected");
            return false;
        }
        
        System.out.println("✓ PASSED: Input validation works correctly");
        return true;
    }
}
 
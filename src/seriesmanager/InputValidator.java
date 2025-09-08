package seriesmanager;

import javax.swing.JOptionPane;

/**
 * Utility class for validating user input
 * Provides methods to validate different types of input with proper error handling
 */
public class InputValidator {
    
    // Validate age restriction input (0-21)
    public static int validateAgeRestriction(String input) {
        try {
            int age = Integer.parseInt(input);
            if (age >= 0 && age <= 21) {
                return age;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid age restriction. Must be between 0 and 21.");
                return -1;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number for age restriction.");
            return -1;
        }
    }
    
    // Validate rating input (0-10)
    public static double validateRating(String input) {
        try {
            double rating = Double.parseDouble(input);
            if (rating >= 0 && rating <= 10) {
                return rating;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid rating. Must be between 0 and 10.");
                return -1;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number for rating.");
            return -1;
        }
    }
    
    // Validate positive integer input
    public static int validatePositiveInt(String input, String fieldName) {
        try {
            int value = Integer.parseInt(input);
            if (value >= 0) {
                return value;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid " + fieldName + ". Must be a positive number.");
                return -1;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number for " + fieldName + ".");
            return -1;
        }
    }
}
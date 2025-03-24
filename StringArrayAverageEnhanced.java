import java.util.Scanner; // Import Scanner for user input

// Main class to calculate the average of numbers stored as Strings
public class StringArrayAverageEnhanced {

    // Method to calculate the average of an array of Strings
    public static double average(String[] values) throws NullPointerException, NumberFormatException {
        if (values == null) { // Check if the input array is null
            throw new NullPointerException("Array is null"); // Throw an exception if array is null
        }

        double sum = 0; // Variable to store the sum of numbers
        int count = 0; // Counter for the number of valid numbers

        for (String value : values) { // Loop through each element in the array
            if (value == null || value.trim().isEmpty()) { // Check if any element is null or empty
                throw new NullPointerException("Array contains a null or empty element");
            }
            try {
                sum += Double.parseDouble(value.trim()); // Convert string to double and add to sum
                count++; // Increment count
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format detected: '" + value + "'. Skipping this value.");
            }
        }

        if (count == 0) {
            throw new ArithmeticException("No valid numbers provided to calculate average.");
        }
        return sum / count; // Return the average
    }

    // Main method to take input from the user and calculate the average
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create Scanner object for user input

        try {
            System.out.print("Enter the number of elements: ");
            int n = Integer.parseInt(scanner.nextLine().trim()); // Read the number of elements

            if (n <= 0) {
                throw new NumberFormatException("Number of elements must be positive");
            }

            String[] numbers = new String[n]; // Create an array of Strings

            System.out.println("Enter " + n + " numbers (non-numeric entries will be skipped):");
            for (int i = 0; i < n; i++) {
                numbers[i] = scanner.nextLine(); // Read user input
            }

            double result = average(numbers); // Call the average method
            System.out.println("Average: " + result); // Print the result

        } catch (NullPointerException | NumberFormatException | ArithmeticException e) {
            System.out.println("Exception occurred: " + e.getMessage()); // Print exception message
        } finally {
            scanner.close(); // Close the scanner to prevent resource leaks
        }
    }
}




import java.io.*;                   // Importing classes for file I/O operations
import java.util.Scanner;            // Importing Scanner for user input

public class BasicFileIO {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   // Create a Scanner object for reading input
        String fileName;                              // Variable to store the file name
        int choice;                                   // Variable to store the user's menu choice

        // Main loop to continuously display the menu and perform operations
        while (true) {
            // Display the menu options
            System.out.println("\n--- File I/O Operations Menu ---");
            System.out.println("1. Create File");
            System.out.println("2. Read File");
            System.out.println("3. Write to File");
            System.out.println("4. Append to File");
            System.out.println("5. Delete File");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            // Read the menu choice as a string, then parse it to an integer
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            // Process the choice
            switch (choice) {
                case 1: // Create File Operation
                    System.out.print("Enter the file name to create: ");
                    fileName = scanner.nextLine();
                    createFile(fileName);
                    break;
                case 2: // Read File Operation
                    System.out.print("Enter the file name to read: ");
                    fileName = scanner.nextLine();
                    readFile(fileName);
                    break;
                case 3: // Write File Operation (Overwrite existing content)
                    System.out.print("Enter the file name to write: ");
                    fileName = scanner.nextLine();
                    System.out.print("Enter content to write: ");
                    String contentToWrite = scanner.nextLine();
                    writeFile(fileName, contentToWrite);
                    break;
                case 4: // Append File Operation (Add to the end of the file)
                    System.out.print("Enter the file name to append: ");
                    fileName = scanner.nextLine();
                    System.out.print("Enter content to append: ");
                    String contentToAppend = scanner.nextLine();
                    appendFile(fileName, contentToAppend);
                    break;
                case 5: // Delete File Operation
                    System.out.print("Enter the file name to delete: ");
                    fileName = scanner.nextLine();
                    deleteFile(fileName);
                    break;
                case 6: // Exit the program
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();   // Close the Scanner to free resources
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Validates the file name.
     * Allowed characters: letters, digits, underscore (_), hyphen (-), and space.
     * Allowed: Optionally one period (.) separating the name and extension.
     * For example, "example.txt" is valid but "JAV.txt.TXT" is invalid.
     */
    public static boolean isValidFileName(String fileName) {
        // Regex explanation:
        // ^                       : start of the string
        // [a-zA-Z0-9_\\- ]+       : one or more allowed characters (letters, digits, underscore, hyphen, space)
        // (\\.[a-zA-Z0-9_\\-]+)?   : optionally, one period followed by one or more allowed characters (extension)
        // $                       : end of the string
        return fileName.matches("^[a-zA-Z0-9_\\- ]+(\\.[a-zA-Z0-9_\\-]+)?$");
    }

    // Method to create a new file
    public static void createFile(String fileName) {
        // Validate the file name first
        if (!isValidFileName(fileName)) {
            System.out.println("Invalid file name. File creation aborted.");
            return;
        }
        try {
            File file = new File(fileName);
            // Create the file if it does not exist
            if (file.createNewFile()) {
                System.out.println("File created successfully: " + fileName);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred during file creation: " + e.getMessage());
        }
    }

    // Method to read from a file and display its contents
    public static void readFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("\nFile Contents:");
            // Read each line until the end of the file is reached
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred during reading: " + e.getMessage());
        }
    }

    // Method to write to a file (overwrites any existing content)
    public static void writeFile(String fileName, String content) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            printWriter.println(content);  // Write the provided content
            System.out.println("Content written to the file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred during writing: " + e.getMessage());
        }
    }

    // Method to append content to an existing file
    public static void appendFile(String fileName, String content) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
            printWriter.println(content);  // Append the provided content
            System.out.println("Content appended to the file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred during appending: " + e.getMessage());
        }
    }

    // Method to delete a file
    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        // Attempt to delete the file
        if (file.delete()) {
            System.out.println("File deleted successfully: " + fileName);
        } else {
            System.out.println("Failed to delete the file. It may not exist.");
        }
    }
}



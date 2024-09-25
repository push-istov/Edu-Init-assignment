import java.util.Scanner;

//Supported commands:
//Add Task
//Remove Task
//View Tasks
//View Tasks by Priority
//Exit

public class Main {
    public static void main(String[] args) {
        ScheduleManager scheduleManager = ScheduleManager.getInstance();
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do {
            System.out.println("\nAstronaut Schedule Manager");
            System.out.println("Enter command (e.g., Add Task(\"Morning Exercise\", \"07:00\", \"08:00\", \"High\")):");
            input = scanner.nextLine();

            if (input.startsWith("Add Task(")) {
                try {
                    Task task = parseAddTaskInput(input);
                    scheduleManager.addTask(task);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("Remove Task(")) {
                String description = extractTaskDescription(input);
                scheduleManager.removeTask(description);
            } else if (input.equalsIgnoreCase("View Tasks")) {
                scheduleManager.viewTasks();
            } else if (input.startsWith("View Tasks by Priority(")) {
                String priority = extractTaskPriority(input);
                scheduleManager.viewTasksByPriority(priority);
            } else if (input.equalsIgnoreCase("Exit")) {
                System.out.println("Exiting the application.");
                scanner.close();
                System.exit(0);
            } else if (!input.equalsIgnoreCase("Exit")){
                System.out.println("Invalid command. Please try again.");
            }
        } while (!input.equalsIgnoreCase("Exit"));  // The loop ends when the user types "Exit"

        System.out.println("Exiting the application.");
        scanner.close();
    }

    // Method to parse the input string
    private static Task parseAddTaskInput(String input) throws IllegalArgumentException {
        // Example input: Add Task("Morning Exercise", "07:00", "08:00", "High")
        input = input.trim();

        // Remove "Add Task(" at the start and ")" at the end
        input = input.substring(9, input.length() - 1);

        // Split the string based on commas to extract task details
        String[] parts = splitInput(input, 4);  // We expect 4 parts: description, startTime, endTime, priority

        if (parts.length != 4) {
            throw new IllegalArgumentException("Error: Invalid input format for Add Task.");
        }

        String description = removeQuotes(parts[0]);
        String startTime = removeQuotes(parts[1]);
        String endTime = removeQuotes(parts[2]);
        String priority = removeQuotes(parts[3]);

        return TaskFactory.createTask(description, startTime, endTime, priority);
    }

    // Helper method to split input by commas, accounting for quoted sections
    private static String[] splitInput(String input, int expectedParts) {
        String[] parts = new String[expectedParts];
        int partIndex = 0;
        StringBuilder currentPart = new StringBuilder();
        boolean insideQuotes = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '"') {
                insideQuotes = !insideQuotes;  // Toggle quote state
            } else if (c == ',' && !insideQuotes) {
                parts[partIndex++] = currentPart.toString().trim();
                currentPart.setLength(0);  // Reset the current part
            } else {
                currentPart.append(c);
            }
        }
        // Add the last part
        parts[partIndex] = currentPart.toString().trim();

        return parts;
    }

    // Method to extract task description from the input (for Remove and Mark as Completed)
    private static String extractTaskDescription(String input) {
        // Example input: Remove Task("Morning Exercise")
        input = input.trim();

        // Remove "Task(" at the start and ")" at the end
        input = input.substring(input.indexOf("Task(") + 5, input.length() - 1);

        // Return the cleaned description without quotes
        return removeQuotes(input);
    }

    // Method to extract priority from input (for View Tasks by Priority)
    private static String extractTaskPriority(String input) {
        // Example input: View Tasks by Priority("High")
        input = input.trim();

        // Remove "Priority(" at the start and ")" at the end
        input = input.substring(input.indexOf("Priority(") + 9, input.length() - 1);

        // Return the cleaned priority without quotes
        return removeQuotes(input);
    }

    // Helper method to remove quotes from a string
    private static String removeQuotes(String input) {
        if (input.startsWith("\"") && input.endsWith("\"")) {
            return input.substring(1, input.length() - 1);
        }
        return input;
    }
}

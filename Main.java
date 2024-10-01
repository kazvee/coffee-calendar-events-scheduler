
// Import the Scanner class for user input
import java.util.Scanner;

// Main class for the event scheduling program
public class Main {
  public static void main(String[] args) {

    // Create a Scanner object to read user input
    Scanner scanner = new Scanner(System.in);

    // Create a Scheduler instance to manage events
    Scheduler scheduler = new Scheduler(scanner);

    // Loop to collect and display events
    while (true) {
      // Collect events from user input
      scheduler.collectEvents();

      // Display the collected events
      scheduler.displayEvents();

      // Check if user wants to restart the scheduling process
      if (!scheduler.userRequestsRestart()) {

        // Print a goodbye message and exit the loop
        System.out.print("\nThank you for using the Coffee Calendar Events Scheduler. Goodbye!\n\n");
        break;
      }
    }

    // Close the Scanner to release resources
    scanner.close();
  }
}
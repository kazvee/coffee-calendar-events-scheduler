
// Import classes for date handling, list management, and user input
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// Public class for scheduling events
public class Scheduler {

  // List to hold Event objects
  private List<Event> events = new ArrayList<Event>();

  // Formatter for dates in the dd/MM/yyyy format
  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  // Scanner to read user input from the console
  private Scanner scanner;

  // Constructor to initialize the scanner
  public Scheduler(Scanner scanner) {
    this.scanner = scanner;
  }

  // Method to collect events from the user
  public void collectEvents() {

    // Greet the user when the program starts
    System.out.print("\nWelcome to the Coffee Calendar Events Scheduler!");

    // Clear the list of events for each session
    events.clear();

    while (true) {

      // Prompt user to input event details
      System.out.print(
          "\nPlease input an upcoming Event Date (using numbers in the format DD/MM/YYYY), Event Description, and related Contact Name separated by a comma.\nIf you are finished event planning, enter 'done' to finish.\nPress the Enter key to submit your data:\n");

      // Read user input and trim whitespace
      String input = scanner.nextLine().trim();

      // Exit the loop if the user is done entering events
      if (input.equalsIgnoreCase("done")) {
        break;
      }

      // Split input into date, description, and contact name using the commas
      String[] parts = input.split(",", -1);

      // Validate input to ensure it contains a Date, Contact, and Description
      if (parts.length != 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {

        // Advise the user their input was invalid and remind them what we expect
        System.out.println(
            "Invalid input. Please provide the Event Date, Event Description, and Contact Name separated by commas.");
        continue;
      }

      // Remove any whitespace around each of the 3 sections
      String dateString = parts[0].trim();
      String contact = parts[1].trim();
      String description = parts[2].trim();

      // Check if the date is in the future
      LocalDate date;
      try {
        date = LocalDate.parse(dateString, formatter);

        // If the date is in the past then print error message
        if (date.isBefore(LocalDate.now())) {
          System.out.println("\nInvalid date. The Event Date must be in the future. \nPlease try again.");
          continue;
        }
      } catch (Exception e) {

        // Handle invalid date format
        System.out.println(
            "Invalid date format. The Event Date must be input using numbers formatted as DD/MM/YYYY.\nPlease try again.");
        continue;
      }

      // Add the valid event to the list
      events.add(new Event(date, contact, description));
    }
  }

  // Method to display events sorted by date
  public void displayEvents() {

    // Sort events by date with soonest upcoming first
    Collections.sort(events, new Comparator<Event>() {
      public int compare(Event event1, Event event2) {
        return event1.getDate().compareTo(event2.getDate());
      }
    });

    // Check if there are no events to display
    if (events.isEmpty()) {
      System.out.println("\n=====================");
      System.out.println("\nNo events to display.");
      System.out.println("\n=====================");
    } else {

      // Display the header and events
      System.out.println("\n================");
      System.out.println("\nUpcoming Events:");
      System.out.println("\n================");

      // Loop through all events and display them to user
      for (Event event : events) {
        System.out.println(event);
      }
    }
  }

  // Method to check if the user wants to start another session
  public boolean userRequestsRestart() {
    System.out
        .print(
            "\nEnter 'start' to start another event planning session or enter any other text to exit the program:\n");

    // Await user input and assign it to the restartPlanning variable
    String restartPlanning = scanner.nextLine();

    // Check if user has typed in the word start, using any upper or lower case
    return restartPlanning.equalsIgnoreCase("start");
  }
}
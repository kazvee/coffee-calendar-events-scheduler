
// Import classes for handling dates and formatting
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Public Event class with date, contact name, and description
public class Event {
  private LocalDate date;
  private String contact;
  private String description;
  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  // Constructor for creating an Event instance
  public Event(LocalDate date, String contact, String description) {
    this.date = date;
    this.contact = contact;
    this.description = description;
  }

  // Getter for the event date
  public LocalDate getDate() {
    return date;
  }

  // Getter for the contact name
  public String getContact() {
    return contact;
  }

  // Getter for the event description
  public String getDescription() {
    return description;
  }

  // Print event details as a formatted string
  public String toString() {
    return date.format(formatter) + "\t" + contact + " - " + description;
  }
}
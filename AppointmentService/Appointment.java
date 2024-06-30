package project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment {
    private final String appmtId;
    private Date appointmentDate;
    private String description;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Appointment(String appmtId, String appointmentDate, String description) throws ParseException {
        if (appmtId == null || appmtId.length() > 10) {
            throw new IllegalArgumentException("Appointment ID must be non-null and no longer than 10 characters.");
        }
        this.appmtId = appmtId;

        // Parse the appointmentDate and check if it is in the past
        Date parsedDate = parseDate(appointmentDate);
        if (parsedDate == null || parsedDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date must be non-null and not in the past.");
        }
        this.appointmentDate = parsedDate;

        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Description must be non-null and no longer than 50 characters.");
        }
        this.description = description;
    }

    // Setters for appointmentDate and description
    // Set appointmentDate
    public void setAppointmentDate(String appointmentDate) throws ParseException {
        Date parsedDate = parseDate(appointmentDate);
        if (parsedDate == null || parsedDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date must be non-null and not in the past.");
        }
        this.appointmentDate = parsedDate;
    }

    // Set task description
    public void setDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Description must be non-null and no longer than 50 characters.");
        }
        this.description = description;
    }

    // Getters
    public String getAppmtId() {
        return appmtId;
    }

    public String getAppointmentDate() {
        return dateFormat.format(appointmentDate);
    }

    public String getDescription() {
        return description;
    }

    // Helper method to parse and validate date
    private Date parseDate(String date) throws ParseException {
        if (date == null) {
            return null;
        }
        return dateFormat.parse(date);
    }
}

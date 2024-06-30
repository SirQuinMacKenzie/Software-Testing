package test;


import project.Appointment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.text.ParseException;

class AppointmentTest {

    private Appointment appointment;

    @BeforeEach
    void setUp() throws ParseException {
        appointment = new Appointment("1234567890", "2024-12-25", "Initial description");
    }

    @Test
    void testValidAppointmentCreation() throws ParseException {
        Appointment validAppointment = new Appointment("1234567890", "2024-12-25", "Valid description");
        assertEquals("1234567890", validAppointment.getAppmtId());
        assertEquals("2024-12-25", validAppointment.getAppointmentDate());
        assertEquals("Valid description", validAppointment.getDescription());
    }

    @Test
    void testInvalidAppointmentId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(null, "2024-12-25", "Description");
        });
        assertEquals("Appointment ID must be non-null and no longer than 10 characters.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345678901", "2024-12-25", "Description");
        });
        assertEquals("Appointment ID must be non-null and no longer than 10 characters.", exception.getMessage());
    }

    @Test
    void testInvalidAppointmentDate() {
        Exception exception = assertThrows(ParseException.class, () -> {
            new Appointment("1234567890", "invalid-date", "Description");
        });
        assertEquals("Unparseable date: \"invalid-date\"", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("1234567890", null, "Description");
        });
        assertEquals("Appointment date must be non-null and not in the past.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("1234567890", "2020-01-01", "Description");
        });
        assertEquals("Appointment date must be non-null and not in the past.", exception.getMessage());
    }

    @Test
    void testInvalidDescription() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("1234567890", "2024-12-25", null);
        });
        assertEquals("Description must be non-null and no longer than 50 characters.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("1234567890", "2024-12-25", "This description is definitely more than fifty characters long.");
        });
        assertEquals("Description must be non-null and no longer than 50 characters.", exception.getMessage());
    }

    @Test
    void testSetValidAppointmentDate() throws ParseException {
        appointment.setAppointmentDate("2024-11-30");
        assertEquals("2024-11-30", appointment.getAppointmentDate());
    }

    @Test
    void testSetInvalidAppointmentDate() {
        Exception exception = assertThrows(ParseException.class, () -> {
            appointment.setAppointmentDate("invalid-date");
        });
        assertEquals("Unparseable date: \"invalid-date\"", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            appointment.setAppointmentDate(null);
        });
        assertEquals("Appointment date must be non-null and not in the past.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            appointment.setAppointmentDate("2020-01-01");
        });
        assertEquals("Appointment date must be non-null and not in the past.", exception.getMessage());
    }

    @Test
    void testSetValidDescription() {
        appointment.setDescription("New valid description");
        assertEquals("New valid description", appointment.getDescription());
    }

    @Test
    void testSetInvalidDescription() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDescription(null);
        });
        assertEquals("Description must be non-null and no longer than 50 characters.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDescription("This description is definitely more than fifty characters long.");
        });
        assertEquals("Description must be non-null and no longer than 50 characters.", exception.getMessage());
    }
}

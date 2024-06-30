package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.*;
import project.Appointment;
import project.AppointmentService;

class AppointmentServiceTest {

    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        appointmentService = new AppointmentService();
    }

    @Test
    void testAddValidAppointment() throws ParseException {
        appointmentService.addAppointment("1234567890", "2024-12-25", "Valid description");
        Appointment appointment = appointmentService.getAppointment("1234567890");

        assertNotNull(appointment);
        assertEquals("1234567890", appointment.getAppmtId());
        assertEquals("2024-12-25", appointment.getAppointmentDate());
        assertEquals("Valid description", appointment.getDescription());
    }

    @Test
    void testAddDuplicateAppointmentId() throws ParseException {
        appointmentService.addAppointment("1234567890", "2024-12-25", "First description");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment("1234567890", "2024-12-26", "Second description");
        });
        assertEquals("Appointment ID must be unique.", exception.getMessage());
    }

    @Test
    void testDeleteValidAppointment() throws ParseException {
        appointmentService.addAppointment("1234567890", "2024-12-25", "Valid description");
        appointmentService.deleteAppointment("1234567890");
        assertNull(appointmentService.getAppointment("1234567890"));
    }

    @Test
    void testDeleteNonexistentAppointment() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.deleteAppointment("nonexistentID");
        });
        assertEquals("Appointment ID does not exist.", exception.getMessage());
    }

    @Test
    void testGetNonexistentAppointment() {
        assertNull(appointmentService.getAppointment("nonexistentID"));
    }

    @Test
    void testAddInvalidAppointmentId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment(null, "2024-12-25", "Description");
        });
        assertEquals("Appointment ID must be non-null and no longer than 10 characters.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment("12345678901", "2024-12-25", "Description");
        });
        assertEquals("Appointment ID must be non-null and no longer than 10 characters.", exception.getMessage());
    }

    @Test
    void testAddInvalidAppointmentDate() {
        Exception exception = assertThrows(ParseException.class, () -> {
            appointmentService.addAppointment("1234567890", "invalid-date", "Description");
        });
        assertEquals("Unparseable date: \"invalid-date\"", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment("1234567890", null, "Description");
        });
        assertEquals("Appointment date must be non-null and not in the past.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment("1234567890", "2020-01-01", "Description");
        });
        assertEquals("Appointment date must be non-null and not in the past.", exception.getMessage());
    }

    @Test
    void testAddInvalidDescription() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment("1234567890", "2024-12-25", null);
        });
        assertEquals("Description must be non-null and no longer than 50 characters.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment("1234567890", "2024-12-25", "This description is definitely more than fifty characters long.");
        });
        assertEquals("Description must be non-null and no longer than 50 characters.", exception.getMessage());
    }
}


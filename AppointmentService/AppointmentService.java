package project;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class AppointmentService {
    private final Map<String, Appointment> appointments;

    public AppointmentService() {
        this.appointments = new HashMap<>();
    }

    public void addAppointment(String appmtId, String appointmentDate, String description) throws ParseException {
        if (appointments.containsKey(appmtId)) {
            throw new IllegalArgumentException("Appointment ID must be unique.");
        }
        Appointment newAppointment = new Appointment(appmtId, appointmentDate, description);
        appointments.put(appmtId, newAppointment);
    }

    public void deleteAppointment(String appmtId) {
        if (!appointments.containsKey(appmtId)) {
            throw new IllegalArgumentException("Appointment ID does not exist.");
        }
        appointments.remove(appmtId);
    }

    public Appointment getAppointment(String appmtId) {
        return appointments.get(appmtId);
    }
}

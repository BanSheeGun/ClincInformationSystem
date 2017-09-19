package dao;
import entity.*;

public interface AppointmentDao {
	Appointment queryAppointment(int apid);
	Appointment updateAppointment(int apid, int st);
	Appointment createPatientRecord(Appointment ap);
	AppointmentPageModel queryAppointment(int start, String more);
}

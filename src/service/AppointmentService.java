package service;

import entity.Appointment;
import entity.AppointmentPageModel;
import dao.*;
import dao.impl.*;

public class AppointmentService {
	private AppointmentDao adao = new AppointmentDao4MySQL();
	public Appointment queryAppointment(int apid) {
		return adao.queryAppointment(apid);
	}
	public Appointment updateAppointment(int apid, int st) {
		return adao.updateAppointment(apid, st);
	}
	public Appointment createPatientRecord(Appointment ap) {
		return adao.createPatientRecord(ap);
	}
	public AppointmentPageModel queryAppointment(int start, String more) {
		return adao.queryAppointment(start, more);
	}
}

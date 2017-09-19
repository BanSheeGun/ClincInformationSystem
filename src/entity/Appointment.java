package entity;

public class Appointment {
	private int ApporintmentId;
	private int DentistId;
	private int PatientId;
	private int ClinicId;
	private String Date;
	private String Status;
	public int getApporintmentId() {
		return ApporintmentId;
	}
	public void setApporintmentId(int apporintmentId) {
		ApporintmentId = apporintmentId;
	}
	public int getDentistId() {
		return DentistId;
	}
	public void setEmployeeId(int dentistId) {
		DentistId = dentistId;
	}
	public int getPatientId() {
		return PatientId;
	}
	public void setPatientId(int patientId) {
		PatientId = patientId;
	}
	public int getClinicId() {
		return ClinicId;
	}
	public void setClinicId(int clinicId) {
		ClinicId = clinicId;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
}

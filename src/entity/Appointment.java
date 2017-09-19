package entity;

public class Appointment {
	private int ApporintmentId;
	private int DentistId;
	private int PatientId;
	private int ClinicId;
	private String Date;
	private int Status;
	public int getApporintmentId() {
		return ApporintmentId;
	}
	public void setApporintmentId(int apporintmentId) {
		ApporintmentId = apporintmentId;
	}
	public int getDentistId() {
		return DentistId;
	}
	public void setDentistId(int dentistId) {
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
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	
}

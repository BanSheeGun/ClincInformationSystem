package entity;

public class PatientRecord {
	private int PatientRecordId;
	private int PatientId;
	private int EmployeeId;
	private String Content;
	private int PaymentId;
	public int getPatientRecordId() {
		return PatientRecordId;
	}
	public void setPatientRecordId(int patientRecordId) {
		PatientRecordId = patientRecordId;
	}
	public int getPatientId() {
		return PatientId;
	}
	public void setPatientId(int patientId) {
		PatientId = patientId;
	}
	public int getEmployeeId() {
		return EmployeeId;
	}
	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getPaymentId() {
		return PaymentId;
	}
	public void setPaymentId(int paymentId) {
		PaymentId = paymentId;
	}
	
}

package entity;

public class Invoice {
	private int InvoiceId;
	private int PatientId;
	private double Number;
	private String Date;
	public int getInvoiceId() {
		return InvoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		InvoiceId = invoiceId;
	}
	public int getPatientId() {
		return PatientId;
	}
	public void setPatientId(int patientId) {
		PatientId = patientId;
	}
	public double getNumber() {
		return Number;
	}
	public void setNumber(double number) {
		Number = number;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	
}

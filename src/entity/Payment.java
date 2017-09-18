package entity;

public class Payment {
	private int PaymentId;
	private int PatientId;
	private double Number;
	private int InvoiceId;
	public int getPaymentId() {
		return PaymentId;
	}
	public void setPaymentId(int paymentId) {
		PaymentId = paymentId;
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
	public int getInvoiceId() {
		return InvoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		InvoiceId = invoiceId;
	}
	
}

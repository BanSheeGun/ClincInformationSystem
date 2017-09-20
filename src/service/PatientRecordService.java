package service;

import dao.*;
import dao.impl.*;
import entity.Appointment;
import entity.Invoice;
import entity.PatientRecord;
import entity.PatientRecordPageModel;
import entity.Payment;

public class PatientRecordService {
	private PatientRecordDao prd = new PatientRecordDao4MySQL();
	private PaymentDao payd = new PaymentDao4MySQL();
	private InvoiceDao id = new InvoiceDao4MySQL();
	public PatientRecord queryPatientRecord(int prid) {
		return prd.queryPatientRecord(prid);
	}
	public PatientRecord updatePatientRecord(int prid, String content){
		return prd.updatePatientRecord(prid, content);
	}
	public PatientRecord updatePatientRecord(int prid, int payid){
		return prd.updatePatientRecord(prid, payid);
	}
	public PatientRecord createPatientRecord(Appointment ap){
		return prd.createPatientRecord(ap);
	}
	public PatientRecordPageModel  queryPatientRecord(int start, String more){
		return prd.queryPatientRecord(start, more);
	}
	public Payment queryPayment(int pid) {
		return payd.queryPayment(pid);
	}
	public Payment createPayment(Payment pay) {
		return payd.createPayment(pay);
	}
	public Payment updatePayment(int pid, int iid) {
		return payd.updatePayment(pid, iid);
	}
	public Invoice queryInvoice(int iid) {
		return id.queryInvoice(iid);
	}
	public Invoice createInvoice(Payment p) {
		return id.createInvoice(p);
	}
}

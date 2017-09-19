package service;

import dao.*;
import dao.impl.*;
import entity.Appointment;
import entity.PatientRecord;
import entity.PatientRecordPageModel;

public class PatientRecordService {
	private PatientRecordDao prd = new PatientRecordDao4MySQL();
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
}

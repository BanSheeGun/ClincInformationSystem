package dao;
import entity.*;

public interface PatientRecordDao {
	PatientRecord queryPatientRecord(int prid);
	PatientRecord updatePatientRecord(int prid, String content);
	PatientRecord updatePatientRecord(int prid, int payid);
	PatientRecord createPatientRecord(Appointment ap);
	PatientRecordPageModel queryPatientRecord(int start, String more);
}

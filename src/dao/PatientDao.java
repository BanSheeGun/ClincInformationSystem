package dao;

import java.util.*;
import entity.*;

public interface PatientDao {
	boolean deletePatientById(int patientId);
	Patient getPatientById(int patientId);
	boolean updatePatientInfo(Patient patient);
	boolean createPatientInfo(Patient patient);
	List<Patient> getPatient(int start);
	int getPatientNumbers();
}

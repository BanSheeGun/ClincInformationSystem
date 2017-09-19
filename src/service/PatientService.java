package service;
import java.util.*;
import entity.*;
import dao.*;
import dao.impl.*;

public class PatientService {
	private PatientDao pdao = new PatientDao4MySQL();
	public boolean deletePatientById(int patientId) {
		return pdao.deletePatientById(patientId);
	}
	public Patient getPatientById(int patientId){
		return pdao.getPatientById(patientId);
	}
	public Patient updatePatientInfo(Patient patient){
		return pdao.updatePatientInfo(patient);
	}
	public Patient createPatientInfo(Patient patient){
		return pdao.createPatientInfo(patient);
	}
	public List<Patient> getPatient(int start){
		return pdao.getPatient(start);
	}
	public int getPatientNumbers(){
		return pdao.getPatientNumbers();
	}
}

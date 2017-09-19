package service;

import java.util.*;
import entity.*;
import dao.*;
import dao.impl.*;

public class DentistService {
	private DentistDao ddao = new DentistDao4MySQL();
	public boolean deleteDentistById(int dentistId) {
		return ddao.deleteDentistById(dentistId);
	}
	public Dentist getDentistById(int dentistId){
		return ddao.getDentistById(dentistId);
	}
	public Dentist updateDentistInfo(Dentist dentist){
		return ddao.updateDentistInfo(dentist);
	}
	public Dentist createDentistInfo(Dentist dentist){
		return ddao.createDentistInfo(dentist);
	}
	public List<Dentist> getDentist(int start){
		return ddao.getDentist(start);
	}
	public int getDentistNumbers(){
		return ddao.getDentistNumbers();
	}
}

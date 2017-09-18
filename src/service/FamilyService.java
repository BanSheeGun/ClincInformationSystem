package service;
import java.util.List;

import dao.*;
import dao.impl.*;
import entity.Family;
import entity.Patient;

public class FamilyService {
	private FamilyDao fdao = new FamilyDao4MySQL();
	public List<Patient> queryFamilyMembers(int fid) {
		return fdao.queryFamilyMembers(fid);
	}
	public Family queryFamily(int fid) {
		return fdao.queryFamily(fid);
	}
	public Family queryFamily(String add) {
		return fdao.queryFamily(add);
	}
	public Family createFamily(String add) {
		return fdao.createFamily(add);
	}
	public List<Family> queryFamilyList(int start) {
		return fdao.queryFamilyList(start);
	}
	
	public int queryFamilyNumbers() {
		return fdao.queryFamilyNumbers();
	}
	
	boolean deleteFamily(int fid) {
		return fdao.deleteFamily(fid);
	}
	boolean updateFamily(Family f) {
		return fdao.updateFamily(f);
	}
}

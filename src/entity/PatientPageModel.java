package entity;

import java.util.*;

public class PatientPageModel {
	private int pn, ptn;
	private List<Patient> lp;
	public int getPn() {
		return pn;
	}
	public void setPn(int pn) {
		this.pn = pn;
	}
	public int getPtn() {
		return ptn;
	}
	public void setPtn(int ptn) {
		this.ptn = ptn;
	}
	public List<Patient> getLp() {
		return lp;
	}
	public void setLp(List<Patient> lp) {
		this.lp = lp;
	}
}

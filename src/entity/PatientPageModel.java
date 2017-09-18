package entity;

import java.util.*;

public class PatientPageModel {
	private int pt, ptn;
	private List<Patient> lp;
	public int getPt() {
		return pt;
	}
	public void setPt(int pt) {
		this.pt = pt;
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

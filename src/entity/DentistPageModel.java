package entity;

import java.util.*;

public class DentistPageModel {
	private int dn, dtn;
	private List<Dentist> ld;
	public int getDn() {
		return dn;
	}
	public void setDn(int dn) {
		this.dn = dn;
	}
	public int getDtn() {
		return dtn;
	}
	public void setDtn(int dtn) {
		this.dtn = dtn;
	}
	public List<Dentist> getLd() {
		return ld;
	}
	public void setLd(List<Dentist> ld) {
		this.ld = ld;
	}
	
}

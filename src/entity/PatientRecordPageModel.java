package entity;
import java.util.*;

public class PatientRecordPageModel {
	private int prtn, prn;
	private List<PatientRecord> lpr;
	public int getPrtn() {
		return prtn;
	}
	public void setPrtn(int prtn) {
		this.prtn = prtn;
	}
	public int getPrn() {
		return prn;
	}
	public void setPrn(int prn) {
		this.prn = prn;
	}
	public List<PatientRecord> getLpr() {
		return lpr;
	}
	public void setLpr(List<PatientRecord> lpr) {
		this.lpr = lpr;
	}
}

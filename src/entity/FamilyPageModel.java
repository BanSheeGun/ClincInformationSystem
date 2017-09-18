package entity;
import java.util.*;
public class FamilyPageModel {
	private int fn, ftn;
	private List<Family> lf;
	public int getFn() {
		return fn;
	}
	public void setFn(int fn) {
		this.fn = fn;
	}
	public int getFtn() {
		return ftn;
	}
	public void setFtn(int ftn) {
		this.ftn = ftn;
	}
	public List<Family> getLf() {
		return lf;
	}
	public void setLf(List<Family> lf) {
		this.lf = lf;
	}
}

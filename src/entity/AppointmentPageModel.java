package entity;
import java.util.*;

public class AppointmentPageModel {
	private int aptn, apn;
	private List<Appointment> lap;
	public int getAptn() {
		return aptn;
	}
	public void setAptn(int aptn) {
		this.aptn = aptn;
	}
	public int getApn() {
		return apn;
	}
	public void setApn(int apn) {
		this.apn = apn;
	}
	public List<Appointment> getLap() {
		return lap;
	}
	public void setLap(List<Appointment> lap) {
		this.lap = lap;
	}
	
}

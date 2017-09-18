package entity;

public class Dentist {
	private int DentistId;
	private int ClincId;
	private String Name;
	private String Sex;
	private int Age;
	private String Tel;
	private String Email;
	private String Address;
	public int getDentistId() {
		return DentistId;
	}
	public void setDentistId(int dentistId) {
		DentistId = dentistId;
	}
	public int getClincId() {
		return ClincId;
	}
	public void setClincId(int clincId) {
		ClincId = clincId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
}

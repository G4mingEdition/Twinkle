/**
	@author
		• Denis Christopher
		• Prevot Sean
**/

package be.twinkle.domaine;

import java.util.ArrayList;

public class Employee {
	/* ATTRIBUTES */
	private int id;
	private String firstname;
	private String lastname;
	private String address;
	private String phone;
	private String birthday;
	private String hiredDate;
	private String degrees;
	private ArrayList<Skill> skills;
	private Function function;
	
	
	/* CONSTRUCTOR */
	public Employee(int id, String firstname, String lastname, String address, String phone, String birthday, String hiredDate, String degrees, ArrayList<Skill> skills, Function function) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.phone = phone;
		this.birthday = birthday;
		this.hiredDate = hiredDate;
		this.degrees = degrees;
		this.skills = skills;
		this.function = function;
	}
	
	public Employee() {
		super();
		this.id = 0;
		this.firstname = "";
		this.lastname = "";
		this.address = "";
		this.phone = "";
		this.birthday = "";
		this.hiredDate = "";
		this.degrees = "";
		this.skills = new ArrayList<Skill>();
		this.function = null;
	}


	/* METHODS */
	public boolean addSkill(Skill skill) {
		if (this.skills.contains(skill)){
			return false; 
		}
		else {
			this.skills.add(skill);
			return true;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHiredDate() {
		return hiredDate;
	}

	public void setHiredDate(String hiredDate) {
		this.hiredDate = hiredDate;
	}

	public String getDegrees() {
		return degrees;
	}

	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
	}	
	
}

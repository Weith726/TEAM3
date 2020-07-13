package com.adopter.model;

import java.io.Serializable;
import java.sql.Date;

public class AdopterVO implements Serializable {
	
	private String adopterNo;
	private String adopterName;
	private String adopterGender;
	private String adopterOccupation;
	private String adopterMail;
	
	public String getAdopterNo() {
		return adopterNo;
	}
	public void setAdopterNo(String adopterNo) {
		this.adopterNo = adopterNo;
	}
	public String getAdopterName() {
		return adopterName;
	}
	public void setAdopterName(String adopterName) {
		this.adopterName = adopterName;
	}
	public String getAdopterGender() {
		return adopterGender;
	}
	public void setAdopterGender(String adopterGender) {
		this.adopterGender = adopterGender;
	}
	public String getAdopterOccupation() {
		return adopterOccupation;
	}
	public void setAdopterOccupation(String adopterOccupation) {
		this.adopterOccupation = adopterOccupation;
	}
	public String getAdopterMail() {
		return adopterMail;
	}
	public void setAdopterMail(String adopterMail) {
		this.adopterMail = adopterMail;
	}
	

}

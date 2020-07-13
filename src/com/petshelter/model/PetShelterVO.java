package com.petshelter.model;

import java.io.Serializable;
import java.sql.Date;

public class PetShelterVO implements Serializable {
	
	private String shelterNo;
	private Date cleanDate;
	private Integer shelterStatus;
	
	public String getShelterNo() {
		return shelterNo;
	}
	public void setShelterNo(String shelterNo) {
		this.shelterNo = shelterNo;
	}
	public Date getCleanDate() {
		return cleanDate;
	}
	public void setCleanDate(Date cleanDate) {
		this.cleanDate = cleanDate;
	}
	public Integer getShelterStatus() {
		return shelterStatus;
	}
	public void setShelterStatus(Integer shelterStatus) {
		this.shelterStatus = shelterStatus;
	}
}

package com.adoptedpets.model;

import java.io.Serializable;
import java.sql.Date;

public class AdoptedPetsVO implements Serializable {

	private String petNo;
	private String adopterNo;
	private String shelterNo;
	private String petBreed;
	private Date adoptedDate;
	private byte[] petPic;
	private Date adoptDate;
	private Date interviewDate;
	private String interviewInfo;
	private String petSpecies;
	private String petGender;
	private Integer adoptStatus;
	
	public String getPetNo() {
		return petNo;
	}
	public void setPetNo(String petNo) {
		this.petNo = petNo;
	}
	public String getAdopterNo() {
		return adopterNo;
	}
	public void setAdopterNo(String adopterNo) {
		this.adopterNo = adopterNo;
	}
	public String getShelterNo() {
		return shelterNo;
	}
	public void setShelterNo(String shelterNo) {
		this.shelterNo = shelterNo;
	}
	public String getPetBreed() {
		return petBreed;
	}
	public void setPetBreed(String petBreed) {
		this.petBreed = petBreed;
	}
	public Date getAdoptedDate() {
		return adoptedDate;
	}
	public void setAdoptedDate(Date adoptedDate) {
		this.adoptedDate = adoptedDate;
	}
	public byte[] getPetPic() {
		return petPic;
	}
	public void setPetPic(byte[] petPic) {
		this.petPic = petPic;
	}
	public Date getAdoptDate() {
		return adoptDate;
	}
	public void setAdoptDate(Date adoptDate) {
		this.adoptDate = adoptDate;
	}
	public Date getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}
	public String getInterviewInfo() {
		return interviewInfo;
	}
	public void setInterviewInfo(String interviewInfo) {
		this.interviewInfo = interviewInfo;
	}
	public String getPetSpecies() {
		return petSpecies;
	}
	public void setPetSpecies(String petSpecies) {
		this.petSpecies = petSpecies;
	}
	public String getPetGender() {
		return petGender;
	}
	public void setPetGender(String petGender) {
		this.petGender = petGender;
	}
	public Integer getAdoptStatus() {
		return adoptStatus;
	}
	public void setAdoptStatus(Integer adoptStatus) {
		this.adoptStatus = adoptStatus;
	}
	
	
}

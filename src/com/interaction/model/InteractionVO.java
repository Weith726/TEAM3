package com.interaction.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class InteractionVO implements Serializable {
	
	private String interactionNo;
	private String petNo;
	private String adopterNo;
	private Timestamp interactionDate;
	private String interactionStatus;
	private Integer adoptDesire;
	
	public String getInteractionNo() {
		return interactionNo;
	}
	public void setInteractionNo(String interactionNo) {
		this.interactionNo = interactionNo;
	}
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
	public Timestamp getInteractionDate() {
		return interactionDate;
	}
	public void setInteractionDate(Timestamp interactionDate) {
		this.interactionDate = interactionDate;
	}
	public String getInteractionStatus() {
		return interactionStatus;
	}
	public void setInteractionStatus(String interactionStatus) {
		this.interactionStatus = interactionStatus;
	}
	public Integer getAdoptDesire() {
		return adoptDesire;
	}
	public void setAdoptDesire(Integer adoptDesire) {
		this.adoptDesire = adoptDesire;
	}
	
	
}

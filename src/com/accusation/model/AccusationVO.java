package com.accusation.model;

import java.sql.Timestamp;


public class AccusationVO  implements java.io.Serializable{
	private String accusationNo;
	private String accusationType;
	private String accusationContent;
	private Timestamp accusationtime;
	private Integer accusationStatue;
	
	public String getAccusationNo() {
		return accusationNo;
	}
	public void setAccusationNo(String accusationNo) {
		this.accusationNo = accusationNo;
	}
	public String getAccusationType() {
		return accusationType;
	}
	public void setAccusationType(String accusationType) {
		this.accusationType = accusationType;
	}
	public String getAccusationContent() {
		return accusationContent;
	}
	public void setAccusationContent(String accusationContent) {
		this.accusationContent = accusationContent;
	}
	public Timestamp getAccusationtime() {
		return accusationtime;
	}
	public void setAccusationtime(Timestamp accusationtime) {
		this.accusationtime = accusationtime;
	}
	public Integer getAccusationStatue() {
		return accusationStatue;
	}
	public void setAccusationStatue(Integer accusationStatue) {
		this.accusationStatue = accusationStatue;
	}
}

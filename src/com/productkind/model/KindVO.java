package com.productkind.model;

import java.io.Serializable;

public class KindVO implements Serializable{
	
	private Integer kindno;
	private String kindname;
		
	public KindVO() {
		super();
	}
	
	public Integer getKindno() {
		return kindno;
	}
	public void setKindno(Integer kindno) {
		this.kindno = kindno;
	}
	public String getKindname() {
		return kindname;
	}
	public void setKindname(String kindname) {
		this.kindname = kindname;
	}
	
	
}

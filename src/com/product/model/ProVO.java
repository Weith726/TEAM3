package com.product.model;

import java.io.Serializable;
import java.sql.Date;


public class ProVO implements Serializable{
	private Integer productid;
	private Integer kindno;
	private String productname;
	private Integer productprice;
	private Date producton;
	private Integer productstock; 
	private Integer productsafe;
	private byte[] productpic;
	private String productintro;
	private Integer productstatus;
	private Integer quantity;
	
	
	public ProVO() {  
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProVO( Integer kindno, String productname, Integer productprice, Date producton,
			Integer productstock, Integer productsafe, byte[] productpic, String productintro, Integer productstatus) {
		super();
		this.kindno = kindno;
		this.productname = productname;
		this.productprice = productprice;
		this.producton = producton;
		this.productstock = productstock;
		this.productsafe = productsafe;
		this.productpic = productpic;
		this.productintro = productintro;
		this.productstatus = productstatus;
	}


	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public Integer getKindno() {
		return kindno;
	}
	public void setKindno(Integer kindno) {
		this.kindno = kindno;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Integer getProductprice() {
		return productprice;
	}
	public void setProductprice(Integer productprice) {
		this.productprice = productprice;
	}
	public Date getProducton() {
		return producton;
	}
	public void setProducton(Date producton) {
		this.producton = producton;
	}
	public Integer getProductstock() {
		return productstock;
	}
	public void setProductstock(Integer productstock) {
		this.productstock = productstock;
	}
	public Integer getProductsafe() {
		return productsafe;
	}
	public void setProductsafe(Integer productsafe) {
		this.productsafe = productsafe;
	}
	public byte[] getProductpic() {
		return productpic;
	}
	public void setProductpic(byte[] productpic) {
		this.productpic = productpic;
	}
	public String getProductintro() {
		return productintro;
	}
	public void setProductintro(String productintro) {
		this.productintro = productintro;
	}
	public Integer getProductstatus() {
		return productstatus;
	}
	public void setProductstatus(Integer productstatus) {
		this.productstatus = productstatus;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productid == null) ? 0 : productid.hashCode());
		result = prime * result + ((productname == null) ? 0 : productname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProVO other = (ProVO) obj;
		if (productid == null) {
			if (other.productid != null)
				return false;
		} else if (!productid.equals(other.productid))
			return false;
		if (productname == null) {
			if (other.productname != null)
				return false;
		} else if (!productname.equals(other.productname))
			return false;
		return true;
	}
	
	
}

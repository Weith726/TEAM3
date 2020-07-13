package com.HotelOrder.model;

import java.sql.Timestamp;
import java.util.Date;

public class HotelOrderVO implements java.io.Serializable {
	
	private String orderNo;
	private String memNo;
	private String petNo;
	private String roomTypeNo;
	private Integer roomNo;
	private Integer roomTypePrice;
	private Timestamp orderDate;
	private Timestamp checkInDate;
	private Timestamp checkOutDate;
	private String totalPrice;
	private Integer hotelOrderStatus;

	// For JSON
	private String title;
	private Timestamp start;
	private Timestamp end;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMemNo() {
		return memNo;
	}

	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}

	public String getRoomTypeNo() {
		return roomTypeNo;
	}

	public void setRoomTypeNo(String roomTypeNo) {
		this.roomTypeNo = roomTypeNo;
	}

	public Integer getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}

	public Integer getRoomTypePrice() {
		return roomTypePrice;
	}

	public void setRoomTypePrice(Integer roomTypePrice) {
		this.roomTypePrice = roomTypePrice;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public Timestamp getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Timestamp checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Timestamp getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Timestamp checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getHotelOrderStatus() {
		return hotelOrderStatus;
	}

	public void setHotelOrderStatus(Integer hotelOrderStatus) {
		this.hotelOrderStatus = hotelOrderStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String memNo) {
		this.title = memNo;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp checkInDate) {
		this.start = checkInDate;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp checkOutDate) {
		this.end = checkOutDate;
	}

	public String getPetNo() {
		return petNo;
	}

	public void setPetNo(String petNo) {
		this.petNo = petNo;
	}

}

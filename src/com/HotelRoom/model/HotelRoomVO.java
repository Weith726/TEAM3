package com.HotelRoom.model;

public class HotelRoomVO implements java.io.Serializable {
	private Integer roomNo;
	private String roomTypeNo;
	private String petNo;
	private Integer roomStatus;

	public Integer getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomTypeNo() {
		return roomTypeNo;
	}

	public void setRoomTypeNo(String roomTypeNo) {
		this.roomTypeNo = roomTypeNo;
	}

	public String getPetNo() {
		return petNo;
	}

	public void setPetNo(String petNo) {
		this.petNo = petNo;
	}

	public Integer getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(Integer roomStatus) {
		this.roomStatus = roomStatus;
	}

}

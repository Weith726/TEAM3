package com.HotelRoomType.model;

public class HotelRoomTypeVO implements java.io.Serializable {
	private String roomTypeNo;
	private String roomTypeName;
	private String roomTypeDescribe;
	private String roomTypeSpace;
	private Integer roomTypePrice;
	private String roomTypeService;
	private Integer roomTypeStatus;
	private byte[] roomTypePic;

	public String getRoomTypeNo() {
		return roomTypeNo;
	}

	public void setRoomTypeNo(String roomTypeNo) {
		this.roomTypeNo = roomTypeNo;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public String getRoomTypeDescribe() {
		return roomTypeDescribe;
	}

	public void setRoomTypeDescribe(String roomTypeDescribe) {
		this.roomTypeDescribe = roomTypeDescribe;
	}

	public String getRoomTypeSpace() {
		return roomTypeSpace;
	}

	public void setRoomTypeSpace(String roomTypeSpace) {
		this.roomTypeSpace = roomTypeSpace;
	}

	public Integer getRoomTypePrice() {
		return roomTypePrice;
	}

	public void setRoomTypePrice(Integer roomTypePrice) {
		this.roomTypePrice = roomTypePrice;
	}

	public String getRoomTypeService() {
		return roomTypeService;
	}

	public void setRoomTypeService(String roomTypeService) {
		this.roomTypeService = roomTypeService;
	}

	public Integer getRoomTypeStatus() {
		return roomTypeStatus;
	}

	public void setRoomTypeStatus(Integer roomTypeStatus) {
		this.roomTypeStatus = roomTypeStatus;
	}

	public byte[] getRoomTypePic() {
		return roomTypePic;
	}

	public void setRoomTypePic(byte[] roomTypePic) {
		this.roomTypePic = roomTypePic;
	}

}

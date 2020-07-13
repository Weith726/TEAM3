package com.HotelRoom.model;

import java.util.List;

import com.HotelRoomType.model.HotelRoomTypeVO;

public interface HotelRoomDAO_interface {
	public void insert(HotelRoomVO hotelRoomVO);
	public void update(HotelRoomVO hotelRoomVO);
	public void delete(Integer roomNo);
	public void confirm(Integer roomNo);
	public void cancel(Integer roomNo);
	public HotelRoomVO findByPrimaryKey(Integer roomNo);
	public List<HotelRoomVO> getAll();
	public List<HotelRoomVO> getEmptyRoom(String roomTypeNo);
}

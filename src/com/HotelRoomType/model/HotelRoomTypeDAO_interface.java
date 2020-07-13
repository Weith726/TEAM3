package com.HotelRoomType.model;

import java.util.List;
import java.util.Set;

import com.HotelRoom.model.HotelRoomVO;

public interface HotelRoomTypeDAO_interface {

	public void insert(HotelRoomTypeVO hotelRoomTypeVO);

	public void update(HotelRoomTypeVO hotelRoomTypeVO);

	public void delete(String roomTypeNo);

	public HotelRoomTypeVO findByPrimaryKey(String roomTypeNo);

	public List<HotelRoomTypeVO> getAll();

	// 查詢該房型所對應的所有房間

	public Set<HotelRoomVO> getRoomsBy_RTID(String roomTypeNo);
}

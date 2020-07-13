package com.HotelRoom.model;

import java.util.List;

public class HotelRoomService {

	private HotelRoomDAO_interface dao;

	public HotelRoomService() {
		dao = new HotelRoomDAO();
	}

	public HotelRoomVO addHotelRoom(Integer roomNo, String roomTypeNo, String petNo, Integer roomStatus) {

		HotelRoomVO hotelRoomVO = new HotelRoomVO();
		hotelRoomVO.setRoomNo(roomNo);
		hotelRoomVO.setRoomTypeNo(roomTypeNo);
		hotelRoomVO.setPetNo(petNo);
		hotelRoomVO.setRoomStatus(roomStatus);
		dao.insert(hotelRoomVO);

		return hotelRoomVO;

	}

	public HotelRoomVO updateHotelRoom(Integer roomNo, String roomTypeNo, String petNo, Integer roomStatus) {

		HotelRoomVO hotelRoomVO = new HotelRoomVO();
		hotelRoomVO.setRoomNo(roomNo);
		hotelRoomVO.setRoomTypeNo(roomTypeNo);
		hotelRoomVO.setPetNo(petNo);
		hotelRoomVO.setRoomStatus(roomStatus);
		dao.update(hotelRoomVO);

		return hotelRoomVO;
	}

	public void deleteHotelRoom(Integer roomNo) {
		dao.delete(roomNo);
	}

	public HotelRoomVO getOneHotelRoom(Integer roomNo) {
		return dao.findByPrimaryKey(roomNo);
	}
	
	public void cancelStatus(Integer roomNo) {
		dao.cancel(roomNo);
	}
	
	public void statusChange(Integer roomNo) {
		dao.confirm(roomNo);
	}

	public List<HotelRoomVO> getAll() {
		return dao.getAll();
	}
	public List<HotelRoomVO> getEmptyRoom(String roomTypeNo) {
		return dao.getEmptyRoom(roomTypeNo);
	}
}

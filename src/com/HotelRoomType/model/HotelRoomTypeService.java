package com.HotelRoomType.model;

import java.util.List;
import java.util.Set;

import com.HotelRoom.model.HotelRoomVO;

public class HotelRoomTypeService {

	private HotelRoomTypeDAO_interface dao;

	public HotelRoomTypeService() {
		dao = new HotelRoomTypeDAO();
	}

	public HotelRoomTypeVO addHotelRoomType(String roomTypeNo, String roomTypeName, String roomTypeDescribe,
			String roomTypeSpace, Integer roomTypePrice, String roomTypeService, Integer roomTypeStatus,
			byte[] roomTypePic) {

		HotelRoomTypeVO hotelRoomTypeVO = new HotelRoomTypeVO();

		hotelRoomTypeVO.setRoomTypeNo(roomTypeNo);
		hotelRoomTypeVO.setRoomTypeName(roomTypeName);
		hotelRoomTypeVO.setRoomTypeDescribe(roomTypeDescribe);
		hotelRoomTypeVO.setRoomTypeSpace(roomTypeSpace);
		hotelRoomTypeVO.setRoomTypePrice(roomTypePrice);
		hotelRoomTypeVO.setRoomTypeService(roomTypeService);
		hotelRoomTypeVO.setRoomTypeStatus(roomTypeStatus);
		hotelRoomTypeVO.setRoomTypePic(roomTypePic);
		dao.insert(hotelRoomTypeVO);

		return hotelRoomTypeVO;

	}

	public HotelRoomTypeVO updateHotelRoomType(String roomTypeNo, String roomTypeName, String roomTypeDescribe,
			String roomTypeSpace, Integer roomTypePrice, String roomTypeService, Integer roomTypeStatus,
			byte[] roomTypePic) {

		HotelRoomTypeVO hotelRoomTypeVO = new HotelRoomTypeVO();

		hotelRoomTypeVO.setRoomTypeNo(roomTypeNo);
		hotelRoomTypeVO.setRoomTypeName(roomTypeName);
		hotelRoomTypeVO.setRoomTypeDescribe(roomTypeDescribe);
		hotelRoomTypeVO.setRoomTypeSpace(roomTypeSpace);
		hotelRoomTypeVO.setRoomTypePrice(roomTypePrice);
		hotelRoomTypeVO.setRoomTypeService(roomTypeService);
		hotelRoomTypeVO.setRoomTypeStatus(roomTypeStatus);
		hotelRoomTypeVO.setRoomTypePic(roomTypePic);
		dao.update(hotelRoomTypeVO);

		return hotelRoomTypeVO;

	}

	public void deleteHotelRoomType(String roomTypeNo) {
		dao.delete(roomTypeNo);
	}

	public HotelRoomTypeVO getOneHotelRoomType(String roomTypeNo) {
		return dao.findByPrimaryKey(roomTypeNo);
	}

	public List<HotelRoomTypeVO> getAll() {
		return dao.getAll();
	}

	public Set<HotelRoomVO> getRoomsBy_RTID(String roomTypeNo) {
		return dao.getRoomsBy_RTID(roomTypeNo);
	}

}

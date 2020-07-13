package com.HotelRoom.model;

import java.util.List;

public class TestHotelRoomDAO_JDBC {

	public static void main(String[] args) {
		HotelRoomDAO dao = new HotelRoomDAO();

		// 新增
		HotelRoomVO hotelRoomVO1 = new HotelRoomVO();
		hotelRoomVO1.setRoomNo(new Integer(104));
		hotelRoomVO1.setRoomTypeNo("RT001");
		hotelRoomVO1.setPetNo("P0005");
		hotelRoomVO1.setRoomStatus(new Integer(1));
		dao.insert(hotelRoomVO1);
		System.out.println("新增成功!");

		// 修改
		HotelRoomVO hotelRoomVO2 = new HotelRoomVO();
		hotelRoomVO2.setRoomNo(102);
		hotelRoomVO2.setRoomTypeNo("RT001");
		hotelRoomVO2.setPetNo("P0009");
		hotelRoomVO2.setRoomStatus(0);
		dao.update(hotelRoomVO2);
		System.out.println("修改成功!");

		// 刪除
		dao.delete(101);
		System.out.println("刪除成功!");

		// 查詢(單筆)
		HotelRoomVO hotelRoomVO3 = dao.findByPrimaryKey(102);
		System.out.println("房間編號:" + hotelRoomVO3.getRoomNo());
		System.out.println("房型編號 :" + hotelRoomVO3.getRoomTypeNo());
		System.out.println("寵物編號 :" + hotelRoomVO3.getPetNo());
		System.out.println("房間狀態 :" + hotelRoomVO3.getRoomStatus());
		System.out.println("-----------------------------------------");

		// 查詢(全部)
		List<HotelRoomVO> list = dao.getAll();

		for (HotelRoomVO hotelRoomAll : list) {
			System.out.println("房間編號:" + hotelRoomAll.getRoomNo());
			System.out.println("房型編號 :" + hotelRoomAll.getRoomTypeNo());
			System.out.println("寵物編號 :" + hotelRoomAll.getPetNo());
			System.out.println("房間狀態 :" + hotelRoomAll.getRoomStatus());
			System.out.println("-----------------------------------------");
		}

		// 變更房間狀態(已存在)
		dao.confirm(101);
		System.out.println("狀態變更成功");

		// 變更房間狀態(已移除)
		dao.cancel(101);
		System.out.println("狀態變更成功");

	}

}

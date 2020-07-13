package com.HotelRoomType.model;

import java.util.List;
import java.util.Set;

import com.HotelRoom.model.HotelRoomVO;

public class TestHotelRoomTypeDAO_JDBC {

	public static void main(String[] args) {
		HotelRoomTypeDAO dao = new HotelRoomTypeDAO();

//		// 新增
//		HotelRoomTypeVO hotelRoomTypeVO1 = new HotelRoomTypeVO();
//		hotelRoomTypeVO1.setRoomTypeName("淑女房");
//		hotelRoomTypeVO1.setRoomTypeDescribe("夢幻房型，粉紅天地，適合高貴貓狗來享受~");
//		hotelRoomTypeVO1.setRoomTypeSpace("200cm*200cm*300cm");
//		hotelRoomTypeVO1.setRoomTypePrice(1000);
//		hotelRoomTypeVO1.setRoomTypeService("寵物美容");
//		hotelRoomTypeVO1.setRoomTypeStatus(1);
//
//		dao.insert(hotelRoomTypeVO1);
//
//		System.out.println("新增成功!");
//
//		// 修改
//		HotelRoomTypeVO hotelRoomTypeVO2 = new HotelRoomTypeVO();
//
//		hotelRoomTypeVO2.setRoomTypeNo("RT001");
//		hotelRoomTypeVO2.setRoomTypeName("淑女房");
//		hotelRoomTypeVO2.setRoomTypeDescribe("夢幻房型，粉紅天地，適合高貴貓狗來享受~");
//		hotelRoomTypeVO2.setRoomTypeSpace("200cm*200cm*300cm");
//		hotelRoomTypeVO2.setRoomTypePrice(1000);
//		hotelRoomTypeVO2.setRoomTypeService("寵物美容");
//		hotelRoomTypeVO2.setRoomTypeStatus(1);
//
//		dao.update(hotelRoomTypeVO2);
//
//		System.out.println("修改成功!");
//
//		// 刪除 【有與訂單關聯，要刪除連動的表格欄位】
//		dao.delete("RT003");
//
//		System.out.println("刪除成功!");
//
//		// 查詢(單筆)
//		HotelRoomTypeVO hotelRoomTypeVO3 = dao.findByPrimaryKey("RT001");
//		System.out.println("房型編號:" + hotelRoomTypeVO3.getRoomTypeNo());
//		System.out.println("房型名稱:" + hotelRoomTypeVO3.getRoomTypeName());
//		System.out.println("房型簡介:" + hotelRoomTypeVO3.getRoomTypeDescribe());
//		System.out.println("房型空間:" + hotelRoomTypeVO3.getRoomTypeSpace());
//		System.out.println("房型價格:" + hotelRoomTypeVO3.getRoomTypePrice());
//		System.out.println("房型服務:" + hotelRoomTypeVO3.getRoomTypeService());
//		System.out.println("房型狀態:" + hotelRoomTypeVO3.getRoomTypeStatus());
//		System.out.println("房型圖片:" + hotelRoomTypeVO3.getRoomTypePic());
//		System.out.println("--------------------------------------------------");
//
//		// 查詢(全部)
//		List<HotelRoomTypeVO> list = dao.getAll();
//
//		for (HotelRoomTypeVO HotelRoomTypeAll : list) {
//			System.out.println("房型編號:" + HotelRoomTypeAll.getRoomTypeNo());
//			System.out.println("房型名稱:" + HotelRoomTypeAll.getRoomTypeName());
//			System.out.println("房型簡介:" + HotelRoomTypeAll.getRoomTypeDescribe());
//			System.out.println("房型空間:" + HotelRoomTypeAll.getRoomTypeSpace());
//			System.out.println("房型價格:" + HotelRoomTypeAll.getRoomTypePrice());
//			System.out.println("房型服務:" + HotelRoomTypeAll.getRoomTypeService());
//			System.out.println("房型狀態:" + HotelRoomTypeAll.getRoomTypeStatus());
//			System.out.println("房型圖片:" + HotelRoomTypeAll.getRoomTypePic());
//			System.out.println("--------------------------------------------------");
//		}

		// 查詢該房型所對應的所有房間
		Set<HotelRoomVO> set = dao.getRoomsBy_RTID("RT002");

		for (HotelRoomVO hotelRoomAll : set) {
			System.out.println("房間編號:" + hotelRoomAll.getRoomNo());
			System.out.println("房型編號 :" + hotelRoomAll.getRoomTypeNo());
			System.out.println("寵物編號 :" + hotelRoomAll.getPetNo());
			System.out.println("房間狀態 :" + hotelRoomAll.getRoomStatus());
			System.out.println("-----------------------------------------");
		}

	}

}

package com.HotelOrder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestHotelOrderDAO_JDBC {

	public static void main(String[] args) {

		// 補充
		// String S = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Timestamp);
		// Format f = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		// String s = f.format();

		GregorianCalendar time3 = new GregorianCalendar();
		// 無參數時獲得一個GregorianCalendar的物件
		// 再用getTimeInMillis()回傳一個long毫秒
		// 此long毫秒即為"1970/1/1到當下時間的毫秒數"

		Calendar time1 = new GregorianCalendar(2021, 0, 15, 12, 0, 0);
		// 有參數時獲得一個手動輸入日期的GregorianCalendar的物件
		// 再用getTimeInMillis()回傳一個long毫秒
		// 此long毫秒即為"1970/1/1到手動輸入時間的毫秒數"
		Calendar time2 = new GregorianCalendar(2022, 0, 15, 12, 0, 0);

		HotelOrderDAO dao = new HotelOrderDAO();

		// 新增
//		HotelOrderVO hotelOrderVO1 = new HotelOrderVO();
//		hotelOrderVO1.setMemNo("M0002");
//		hotelOrderVO1.setRoomTypeNo("RT002");
//		hotelOrderVO1.setRoomNo(101);
//		hotelOrderVO1.setRoomTypePrice(999);
//		hotelOrderVO1.setOrderDate(new Timestamp(time3.getTimeInMillis()));    // java.sql.Timestamp 參數要放入long -->可轉型為SQL時間送入資料庫
//		hotelOrderVO1.setCheckInDate(new Timestamp(time1.getTimeInMillis()));  // java.sql.Timestamp 參數要放入long -->可轉型為SQL時間送入資料庫
//		hotelOrderVO1.setCheckOutDate(new Timestamp(time2.getTimeInMillis())); // java.sql.Timestamp 參數要放入long -->可轉型為SQL時間送入資料庫
//		hotelOrderVO1.setTotalPrice("3000");
//		hotelOrderVO1.setHotelOrderStatus(1);
//		dao.insert(hotelOrderVO1);
//		System.out.println("新增成功!");

		// 修改
//		HotelOrderVO hotelOrderVO2 = new HotelOrderVO();
//		hotelOrderVO2.setOrderNo("20200623-000001");
//		hotelOrderVO2.setMemNo("M0005");
//		hotelOrderVO2.setRoomTypeNo("RT001");
//		hotelOrderVO2.setRoomNo(101);
//		hotelOrderVO2.setRoomTypePrice(1111);
//		hotelOrderVO2.setCheckInDate(new Timestamp(time1.getTimeInMillis()));
//		hotelOrderVO2.setCheckOutDate(new Timestamp(time2.getTimeInMillis()));
//		hotelOrderVO2.setTotalPrice("5111");
//		hotelOrderVO2.setHotelOrderStatus(0);
//		dao.update(hotelOrderVO2);
//		System.out.println("修改成功!");

		// 刪除
//		dao.delete("20200623-000001");
//		System.out.println("刪除成功!");

		// 查詢(單筆)
//		HotelOrderVO hotelOrderVO3 = dao.findByPrimaryKey("20200623-000002");
//		System.out.println("訂單編號 :" + hotelOrderVO3.getOrderNo());
//		System.out.println("會員編號 :" + hotelOrderVO3.getMemNo());
//		System.out.println("房型編號 :" + hotelOrderVO3.getRoomTypeNo());
//		System.out.println("房間編號 :" + hotelOrderVO3.getRoomNo());
//		System.out.println("房型價格 :" + hotelOrderVO3.getRoomTypePrice());
//		System.out.println("訂單日期 :" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(hotelOrderVO3.getOrderDate()));
//		System.out.println("入住日期 :" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(hotelOrderVO3.getCheckInDate()));
//		System.out.println("退房日期 :" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(hotelOrderVO3.getCheckOutDate()));
//		System.out.println("訂單總價 :" + hotelOrderVO3.getTotalPrice());
//		System.out.println("訂單狀態 :" + hotelOrderVO3.getHotelOrderStatus());
//		System.out.println("---------------------------");

		// 查詢(全部)
//		List<HotelOrderVO> list = dao.getAll();
//
//		for (HotelOrderVO hotelOrderAll : list) {
//			System.out.println("訂單編號 :" + hotelOrderAll.getOrderNo());
//			System.out.println("會員編號 :" + hotelOrderAll.getMemNo());
//			System.out.println("房型編號 :" + hotelOrderAll.getRoomTypeNo());
//			System.out.println("房間編號 :" + hotelOrderAll.getRoomNo());
//			System.out.println("房型價格 :" + hotelOrderAll.getRoomTypePrice());
//			System.out.println("訂單日期 :" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(hotelOrderAll.getOrderDate()));
//			System.out.println("入住日期 :" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(hotelOrderAll.getCheckInDate()));
//			System.out.println("退房日期 :" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(hotelOrderAll.getCheckOutDate()));
//			System.out.println("訂單總價 :" + hotelOrderAll.getTotalPrice());
//			System.out.println("訂單狀態 :" + hotelOrderAll.getHotelOrderStatus());
//			System.out.println("---------------------------");
//		}

//		List<HotelOrderVO> list = dao.getOneMemOrder("M0002");
//		for (HotelOrderVO hotelOrderAll : list) {
//			System.out.println("訂單編號 :" + hotelOrderAll.getOrderNo());
//			System.out.println("會員編號 :" + hotelOrderAll.getMemNo());
//			System.out.println("房型編號 :" + hotelOrderAll.getRoomTypeNo());
//			System.out.println("房間編號 :" + hotelOrderAll.getRoomNo());
//			System.out.println("房型價格 :" + hotelOrderAll.getRoomTypePrice());
//			System.out.println("訂單日期 :" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(hotelOrderAll.getOrderDate()));
//			System.out.println("入住日期 :" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(hotelOrderAll.getCheckInDate()));
//			System.out.println("退房日期 :" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(hotelOrderAll.getCheckOutDate()));
//			System.out.println("訂單總價 :" + hotelOrderAll.getTotalPrice());
//			System.out.println("---------------------------");
//		}

		// 修改狀態(待確認 -> 已確認)
//		HotelOrderVO hotelOrderVO3 = new HotelOrderVO();
//		hotelOrderVO3.setHotelOrderStatus(1);
//		hotelOrderVO3.setOrderNo("20200629-000005");
//		
//		dao.comfirm(hotelOrderVO3);
//		System.out.println("狀態修改成功!");

		dao.cancel("20200630-000001");
		System.out.println("訂單取消成功!");

	}
}

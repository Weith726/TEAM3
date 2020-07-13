package com.HotelOrder.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class HotelOrderService {

	private HotelOrderDAO_interface dao;

	public HotelOrderService() {
		dao = new HotelOrderDAO();
	}

	public HotelOrderVO addHotelOrder(String memNo,String petNo, String roomTypeNo, Timestamp orderDate, Integer roomNo,
			Integer roomTypePrice, Timestamp checkInDate, Timestamp checkOutDate, String totalPrice,
			Integer hotelOrderStatus) {

		HotelOrderVO hotelOrderVO = new HotelOrderVO();

		hotelOrderVO.setMemNo(memNo);
		hotelOrderVO.setPetNo(petNo);
		hotelOrderVO.setRoomTypeNo(roomTypeNo);
		hotelOrderVO.setOrderDate(orderDate);
		hotelOrderVO.setRoomNo(roomNo);
		hotelOrderVO.setRoomTypePrice(roomTypePrice);
		hotelOrderVO.setCheckInDate(checkInDate);
		hotelOrderVO.setCheckOutDate(checkOutDate);
		hotelOrderVO.setTotalPrice(totalPrice);
		hotelOrderVO.setHotelOrderStatus(hotelOrderStatus);
		dao.insert(hotelOrderVO);

		return hotelOrderVO;

	}

	public HotelOrderVO updateHotelOrder(String orderNo, String memNo,String petNo,String roomTypeNo, Timestamp orderDate,
			Integer roomNo, Integer roomTypePrice, Timestamp checkInDate, Timestamp checkOutDate, String totalPrice,
			Integer hotelOrderStatus) {

		HotelOrderVO hotelOrderVO = new HotelOrderVO();

		hotelOrderVO.setOrderNo(orderNo);
		hotelOrderVO.setMemNo(memNo);
		hotelOrderVO.setPetNo(petNo);
		hotelOrderVO.setRoomTypeNo(roomTypeNo);
		hotelOrderVO.setOrderDate(orderDate);
		hotelOrderVO.setRoomNo(roomNo);
		hotelOrderVO.setRoomTypePrice(roomTypePrice);
		hotelOrderVO.setCheckInDate(checkInDate);
		hotelOrderVO.setCheckOutDate(checkOutDate);
		hotelOrderVO.setTotalPrice(totalPrice);
		hotelOrderVO.setHotelOrderStatus(hotelOrderStatus);
		dao.update(hotelOrderVO);

		return hotelOrderVO;
	}

	public void deleteHotelOrder(String orderNo) {
		dao.delete(orderNo);
	}

	public HotelOrderVO getOneHotelOrder(String orderNo) {
		return dao.findByPrimaryKey(orderNo);
	}

	public List<HotelOrderVO> getAll() {
		return dao.getAll();
	}

	public List<HotelOrderVO> getFullCalendarInfo() {
		return dao.getFullCalendarInfo();
	}

	public List<HotelOrderVO> getOneMemOrder(String memNo) {
		return dao.getOneMemOrder(memNo);
	}

	public void cancelStatus(String orderNo) {
		dao.cancel(orderNo);
	}

	public HotelOrderVO statusChange(Integer hotelOrderStatus, String orderNo) {
		HotelOrderVO hotelOrderVO = new HotelOrderVO();

		hotelOrderVO.setOrderNo(orderNo);
		hotelOrderVO.setHotelOrderStatus(hotelOrderStatus);
		dao.confirm(hotelOrderVO);

		return hotelOrderVO;
	}
	//JavaMail
	public void sendMail(String to, String subject, String messageText) {
		dao.sendMail(to, subject, messageText);
	}
}

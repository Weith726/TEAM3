package com.HotelOrder.model;

import java.util.List;

public interface HotelOrderDAO_interface {
	public void insert(HotelOrderVO hotelOrderVO);

	public void update(HotelOrderVO hotelOrderVO);

	public void delete(String orderNo);
	
	public void confirm(HotelOrderVO hotelOrderVO);
	
	public void cancel(String orderNo);

	public HotelOrderVO findByPrimaryKey(String orderNo);

	public List<HotelOrderVO> getAll();
	
	public List<HotelOrderVO> getFullCalendarInfo();
	
	public List<HotelOrderVO> getOneMemOrder(String memNo);
	
	public void sendMail(String to, String subject, String messageText);
}

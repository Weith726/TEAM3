
package com.HotelRoomType.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.HotelRoomType.model.HotelRoomTypeService;
import com.HotelRoomType.model.HotelRoomTypeVO;



public class HotelRoomTypePic extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		ServletOutputStream out = res.getOutputStream();
		res.setContentType("image/gif");
		String roomTypeNo = req.getParameter("roomTypeNo");
//		HttpSession session = req.getSession();
		
		if(req.getAttribute(roomTypeNo)==null) {
			HotelRoomTypeService hotelRoomTypeSvc = new HotelRoomTypeService();
			HotelRoomTypeVO hotelRoomTypeVO = hotelRoomTypeSvc.getOneHotelRoomType(roomTypeNo);
			req.setAttribute(roomTypeNo,hotelRoomTypeVO.getRoomTypePic());
		}
		out.write((byte[])req.getAttribute(roomTypeNo));

	}

}

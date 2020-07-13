
package com.HotelRoom.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import com.MemberPet.model.*;

public class HotelRoomPic extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		ServletOutputStream out = res.getOutputStream();
		res.setContentType("image/gif");
		String petNo = req.getParameter("petNo");
//		HttpSession session = req.getSession();
		System.out.println("123123");
		
		if(req.getAttribute(petNo)==null) {
			MemberPetService memberPetSvc = new MemberPetService();
			MemberPetVO memberPetVO = memberPetSvc.getOneMemberPet(petNo);
			req.setAttribute(petNo,memberPetVO.getPetPic());
		}
		out.write((byte[])req.getAttribute(petNo));

	}

}

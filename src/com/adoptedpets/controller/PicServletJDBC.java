package com.adoptedpets.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.adoptedpets.model.*;


public class PicServletJDBC extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
		
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
	
		try {
			String petNo = req.getParameter("petNo");
			AdoptedPetsService adoptedPetssrv = new AdoptedPetsService();
			AdoptedPetsVO adoptedPetsVO = adoptedPetssrv.getOneAdoptedPets(petNo) ;
			byte[] buf = adoptedPetsVO.getPetPic();
			System.out.println(adoptedPetsVO.getPetPic());
			out.write(buf);
		
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}


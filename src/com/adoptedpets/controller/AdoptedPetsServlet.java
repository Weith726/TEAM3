package com.adoptedpets.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;
import java.util.stream.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.adoptedpets.model.*;
import com.google.gson.*;

@MultipartConfig
public class AdoptedPetsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自listAllAdoptedPets.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("petNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入寵物編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adoptedpets/listAllAdoptedPets.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String petNo = null;
				try {
					petNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("收容寵物不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adoptedpets/listAllAdoptedPets.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				AdoptedPetsService adoptedPetsSvc = new AdoptedPetsService();
				AdoptedPetsVO adoptedPetsVO = adoptedPetsSvc.getOneAdoptedPets(petNo);
				if (adoptedPetsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adoptedpets/listAllAdoptedPets.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("adoptedpetsVO", adoptedPetsVO); // 資料庫取出的adoptedPetsVO物件,存入req
				String url = "/back-end/adopt/adoptedpets/listOneAdoptedPets.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAdoptedPets.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adoptedpets/listAllAdoptedPets.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOnePet_Frontend".equals(action)) { // 來自/frontPets/listAllPets.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("petNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入寵物編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/adopt/adoptedpets/listAllPets.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String petNo = null;
				try {
					petNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("收容寵物不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/adopt/adoptedpets/listAllPets.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				AdoptedPetsService adoptedPetsSvc = new AdoptedPetsService();
				AdoptedPetsVO adoptedPetsVO = adoptedPetsSvc.getOneAdoptedPets(petNo);
				if (adoptedPetsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/adopt/adoptedpets/listAllPets.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("adoptedpetsVO", adoptedPetsVO); // 資料庫取出的adoptedPetsVO物件,存入req
				String url = "/front-end/adopt/adoptedpets/listOnePet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAdoptedPets.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/adopt/adoptedpets/listAllPets.jsp");
				failureView.forward(req, res);
			}
		}

		if ("listAdoptedPets_ByCompositeQuery".equals(action)) { // 來自/frontPets/listAllPets.jsp的請求

			System.out.println("1");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				// Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map", map1);
					map = map1;
				}
				/*************************** 2.開始複合查詢 ***************************************/
				AdoptedPetsService adoptedPetsSvc = new AdoptedPetsService();
				List<AdoptedPetsVO> list = adoptedPetsSvc.getAll(map);
				Gson gson = new Gson();
				String jsonstring = gson.toJson(list);

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");

				PrintWriter out = res.getWriter();
				out.print(jsonstring);
				out.flush();

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/adopt/adoptedpets/listAllPets.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String petNo = new String(req.getParameter("petNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				AdoptedPetsService adoptedpetsSvc = new AdoptedPetsService();
				AdoptedPetsVO adoptedpetsVO = adoptedpetsSvc.getOneAdoptedPets(petNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("adoptedpetsVO", adoptedpetsVO); // 資料庫取出的adoptedpetsVO物件,存入req
				String url = "/back-end/adopt/adoptedpets/update_AdoptedPets_Input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_AdoptedPets_Input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adoptedpets/listAllAdoptedPets.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOneinteractionforAdd_Frontend".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String petNo = new String(req.getParameter("petNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				AdoptedPetsService adoptedpetsSvc = new AdoptedPetsService();
				AdoptedPetsVO adoptedpetsVO = adoptedpetsSvc.getOneAdoptedPets(petNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				HttpSession session = req.getSession();
				session.setAttribute("adoptedpetsVO", adoptedpetsVO);
				req.setAttribute("adoptedpetsVO", adoptedpetsVO); // 資料庫取出的adoptedpetsVO物件,存入req
				String url = "/front-end/adopt/interaction/addInteraction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_AdoptedPets_Input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/adopt/adoptedpets/listAllPets.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自 update_AdoptedPets_Input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//		
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String petNo = req.getParameter("petNo");
				if (petNo == null || petNo.trim().length() == 0) {
					errorMsgs.add("寵物編號: 請勿空白");
				}

				String adopterNo = req.getParameter("adopterNo");

				String shelterNo = req.getParameter("shelterNo");

				String petBreed = req.getParameter("petBreed");
				if (petBreed == null || petBreed.trim().length() == 0) {
					errorMsgs.add("寵物品種: 請勿空白");
				}

				java.sql.Date adoptedDate = null;
				if (!(req.getParameter("adoptedDate").isEmpty())) {
					adoptedDate = java.sql.Date.valueOf(req.getParameter("adoptedDate"));
				}

				byte[] petPic = null;
				if ((req.getPart("petPic").getSize()) == 0) {
					AdoptedPetsService petPicSrv = new AdoptedPetsService();
					AdoptedPetsVO petPicVO = petPicSrv.getOneAdoptedPets(petNo);
					petPic = petPicVO.getPetPic();
				} else {
					Part petPicPart = req.getPart("petPic");
					InputStream petPicIn = petPicPart.getInputStream();
					petPic = new byte[petPicIn.available()];
					petPicIn.read(petPic);
					petPicIn.close();
				}

				java.sql.Date adoptDate = null;
				if (!(req.getParameter("adoptDate").isEmpty())) {
					adoptDate = java.sql.Date.valueOf(req.getParameter("adoptDate"));
				}

				java.sql.Date interviewDate = null;
				if (!(req.getParameter("interviewDate").isEmpty())) {
					interviewDate = java.sql.Date.valueOf(req.getParameter("interviewDate"));
				}

				String interviewInfo = req.getParameter("interviewInfo");

				String petSpecies = req.getParameter("petSpecies");

				String petGender = req.getParameter("petGender");

				Integer adoptStatus = new Integer(req.getParameter("adoptStatus"));

				AdoptedPetsVO adoptedPetsVO = new AdoptedPetsVO();
				adoptedPetsVO.setPetNo(petNo);
				adoptedPetsVO.setAdopterNo(adopterNo);
				adoptedPetsVO.setShelterNo(shelterNo);
				adoptedPetsVO.setPetBreed(petBreed);
				adoptedPetsVO.setAdoptedDate(adoptedDate);
				adoptedPetsVO.setPetPic(petPic);
				adoptedPetsVO.setAdoptDate(adoptDate);
				adoptedPetsVO.setInterviewDate(interviewDate);
				adoptedPetsVO.setInterviewInfo(interviewInfo);
				adoptedPetsVO.setPetSpecies(petSpecies);
				adoptedPetsVO.setPetGender(petGender);
				adoptedPetsVO.setAdoptStatus(adoptStatus);

//				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adoptedpetsVO", adoptedPetsVO); // 含有輸入格式錯誤的adoptedPetsVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adopt/adoptedpets/update_AdoptedPets_Input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
//				
				/*************************** 2.開始修改資料 *****************************************/
				AdoptedPetsService adoptedPetsSvc = new AdoptedPetsService();
				adoptedPetsVO = adoptedPetsSvc.updateAdoptedPets(petNo, adopterNo, shelterNo, petBreed, adoptedDate,
						petPic, adoptDate, interviewDate, interviewInfo, petSpecies, petGender, adoptStatus);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("adoptedpetsVO", adoptedPetsVO); // 資料庫update成功後,正確的的adoptedPetsVO物件,存入req
				String url = "/back-end/adopt/adoptedpets/listOneAdoptedPets.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAdoptedPets.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adoptedpets/update_AdoptedPets_Input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自 update_AdoptedPets_Input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//		
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String adopterNo = req.getParameter("adopterNo");

				String shelterNo = req.getParameter("shelterNo");

				String petBreed = req.getParameter("petBreed");
				if (petBreed == null || petBreed.trim().length() == 0) {
					errorMsgs.add("寵物品種: 請勿空白");
				}

				java.sql.Date adoptedDate = null;
				if (!(req.getParameter("adoptedDate").isEmpty())) {
					adoptedDate = java.sql.Date.valueOf(req.getParameter("adoptedDate"));
				}

				byte[] petPic = null;
				Part petPicPart = req.getPart("petPic");
				InputStream petPicIn = petPicPart.getInputStream();
				petPic = new byte[petPicIn.available()];
				petPicIn.read(petPic);
				petPicIn.close();

				java.sql.Date adoptDate = null;
				if (!(req.getParameter("adoptDate").isEmpty())) {
					adoptDate = java.sql.Date.valueOf(req.getParameter("adoptDate"));
				}

				java.sql.Date interviewDate = null;
				if (!(req.getParameter("interviewDate").isEmpty())) {
					interviewDate = java.sql.Date.valueOf(req.getParameter("interviewDate"));
				}

				String interviewInfo = req.getParameter("interviewInfo");

				String petSpecies = req.getParameter("petSpecies");

				String petGender = req.getParameter("petGender");

				Integer adoptStatus = new Integer(req.getParameter("adoptStatus"));

				AdoptedPetsVO adoptedPetsVO = new AdoptedPetsVO();
				adoptedPetsVO.setAdopterNo(adopterNo);
				adoptedPetsVO.setShelterNo(shelterNo);
				adoptedPetsVO.setPetBreed(petBreed);
				adoptedPetsVO.setAdoptedDate(adoptedDate);
				adoptedPetsVO.setPetPic(petPic);
				adoptedPetsVO.setAdoptDate(adoptDate);
				adoptedPetsVO.setInterviewDate(interviewDate);
				adoptedPetsVO.setInterviewInfo(interviewInfo);
				adoptedPetsVO.setPetSpecies(petSpecies);
				adoptedPetsVO.setPetGender(petGender);
				adoptedPetsVO.setAdoptStatus(adoptStatus);

//				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adoptedpetsVO", adoptedPetsVO); // 含有輸入格式錯誤的adoptedPetsVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adoptedpets/add_AdoptedPets.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
//				
				/*************************** 2.開始修改資料 *****************************************/
				AdoptedPetsService adoptedPetsSvc = new AdoptedPetsService();
				adoptedPetsVO = adoptedPetsSvc.addAdoptedPets(adopterNo, shelterNo, petBreed, adoptedDate, petPic,
						adoptDate, interviewDate, interviewInfo, petSpecies, petGender, adoptStatus);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = "/back-end/adopt/adoptedpets/listAllAdoptedPets.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後,轉交listAllAdoptedPets.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adoptedpets/add_AdoptedPets.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllAdoptedPets.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String petNo = req.getParameter("petNo");
				if (petNo == null || petNo.trim().length() == 0) {
					errorMsgs.add("寵物編號: 請勿空白");
				}

				/*************************** 2.開始刪除資料 ***************************************/
				AdoptedPetsService adoptedPetsSvc = new AdoptedPetsService();
				adoptedPetsSvc.deleteAdoptedPets(petNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/adopt/adoptedpets/listAllAdoptedPets.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adoptedpets/listAllAdoptedPets.jsp");
				failureView.forward(req, res);
			}
		}

	}
}

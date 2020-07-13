package com.petshelter.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.adoptedpets.model.AdoptedPetsService;
import com.adoptedpets.model.AdoptedPetsVO;
import com.adopter.model.*;
import com.petshelter.model.*;

public class PetShelterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自listAllAdopter.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("shelterNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入收容住所編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/petshelter/listAllPetShelter.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String shelterNo = null;
				try {
					shelterNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("收容住所不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/petshelter/listAllPetShelter.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				PetShelterService petShelterSvc = new PetShelterService();
				PetShelterVO petShelterVO = petShelterSvc.getOnePetShelter(shelterNo);
				if (shelterNo == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/petshelter/listAllPetShelter.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("petShelterVO", petShelterVO); // 資料庫取出的petShelterVO物件,存入req
				String url = "/back-end/adopt/petshelter/listOnePetShelter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllPetShelter.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/petshelter/listAllPetShelter.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllPetShelter.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String shelterNo = req.getParameter("shelterNo");
				;

				/*************************** 2.開始查詢資料 ****************************************/
				PetShelterService petShelterSvc = new PetShelterService();
				PetShelterVO petShelterVO = petShelterSvc.getOnePetShelter(shelterNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("petShelterVO", petShelterVO); // 資料庫取出的petShelterVO物件,存入req
				String url = "/back-end/adopt/petshelter/update_PetShelter_Input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 update_PetShelter_Input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/petshelter/listAllPetShelter.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自 update_PetShelter_Input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//		
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String shelterNo = req.getParameter("shelterNo");
				;
				if (shelterNo == null || shelterNo.trim().length() == 0) {
					errorMsgs.add("收容住所編號: 請勿空白");
				}

				java.sql.Date cleanDate = null;
				if (!(req.getParameter("cleanDate").isEmpty())) {
					cleanDate = java.sql.Date.valueOf(req.getParameter("cleanDate"));
				}

				Integer shelterStatus = new Integer(req.getParameter("shelterStatus"));

				PetShelterVO petShelterVO = new PetShelterVO();
				petShelterVO.setShelterNo(shelterNo);
				petShelterVO.setCleanDate(cleanDate);
				petShelterVO.setShelterStatus(shelterStatus);

//				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("petShelterVO", petShelterVO); // 含有輸入格式錯誤的petShelterVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/petshelter/update_PetShelter_Input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
//				
				/*************************** 2.開始修改資料 *****************************************/
				PetShelterService petShelterSvc = new PetShelterService();
				petShelterVO = petShelterSvc.updatePetShetler(shelterNo, cleanDate, shelterStatus);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("petShelterVO", petShelterVO); // 資料庫update成功後,正確的的petShelterVO物件,存入req
				String url = "/back-end/adopt/petshelter/listOnePetShelter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交/back-end/adopt/petshelter/listOnePetShelter.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/petshelter/update_PetShelter_Input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自 add_PetShelter.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//	
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				java.sql.Date cleanDate = null;
				if (!(req.getParameter("cleanDate").isEmpty())) {
					cleanDate = java.sql.Date.valueOf(req.getParameter("cleanDate"));
				}

				Integer shelterStatus = new Integer(req.getParameter("shelterStatus"));

				PetShelterVO petShelterVO = new PetShelterVO();
				petShelterVO.setCleanDate(cleanDate);
				petShelterVO.setShelterStatus(shelterStatus);

//			// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("petShelterVO", petShelterVO); // 含有輸入格式錯誤的petShelterVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/petshelter/add_PetShelter.jsp");
					failureView.forward(req, res);
					return; // 程式中斷 // 程式中斷
				}
//			
				/*************************** 2.開始修改資料 *****************************************/
				PetShelterService petShelterSvc = new PetShelterService();
				petShelterVO = petShelterSvc.addPetShetler(cleanDate, shelterStatus);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = "/back-end/adopt/petshelter/listAllPetShelter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後,轉交/adoptedpets/listAllPetShelter.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/petshelter/add_PetShelter.jsp");
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
				String shelterNo = req.getParameter("shelterNo");
				;
				if (shelterNo == null || shelterNo.trim().length() == 0) {
					errorMsgs.add("收容住所編號: 請勿空白");
				}

				/*************************** 2.開始刪除資料 ***************************************/
				PetShelterService petShelterSvc = new PetShelterService();
				petShelterSvc.deletePetShelter(shelterNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/adopt/petshelter/listAllPetShelter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後,轉交/adoptedpets/listAllPetShelter.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/petshelter/listAllPetShelter.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
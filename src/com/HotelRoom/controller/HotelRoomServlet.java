package com.HotelRoom.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HotelRoom.model.HotelRoomService;
import com.HotelRoom.model.HotelRoomVO;

public class HotelRoomServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer roomNo = null;
				try {
					roomNo = new Integer(req.getParameter("roomNo"));
					System.out.println(roomNo);
				} catch (NumberFormatException e) {
					errorMsgs.add("房間編號請填數字.");
				}

				/*************************** 2.開始查詢資料 *****************************************/
				HotelRoomService hotelRoomSvc = new HotelRoomService();
				HotelRoomVO hotelRoomVO = hotelRoomSvc.getOneHotelRoom(roomNo);
				if (hotelRoomVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/Hotel/HotelRoom/listAllHotelRoom.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("hotelRoomVO", hotelRoomVO);// 資料庫取出的hotelRoomVO物件,存入req
				String url = "/back-end/Hotel/HotelRoom/listOneHotelRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelRoom/listAllHotelRoom.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllHotelRoom.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer roomNo = new Integer(req.getParameter("roomNo"));
				System.out.println(roomNo);
				/*************************** 2.開始查詢資料 ****************************************/
				HotelRoomService hotelRoomSvc = new HotelRoomService();
				HotelRoomVO hotelRoomVO = hotelRoomSvc.getOneHotelRoom(roomNo);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("hotelRoomVO", hotelRoomVO);// 資料庫取出的hotelRoomVO物件,存入req
				String url = "/back-end/Hotel/HotelRoom/update_HotelRoom_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_HotelRoom_input.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelRoom/listAllHotelRoom.jsp");
				failureView.forward(req, res);

			}
		}

		if ("update".equals(action)) { // 來自update_HotelRoom_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer roomNo = null;
				try {
					roomNo = new Integer(req.getParameter("roomNo"));
					System.out.println(roomNo);
				} catch (NumberFormatException e) {
					errorMsgs.add("房間編號請勿空白");
				}

				String roomTypeNo = req.getParameter("roomTypeNo");
				System.out.println(roomTypeNo);
				if (roomTypeNo == null || (roomTypeNo.trim()).length() == 0) {
					errorMsgs.add("房型編號請勿空白");
				}

				String petNo = req.getParameter("petNo"); // 寵物編號可以為空值
				System.out.println(petNo);

				Integer roomStatus = null;
				try {
					roomStatus = new Integer(req.getParameter("roomStatus"));
					System.out.println(roomStatus);
				} catch (NumberFormatException e) {
					roomStatus = 0;
					errorMsgs.add("房型狀態請勿空白");
				}

				HotelRoomVO hotelRoomVO = new HotelRoomVO();
				hotelRoomVO.setRoomNo(roomNo);
				hotelRoomVO.setRoomTypeNo(roomTypeNo);
				hotelRoomVO.setPetNo(petNo);
				hotelRoomVO.setRoomStatus(roomStatus);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("hotelRoomVO", hotelRoomVO); // 含有輸入格式錯誤的hotelRoomVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelRoom/update_HotelRoomType_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				HotelRoomService hotelRoomSvc = new HotelRoomService();
				hotelRoomVO = hotelRoomSvc.updateHotelRoom(roomNo, roomTypeNo, petNo, roomStatus);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("hotelRoomVO", hotelRoomVO);
				String url = "/back-end/Hotel/HotelRoom/listOneHotelRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				req.setAttribute("updateOk", "更新成功");
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelRoom/update_HotelRoom_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addHotelRoom.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer roomNo = null;
				try {
					roomNo = new Integer(req.getParameter("roomNo"));
					System.out.println(roomNo);
				} catch (NumberFormatException e) {
					errorMsgs.add("房間編號請勿空白");
				}

				String roomTypeNo = req.getParameter("roomTypeNo");
				System.out.println(roomTypeNo);
				if (roomTypeNo == null || (roomTypeNo.trim()).length() == 0) {
					errorMsgs.add("房型編號請勿空白");
				}

				String petNo = req.getParameter("petNo"); // 寵物編號可以為空值
				System.out.println(petNo);

				Integer roomStatus = null;
				try {
					roomStatus = new Integer(req.getParameter("roomStatus"));
					System.out.println(roomStatus);
				} catch (NumberFormatException e) {
					roomStatus = 0;
					errorMsgs.add("房型狀態:請勿空白");
				}

				HotelRoomVO hotelRoomVO = new HotelRoomVO();
				hotelRoomVO.setRoomNo(roomNo);
				hotelRoomVO.setRoomTypeNo(roomTypeNo);
				hotelRoomVO.setPetNo(petNo);
				hotelRoomVO.setRoomStatus(roomStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("hotelRoomVO", hotelRoomVO); // 含有輸入格式錯誤的hotelRoomVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelRoom/addHotelRoom.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				HotelRoomService hotelRoomSvc = new HotelRoomService();
				hotelRoomVO = hotelRoomSvc.addHotelRoom(roomNo, roomTypeNo, petNo, roomStatus);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/Hotel/HotelRoom/listAllHotelRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 新增成功後轉交listAllHotelRoom.jsp
				req.setAttribute("success", "success");
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelRoom/addHotelRoom.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllHotelRoomType.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer roomNo = new Integer(req.getParameter("roomNo"));
				System.out.println(roomNo);
				/*************************** 2.開始刪除資料 ***************************************/
				HotelRoomService hotelRoomSvc = new HotelRoomService();
				hotelRoomSvc.deleteHotelRoom(roomNo);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/Hotel/HotelRoom/listAllHotelRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				req.setAttribute("fail", "刪除成功");
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelRoom/listAllHotelRoom.jsp");
				failureView.forward(req, res);

			}
		}
		if ("delete".equals(action)) { // 來自listAllHotelRoom.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer roomNo = new Integer(req.getParameter("roomNo"));
				System.out.println(roomNo);
				/*************************** 2.開始刪除資料 ***************************************/
				HotelRoomService hotelRoomSvc = new HotelRoomService();
				hotelRoomSvc.deleteHotelRoom(roomNo);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/Hotel/HotelRoom/listAllHotelRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				req.setAttribute("fail", "刪除成功");
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelRoom/listAllHotelRoom.jsp");
				failureView.forward(req, res);

			}
		}
		if ("delete".equals(action)) { // 來自listAllHotelRoom.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer roomNo = new Integer(req.getParameter("roomNo"));
				System.out.println(roomNo);
				/*************************** 2.開始刪除資料 ***************************************/
				HotelRoomService hotelRoomSvc = new HotelRoomService();
				hotelRoomSvc.deleteHotelRoom(roomNo);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/Hotel/HotelRoom/listAllHotelRoom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				req.setAttribute("fail", "刪除成功");
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelRoom/listAllHotelRoom.jsp");
				failureView.forward(req, res);

			}
		}

		if ("getemptyroom".equals(action)) {
			String roomTypeNo = req.getParameter("roomTypeNo");
			HotelRoomService svc = new HotelRoomService();
			List list = svc.getEmptyRoom(roomTypeNo);
			req.setAttribute("emptyRoom", list);
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/emptyroom.jsp");
			failureView.forward(req, res);
		}
	}

}

package com.HotelOrder.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HotelOrder.model.HotelOrderService;
import com.HotelOrder.model.HotelOrderVO;
import com.HotelRoom.model.HotelRoomService;
import com.HotelRoom.model.HotelRoomVO;
import com.HotelRoomType.model.HotelRoomTypeService;

public class HotelOrderServlet extends HttpServlet {

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
				String orderNo = req.getParameter("orderNo");
				System.out.println(orderNo);
				if (orderNo == null || (orderNo.trim()).length() == 0) {
					errorMsgs.add("●訂單編號:請勿空白");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/Hotel/HotelOrder/listAllHotelOrder.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				HotelOrderService hotelOrderSvc = new HotelOrderService();
				HotelOrderVO hotelOrderVO = hotelOrderSvc.getOneHotelOrder(orderNo);
				if (hotelOrderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/Hotel/HotelOrder/listAllHotelOrder.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("hotelOrderVO", hotelOrderVO); // 資料庫取出的hotelOrderVO物件,存入req
				String url = "/back-end/Hotel/HotelOrder/listOneHotelOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelOrder/listAllHotelOrder.jsp");
				failureView.forward(req, res);

			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllHotelOrder.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String orderNo = req.getParameter("orderNo");
				System.out.println(orderNo);
				/*************************** 2.開始查詢資料 ****************************************/
				HotelOrderService hotelOrderSvc = new HotelOrderService();
				HotelOrderVO hotelOrderVO = hotelOrderSvc.getOneHotelOrder(orderNo);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("hotelOrderVO", hotelOrderVO); // 資料庫取出的hotelOrderVO物件,存入req
				String url = "/back-end/Hotel/HotelOrder/update_HotelOrder_input.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 update_HotelOrder_input.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelOrder/listAllHotelOrder.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自前台訂房(hotelIndex.jsp)的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String orderNo = req.getParameter("orderNo");
				System.out.println(orderNo);
				
				String memNo = req.getParameter("memNo");
				System.out.println(memNo);
				if (memNo == null || (memNo.trim()).length() == 0) {
					errorMsgs.add("●會員編號:請勿空白");
				}

				String petNo = req.getParameter("petNo");
				System.out.println(petNo);
				if (petNo == null || (petNo.trim()).length() == 0) {
					errorMsgs.add("●寵物編號:請勿空白");
				}

				String roomTypeNo = req.getParameter("roomTypeNo");
				System.out.println(roomTypeNo);
				if (roomTypeNo == null || (roomTypeNo.trim()).length() == 0) {
					errorMsgs.add("●房型編號:請勿空白");
				}
				
				//訂單自動生成時間
				GregorianCalendar time = new GregorianCalendar();
				java.sql.Timestamp orderDate = new Timestamp(time.getTimeInMillis());
				System.out.println(orderDate);

				Integer roomNo = null;
				try {
					roomNo = new Integer(req.getParameter("roomNo"));
					System.out.println(roomNo);
				} catch (NumberFormatException e) {
					errorMsgs.add("●房間編號:請填數字");
				}

				Integer roomTypePrice = null;
				try {
					roomTypePrice = new Integer(req.getParameter("roomTypePrice"));
					System.out.println(roomTypePrice);
				} catch (NumberFormatException e) {
					errorMsgs.add("●房型價格:請填數字");
				}

				java.sql.Timestamp checkInDate = null;
				try {
				checkInDate = java.sql.Timestamp.valueOf((req.getParameter("checkInDate") + ":00").trim());
				}catch(IllegalArgumentException e) {
					errorMsgs.add("●入住日期:請勿空白");
				}
				
				//javaMail(入住時間)
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String checkInDateForMail = df.format(checkInDate);
			
				java.sql.Timestamp checkOutDate = null;
				try {
				checkOutDate = java.sql.Timestamp.valueOf(req.getParameter("checkOutDate") + ":00");
				}catch(IllegalArgumentException e) {
					errorMsgs.add("●退房日期:請勿空白");
				}
				
				//javaMail(退房時間)
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String checkOutDateForMail = df.format(checkOutDate);
		
				//javaMail(總入住天數)
				long checkInDateInMilis = checkInDate.getTime();
				long checkOutDateInMilis = checkOutDate.getTime();
				int totalDays = (int) Math.ceil((checkOutDateInMilis - checkInDateInMilis) / (24 * 60 * 60 * 1000));
				System.out.println(totalDays);

				//訂單總價
				String totalPrice = req.getParameter("totalPrice");
				System.out.println(totalPrice);
				if (totalPrice == null || (totalPrice.trim()).length() == 0) {
					errorMsgs.add("●訂單總價:請勿空白");
				}

				//訂單狀態
				Integer hotelOrderStatus = null;
				try {
					hotelOrderStatus = new Integer(req.getParameter("hotelOrderStatus"));
					System.out.println(hotelOrderStatus);
				} catch (NumberFormatException e) {
					errorMsgs.add("●訂單狀態:請填數字");
				}

				// For JavaMail
				/*--------------------------------------------------------------------------------------------*/
				String memName = req.getParameter("memName"); // 會員姓名
				System.out.println(memName);
				String memEmail = req.getParameter("memEmail"); // 會員信箱
				System.out.println(memEmail);
				String subject = "訂房成功通知(萌寵家族)!"; // 信件主旨
				String messageText = "Hi~!" + "『" + memName + "』" + "您好~您預定的寵物旅館日期為" + "『" + checkInDateForMail + "』"
						+ "至" + "『" + checkOutDateForMail + "』" + "共" + "『" + totalDays + "』" + "天，費用總共為" + "『"
						+ totalPrice + "NT』，祝您住房愉快~!"; // 信件內容
				String to = memEmail; // 會員信箱
				/*--------------------------------------------------------------------------------------------*/

				HotelOrderVO hotelOrderVO = new HotelOrderVO();
				hotelOrderVO.setOrderNo(orderNo);
				hotelOrderVO.setMemNo(memNo);
				hotelOrderVO.setRoomTypeNo(roomTypeNo);
				hotelOrderVO.setOrderDate(orderDate);
				hotelOrderVO.setRoomNo(roomNo);
				hotelOrderVO.setRoomTypePrice(roomTypePrice);
				hotelOrderVO.setCheckInDate(checkInDate);
				hotelOrderVO.setCheckOutDate(checkOutDate);
				hotelOrderVO.setTotalPrice(totalPrice);
				hotelOrderVO.setHotelOrderStatus(hotelOrderStatus);

				HotelRoomVO hotelRoomVO = new HotelRoomVO();
				hotelRoomVO.setRoomNo(roomNo);
				hotelRoomVO.setRoomTypeNo(roomTypeNo);
				hotelRoomVO.setPetNo(petNo);
				hotelRoomVO.setRoomStatus(0);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("hotelOrderVO", hotelOrderVO); // 含有輸入格式錯誤的hotelorderVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelOrder/addHotelOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				HotelOrderService hotelOrderSvc = new HotelOrderService();
				hotelOrderVO = hotelOrderSvc.addHotelOrder(memNo, petNo, roomTypeNo, orderDate, roomNo, roomTypePrice,
						checkInDate, checkOutDate, totalPrice, hotelOrderStatus);
				// 新增訂單時要變更房間裡面的欄位(有無寵物)
				HotelRoomService hotelRoomSvc = new HotelRoomService();
				hotelRoomVO = hotelRoomSvc.updateHotelRoom(roomNo, roomTypeNo, petNo, 0);

				/*************************** 2.1 開始寄信 ***************************************/
				hotelOrderSvc.sendMail(to, subject, messageText);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/Hotel/hotel-pay/pay.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 新增成功後轉交listAllHotelOrder.jsp
				req.setAttribute("success", "success");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelOrder/addHotelOrder.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert_back".equals(action)) { // 來自後台新增訂單(addHotelOrder.jsp)的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String orderNo = req.getParameter("orderNo");
				System.out.println(orderNo);
				
				String memNo = req.getParameter("memNo");
				System.out.println(memNo);
				if (memNo == null || (memNo.trim()).length() == 0) {
					errorMsgs.add("●會員編號:請勿空白");
				}
				String petNo = req.getParameter("petNo");
				System.out.println(petNo);
				if (petNo == null || (petNo.trim()).length() == 0) {
					errorMsgs.add("●寵物編號:請勿空白");
				}

				String roomTypeNo = req.getParameter("roomTypeNo");
				System.out.println(roomTypeNo);
				if (roomTypeNo == null || (roomTypeNo.trim()).length() == 0) {
					errorMsgs.add("●房型編號:請勿空白");
				}

				GregorianCalendar time = new GregorianCalendar();
				java.sql.Timestamp orderDate = new Timestamp(time.getTimeInMillis());
				System.out.println(orderDate);

				Integer roomNo = null;
				try {
					roomNo = new Integer(req.getParameter("roomNo"));
					System.out.println(roomNo);
				} catch (NumberFormatException e) {
					errorMsgs.add("●房間編號:請填數字");
				}

				Integer roomTypePrice = null;
				try {
					roomTypePrice = new Integer(req.getParameter("roomTypePrice"));
					System.out.println(roomTypePrice);
				} catch (NumberFormatException e) {
					errorMsgs.add("●房型價格:請填數字");
				}
				
				java.sql.Timestamp checkInDate = null;
				try {
				checkInDate = java.sql.Timestamp.valueOf((req.getParameter("checkInDate") + ":00").trim());
				}catch(IllegalArgumentException e) {
					errorMsgs.add("●入住日期:請勿空白");
				}
				
				java.sql.Timestamp checkOutDate = null;
				try {
				checkOutDate = java.sql.Timestamp.valueOf((req.getParameter("checkOutDate") + ":00").trim());
				}catch(IllegalArgumentException e) {
					errorMsgs.add("●退房日期:請勿空白");
				}

				String totalPrice = req.getParameter("totalPrice");
				System.out.println(totalPrice);
				if (totalPrice == null || (totalPrice.trim()).length() == 0) {
					errorMsgs.add("●訂單總價:請勿空白");
				}

				Integer hotelOrderStatus = null;
				try {
					hotelOrderStatus = new Integer(req.getParameter("hotelOrderStatus"));
					System.out.println(hotelOrderStatus);
				} catch (NumberFormatException e) {
					errorMsgs.add("●訂單狀態:請填數字");
				}

				HotelOrderVO hotelOrderVO = new HotelOrderVO();
				hotelOrderVO.setOrderNo(orderNo);
				hotelOrderVO.setMemNo(memNo);
				hotelOrderVO.setRoomTypeNo(roomTypeNo);
				hotelOrderVO.setOrderDate(orderDate);
				hotelOrderVO.setRoomNo(roomNo);
				hotelOrderVO.setRoomTypePrice(roomTypePrice);
				hotelOrderVO.setCheckInDate(checkInDate);
				hotelOrderVO.setCheckOutDate(checkOutDate);
				hotelOrderVO.setTotalPrice(totalPrice);
				hotelOrderVO.setHotelOrderStatus(hotelOrderStatus);

				HotelRoomVO hotelRoomVO = new HotelRoomVO();
				hotelRoomVO.setRoomNo(roomNo);
				hotelRoomVO.setRoomTypeNo(roomTypeNo);
				hotelRoomVO.setPetNo(petNo);
				hotelRoomVO.setRoomStatus(0);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("hotelOrderVO", hotelOrderVO); // 含有輸入格式錯誤的hotelorderVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelOrder/addHotelOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				HotelOrderService hotelOrderSvc = new HotelOrderService();
				hotelOrderVO = hotelOrderSvc.addHotelOrder(memNo, petNo, roomTypeNo, orderDate, roomNo, roomTypePrice,
						checkInDate, checkOutDate, totalPrice, hotelOrderStatus);
				// 新增訂單時要變更房間裡面的欄位(有無寵物)
				HotelRoomService hotelRoomSvc = new HotelRoomService();
				hotelRoomVO = hotelRoomSvc.updateHotelRoom(roomNo, roomTypeNo, petNo, 0);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/Hotel/HotelOrder/listAllHotelOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 新增成功後轉交listAllHotelOrder.jsp
				req.setAttribute("success", "success");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelOrder/addHotelOrder.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_HotelOrder_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String orderNo = req.getParameter("orderNo");
				System.out.println(orderNo);
				if (orderNo == null || (orderNo.trim()).length() == 0) {
					errorMsgs.add("●訂單編號:請勿空白");
				}

				String memNo = req.getParameter("memNo");
				System.out.println(memNo);
				if (memNo == null || (memNo.trim()).length() == 0) {
					errorMsgs.add("●會員編號:請勿空白");
				}
				String petNo = req.getParameter("petNo");
				System.out.println(petNo);
				if (petNo == null || (petNo.trim()).length() == 0) {
					errorMsgs.add("●寵物編號:請勿空白");
				}

				String roomTypeNo = req.getParameter("roomTypeNo");
				System.out.println(roomTypeNo);
				if (roomTypeNo == null || (roomTypeNo.trim()).length() == 0) {
					errorMsgs.add("●房型編號:請勿空白");
				}

				//訂單自動成時間
				GregorianCalendar time = new GregorianCalendar();
				java.sql.Timestamp orderDate = new Timestamp(time.getTimeInMillis());

				Integer roomNo = null;
				try {
					roomNo = new Integer(req.getParameter("roomNo"));
					System.out.println(roomNo);
				} catch (NumberFormatException e) {
					errorMsgs.add("●房間編號:請填數字");
				}

				Integer roomTypePrice = null;
				try {
					roomTypePrice = new Integer(req.getParameter("roomTypePrice"));
					System.out.println(roomTypePrice);
				} catch (NumberFormatException e) {
					errorMsgs.add("●房型價格:請填數字");
				}

				java.sql.Timestamp checkInDate = null;
				try {
				checkInDate = java.sql.Timestamp.valueOf((req.getParameter("checkInDate") + ":00").trim());
				}catch(IllegalArgumentException e) {
					errorMsgs.add("●入住日期:請勿空白");
				}

				java.sql.Timestamp checkOutDate = null;
				try {
				checkOutDate = java.sql.Timestamp.valueOf((req.getParameter("checkOutDate") + ":00").trim());
				}catch(IllegalArgumentException e) {
					errorMsgs.add("●退房日期:請勿空白");
				}

				String totalPrice = req.getParameter("totalPrice");
				System.out.println(totalPrice);
				if (totalPrice == null || (totalPrice.trim()).length() == 0) {
					errorMsgs.add("●訂單總價:請勿空白");
				}

				Integer hotelOrderStatus = null;
				try {
					hotelOrderStatus = new Integer(req.getParameter("hotelOrderStatus"));
					System.out.println(hotelOrderStatus);
				} catch (NumberFormatException e) {
					errorMsgs.add("●訂單狀態:請填數字");
				}

				HotelOrderVO hotelOrderVO = new HotelOrderVO();
				hotelOrderVO.setOrderNo(orderNo);
				hotelOrderVO.setMemNo(memNo);
				hotelOrderVO.setRoomTypeNo(roomTypeNo);
				hotelOrderVO.setOrderDate(orderDate);
				hotelOrderVO.setRoomNo(roomNo);
				hotelOrderVO.setRoomTypePrice(roomTypePrice);
				hotelOrderVO.setCheckInDate(checkInDate);
				hotelOrderVO.setCheckOutDate(checkOutDate);
				hotelOrderVO.setHotelOrderStatus(hotelOrderStatus);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("hotelOrderVO", hotelOrderVO); // 含有輸入格式錯誤的hotelOrderVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelOrder/update_HotelOrder_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				HotelOrderService hotelOrderSvc = new HotelOrderService();
				hotelOrderVO = hotelOrderSvc.updateHotelOrder(orderNo, memNo, petNo, roomTypeNo, orderDate, roomNo,
						roomTypePrice, checkInDate, checkOutDate, totalPrice, hotelOrderStatus);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("hotelOrderVO", hotelOrderVO); // 資料庫update成功後,正確的的hotelOrderVO物件,存入req
				String url = "/back-end/Hotel/HotelOrder/listOneHotelOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 修改成功後,轉交listOneHotelOrder.jsp
				
				//sweatAlert
				req.setAttribute("updateOk", "更新成功");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/Hotel/HotelOrder/update_HotelOrder_input.jsp");
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
				String orderNo = req.getParameter("orderNo");
				System.out.println(orderNo);

				/*************************** 2.開始刪除資料 ***************************************/
				HotelOrderService hotelOrderSvc = new HotelOrderService();
				hotelOrderSvc.deleteHotelOrder(orderNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/Hotel/HotelOrder/listAllHotelOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				req.setAttribute("fail", "刪除成功");
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelOrder/listAllHotelOrder.jsp");
				failureView.forward(req, res);

			}
		}

		if ("confirm".equals(action)) { // 來自listAllHotelRoomType.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String orderNo = req.getParameter("orderNo");
				System.out.println(orderNo);
				Integer hotelOrderStatus = new Integer(req.getParameter("hotelOrderStatus"));

				/*************************** 2.開始更新狀態 ***************************************/
				HotelOrderService hotelOrderSvc = new HotelOrderService();
				hotelOrderSvc.statusChange(hotelOrderStatus, orderNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/Hotel/HotelOrder/listAllHotelOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				req.setAttribute("statusChangeOK", "狀態更新成功");
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelOrder/listAllHotelOrder.jsp");
				failureView.forward(req, res);

			}
		}

		if ("cancel".equals(action)) { // 來自listAllHotelRoomType.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String orderNo = req.getParameter("orderNo");
				System.out.println(orderNo);

				/*************************** 2.開始更新狀態 ***************************************/
				HotelOrderService hotelOrderSvc = new HotelOrderService();
				hotelOrderSvc.cancelStatus(orderNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/Hotel/HotelOrder/listAllHotelOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				req.setAttribute("cancelOK", "訂單取消成功");
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelOrder/listAllHotelOrder.jsp");
				failureView.forward(req, res);

			}
		}

		if ("payMoney".equals(action)) {
			/*************************** 付款完成,準備轉交(Send the Success view) ***********/
			String url = "/front-end/Hotel/hotelOrderInfo/hotelOrderInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			req.setAttribute("payMoney", "付款成功"); // sweetAlert需要的參數
			successView.forward(req, res);
		}
	}
}

package com.HotelRoomType.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.HotelRoom.model.HotelRoomVO;
import com.HotelRoomType.model.HotelRoomTypeService;
import com.HotelRoomType.model.HotelRoomTypeVO;

@MultipartConfig
public class HotelRoomTypeServlet extends HttpServlet {
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
				String roomTypeNo = req.getParameter("roomTypeNo");
				System.out.println(roomTypeNo);
				if (roomTypeNo == null || (roomTypeNo.trim()).length() == 0) {
					errorMsgs.add("請輸入房型編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelRoomType/listAllHotelRoomType.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				HotelRoomTypeService hotelRoomTypeSvc = new HotelRoomTypeService();
				HotelRoomTypeVO hotelRoomTypeVO = hotelRoomTypeSvc.getOneHotelRoomType(roomTypeNo);
				if (hotelRoomTypeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelRoomType/listAllHotelRoomType.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("hotelRoomTypeVO", hotelRoomTypeVO); // 資料庫取出的hotelRoomTypeVO物件,存入req
				String url = "/back-end/Hotel/HotelRoomType/listOneHotelRoomType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Hotel/HotelRoomType/listAllHotelRoomType.jsp");
				failureView.forward(req, res);

			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllHotelRoomType.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String roomTypeNo = req.getParameter("roomTypeNo");
				System.out.println(roomTypeNo);
				/*************************** 2.開始查詢資料 ****************************************/
				HotelRoomTypeService hotelRoomSvc = new HotelRoomTypeService();
				HotelRoomTypeVO hotelRoomTypeVO = hotelRoomSvc.getOneHotelRoomType(roomTypeNo);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("hotelRoomTypeVO", hotelRoomTypeVO); // 資料庫取出的hotelRoomTypeVO物件,存入req
				String url = "/back-end/Hotel/HotelRoomType/update_HotelRoomType_input.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 update_HotelRoomType_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/Hotel/HotelRoomType/listAllHotelRoomType.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addHotelRoomType.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String roomTypeNo = req.getParameter("roomTypeNo");
				System.out.println(roomTypeNo);
				if (roomTypeNo == null || (roomTypeNo.trim()).length() == 0) {
					errorMsgs.add("房型編號:請勿空白");
				}

				String roomTypeName = req.getParameter("roomTypeName");
				String roomTypeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				System.out.println(roomTypeName);
				if (roomTypeName == null || roomTypeName.trim().length() == 0) {
					errorMsgs.add("房型名稱:請勿空白");
				} else if (!roomTypeName.trim().matches(roomTypeNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房型名稱:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String roomTypeDescribe = req.getParameter("roomTypeDescribe");
				System.out.println(roomTypeDescribe);
				if (roomTypeDescribe == null || (roomTypeDescribe.trim()).length() == 0) {
					errorMsgs.add("房型簡介:請勿空白");
				}

				String roomTypeSpace = req.getParameter("roomTypeSpace");
				System.out.println(roomTypeSpace);
				if (roomTypeSpace == null || (roomTypeSpace.trim()).length() == 0) {
					errorMsgs.add("房型空間:請勿空白");
				}

				Integer roomTypePrice = null;
				try {
					roomTypePrice = new Integer(req.getParameter("roomTypePrice"));
					System.out.println(roomTypePrice);
				} catch (NumberFormatException e) {
					roomTypePrice = 1600;
					errorMsgs.add("房型價格請填數字.");
				}

				String roomTypeService = req.getParameter("roomTypeService");
				System.out.println(roomTypeService);
				if (roomTypeService == null || (roomTypeService.trim()).length() == 0) {
					errorMsgs.add("房型服務:請勿空白");
				}

				Integer roomTypeStatus = null;
				try {
					roomTypeStatus = new Integer(req.getParameter("roomTypeStatus"));
					System.out.println(roomTypeStatus);
				} catch (NumberFormatException e) {
					roomTypeStatus = 0;
					errorMsgs.add("房型狀態:請填數字");
				}

				// 房型圖片
				Part part = req.getPart("roomTypePic");
				InputStream in = part.getInputStream();
				System.out.println(in.available());
				byte[] roomTypePic = new byte[in.available()];
				in.read(roomTypePic);
				in.close();

				HotelRoomTypeVO hotelRoomTypeVO = new HotelRoomTypeVO();
				hotelRoomTypeVO.setRoomTypeNo(roomTypeNo);
				hotelRoomTypeVO.setRoomTypeName(roomTypeName);
				hotelRoomTypeVO.setRoomTypeDescribe(roomTypeDescribe);
				hotelRoomTypeVO.setRoomTypeSpace(roomTypeSpace);
				hotelRoomTypeVO.setRoomTypePrice(roomTypePrice);
				hotelRoomTypeVO.setRoomTypeService(roomTypeService);
				hotelRoomTypeVO.setRoomTypeStatus(roomTypeStatus);
				hotelRoomTypeVO.setRoomTypePic(roomTypePic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("hotelRoomTypeVO", hotelRoomTypeVO); // 含有輸入格式錯誤的hotelRoomTypeVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/Hotel/HotelRoomType/addHotelRoomType.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				HotelRoomTypeService hotelRoomTypeSvc = new HotelRoomTypeService();
				hotelRoomTypeVO = hotelRoomTypeSvc.addHotelRoomType(roomTypeNo, roomTypeName, roomTypeDescribe,
						roomTypeSpace, roomTypePrice, roomTypeService, roomTypeStatus, roomTypePic);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/Hotel/HotelRoomType/listAllHotelRoomType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 新增成功後轉交listAllHotelRoomType.jsp
				req.setAttribute("success", "新增成功");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/Hotel/HotelRoomType/addHotelRoomType.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_HotelRoomType_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String roomTypeNo = req.getParameter("roomTypeNo");
				System.out.println(roomTypeNo);
				if (roomTypeNo == null || (roomTypeNo.trim()).length() == 0) {
					errorMsgs.add("房型編號:請勿空白");
				}

				String roomTypeName = req.getParameter("roomTypeName");
				String roomTypeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				System.out.println(roomTypeName);
				if (roomTypeName == null || (roomTypeName.trim()).length() == 0) {
					errorMsgs.add("房型名稱:請勿空白");
				} else if (!roomTypeName.trim().matches(roomTypeNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("房型名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String roomTypeDescribe = req.getParameter("roomTypeDescribe");
				System.out.println(roomTypeDescribe);
				if (roomTypeDescribe == null || (roomTypeDescribe.trim()).length() == 0) {
					errorMsgs.add("房型簡介:請勿空白");
				}

				String roomTypeSpace = req.getParameter("roomTypeSpace");
				System.out.println(roomTypeSpace);
				if (roomTypeSpace == null || (roomTypeSpace.trim()).length() == 0) {
					errorMsgs.add("房型空間:請勿空白");
				}

				Integer roomTypePrice = null;
				try {
					roomTypePrice = new Integer(req.getParameter("roomTypePrice"));
					System.out.println(roomTypePrice);
				} catch (NumberFormatException e) {
					roomTypePrice = 1600;
					errorMsgs.add("房型價格:請填數字");
				}

				String roomTypeService = req.getParameter("roomTypeService");
				System.out.println(roomTypeService);
				if (roomTypeService == null || (roomTypeService.trim()).length() == 0) {
					errorMsgs.add("房型服務:請勿空白");
				}

				Integer roomTypeStatus = null;
				try {
					roomTypeStatus = new Integer(req.getParameter("roomTypeStatus"));
					System.out.println(roomTypeStatus);
				} catch (NumberFormatException e) {
					roomTypeStatus = 0;
					errorMsgs.add("房型狀態:請填數字");
				}

				byte[] roomTypePic = null;
				InputStream in = null;

				try {
					// 房型圖片
					Part part = req.getPart("roomTypePic");
					in = part.getInputStream();
					System.out.println(in.available());
					if (in.available() > 0) {
						roomTypePic = new byte[in.available()];
						in.read(roomTypePic);
						in.close();
					} else {
						HotelRoomTypeService hotelRoomTypeSvc = new HotelRoomTypeService();
						HotelRoomTypeVO hotelRoomTypeVO = hotelRoomTypeSvc.getOneHotelRoomType(roomTypeNo);
						roomTypePic = hotelRoomTypeVO.getRoomTypePic();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

				HotelRoomTypeVO hotelRoomTypeVO = new HotelRoomTypeVO();
				hotelRoomTypeVO.setRoomTypeNo(roomTypeNo);
				hotelRoomTypeVO.setRoomTypeName(roomTypeName);
				hotelRoomTypeVO.setRoomTypeDescribe(roomTypeDescribe);
				hotelRoomTypeVO.setRoomTypeSpace(roomTypeSpace);
				hotelRoomTypeVO.setRoomTypePrice(roomTypePrice);
				hotelRoomTypeVO.setRoomTypeService(roomTypeService);
				hotelRoomTypeVO.setRoomTypeStatus(roomTypeStatus);
				hotelRoomTypeVO.setRoomTypePic(roomTypePic);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("hotelRoomTypeVO", hotelRoomTypeVO); // 含有輸入格式錯誤的hotelRoomTypeVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/Hotel/HotelRoomType/update_HotelRoomType_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				HotelRoomTypeService hotelRoomTypeSvc = new HotelRoomTypeService();
				hotelRoomTypeVO = hotelRoomTypeSvc.updateHotelRoomType(roomTypeNo, roomTypeName, roomTypeDescribe,
						roomTypeSpace, roomTypePrice, roomTypeService, roomTypeStatus, roomTypePic);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("hotelRoomTypeVO", hotelRoomTypeVO); // 資料庫update成功後,正確的的hotelRoomTypeVO物件,存入req
				String url = "/back-end/Hotel/HotelRoomType/listOneHotelRoomType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 修改成功後,轉交listOneHotelRoomType.jsp
				//sweatAlert
				req.setAttribute("updateOk", "更新成功");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/Hotel/HotelRoomType/update_HotelRoomType_input.jsp");
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
				String roomTypeNo = req.getParameter("roomTypeNo");
				System.out.println(roomTypeNo);

				/*************************** 2.開始刪除資料 ***************************************/
				HotelRoomTypeService hotelRoomTypeSvc = new HotelRoomTypeService();
				hotelRoomTypeSvc.deleteHotelRoomType(roomTypeNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/Hotel/HotelRoomType/listAllHotelRoomType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				req.setAttribute("fail", "刪除成功");
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/Hotel/HotelRoomType/listAllHotelRoomType.jsp");
				failureView.forward(req, res);
			}

		}
//		if ("showAllRoomByRT".equals(action)) {
//
//			String roomTypeNo = req.getParameter("roomTypeNo");
//			System.out.println(roomTypeNo);
//
//			HotelRoomTypeService hotelRoomTypeSvc = new HotelRoomTypeService();
//			Set<HotelRoomVO> hotelRoomTypeVO = hotelRoomTypeSvc.getRoomsBy_RTID(roomTypeNo);
//
//			req.setAttribute("hotelRoomTypeVO", hotelRoomTypeVO);
//			String url = "/back-end/HotelRoom/listAllHotelRoomByRT.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//
//		}

	}

}

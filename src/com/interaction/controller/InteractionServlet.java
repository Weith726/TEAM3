package com.interaction.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.interaction.model.*;

public class InteractionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自listAllInteraction.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("interactionNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入互動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adopt/interaction/listAllInteraction.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String interactionNo = null;
				try {
					interactionNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("互動編號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adopt/interaction/listAllInteraction.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				InteractionService interactionSvc = new InteractionService();
				InteractionVO interactionVO = interactionSvc.getOneInteraction(interactionNo);
				if (interactionVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adopt/interaction/listAllInteraction.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("interactionVO", interactionVO); // 資料庫取出的interactionVO物件,存入req
				String url = "/back-end/adopt/interaction/listOneInteraction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneInteraction.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/adopt/interaction/listAllInteraction.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllInteraction.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String interactionNo = new String(req.getParameter("interactionNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				InteractionService interactionSvc = new InteractionService();
				InteractionVO interactionVO = interactionSvc.getOneInteraction(interactionNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("interactionVO", interactionVO); // 資料庫取出的interactionVO物件,存入req
				String url = "/back-end/adopt/interaction/update_Interaction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 update_Interaction.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/adopt/interaction/listAllInteraction.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自 update_Interaction.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//		
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String interactionNo = req.getParameter("interactionNo");
				if (interactionNo == null || interactionNo.trim().length() == 0) {
					errorMsgs.add("互動編號: 請勿空白");
				}

				String petNo = req.getParameter("petNo");
				if (petNo == null || petNo.trim().length() == 0) {
					errorMsgs.add("收容寵物編號: 請勿空白");
				}

				String adopterNo = req.getParameter("adopterNo");
				if (adopterNo == null || adopterNo.trim().length() == 0) {
					errorMsgs.add("領養人編號: 請勿空白");
				}

				java.sql.Timestamp interactionDate = null;
				if (!(req.getParameter("interactionDate").isEmpty())) {
					String strDate = req.getParameter("interactionDate");
					String strDateRegI = "^[0-9]{4}-[0-9]{2}-[0-9]{2} [1]{1}[1357]{1}:[0]{2}$";
					String strDateRegII = "^[0-9]{4}-[0-9]{2}-[0-9]{2} 09:[0]{2}$";
					System.out.println(strDate);
					if (strDate.matches(strDateRegI) || strDate.matches(strDateRegII)) {
						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						// you can change format of date
						Date date = formatter.parse(strDate);
						interactionDate = new Timestamp(date.getTime());
//						interactionDate = java.sql.Timestamp.valueOf(req.getParameter("interactionDate"));
					} else {
						errorMsgs.add("預約時段錯誤");
					}
				}

				String interactionStatus = req.getParameter("interactionStatus");

				Integer adoptDesire = new Integer(req.getParameter("adoptDesire"));

				InteractionVO interactionVO = new InteractionVO();
				interactionVO.setInteractionNo(interactionNo);
				interactionVO.setPetNo(petNo);
				interactionVO.setAdopterNo(adopterNo);
				interactionVO.setInteractionDate(interactionDate);
				interactionVO.setInteractionStatus(interactionStatus);
				interactionVO.setAdoptDesire(adoptDesire);

//				 Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("interactionVO", interactionVO); // 含有輸入格式錯誤的interactionVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adopt/interaction/update_Interaction.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				InteractionService interactionSvc = new InteractionService();
				interactionVO = interactionSvc.updateInteraction(interactionNo, petNo, adopterNo, interactionDate,
						interactionStatus, adoptDesire);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("interactionVO", interactionVO); // 資料庫取出的interactionVO物件,存入req
				String url = "/back-end/adopt/interaction/listOneInteraction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneInteraction.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/adopt/interaction/update_Interaction.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自listAllInteraction.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//		
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String petNo = req.getParameter("petNo");
				if (petNo == null || petNo.trim().length() == 0) {
					errorMsgs.add("收容寵物編號: 請勿空白");
				}

				String adopterNo = req.getParameter("adopterNo");
				if (adopterNo == null || adopterNo.trim().length() == 0) {
					errorMsgs.add("領養人編號: 請勿空白");
				}

				java.sql.Timestamp interactionDate = null;
				if (!(req.getParameter("interactionDate").isEmpty())) {
					String strDate = req.getParameter("interactionDate");
					String strDateRegI = "^[0-9]{4}-[0-9]{2}-[0-9]{2} [1]{1}[1357]{1}:[0]{2}$";
					String strDateRegII = "^[0-9]{4}-[0-9]{2}-[0-9]{2} 09:[0]{2}$";
					System.out.println(strDate);
					if (strDate.matches(strDateRegI) || strDate.matches(strDateRegII)) {
						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						// you can change format of date
						Date date = formatter.parse(strDate);
						interactionDate = new Timestamp(date.getTime());
//						interactionDate = java.sql.Timestamp.valueOf(req.getParameter("interactionDate"));
					} else {
						errorMsgs.add("預約時段錯誤");
					}
				}

				String interactionStatus = req.getParameter("interactionStatus");

				Integer adoptDesire = new Integer(req.getParameter("adoptDesire"));

				InteractionVO interactionVO = new InteractionVO();
				interactionVO.setPetNo(petNo);
				interactionVO.setAdopterNo(adopterNo);
				interactionVO.setInteractionDate(interactionDate);
				interactionVO.setInteractionStatus(interactionStatus);
				interactionVO.setAdoptDesire(adoptDesire);

//				 Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("interactionVO", interactionVO); // 含有輸入格式錯誤的interactionVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adopt/interaction/add_Interaction.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				InteractionService interactionSvc = new InteractionService();
				interactionVO = interactionSvc.addInteraction(petNo, adopterNo, interactionDate, interactionStatus,
						adoptDesire);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("interactionVO", interactionVO); // 資料庫取出的interactionVO物件,存入req
				String url = "/back-end/adopt/interaction/listAllInteraction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllInteraction.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/interaction/add_Interaction.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert_Frontend".equals(action)) { // 來自listAllInteraction.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//		
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String petNo = req.getParameter("petNo");
				if (petNo == null || petNo.trim().length() == 0) {
					errorMsgs.add("收容寵物編號: 請勿空白");
				}

				String adopterNo = req.getParameter("adopterNo");
				if (adopterNo == null || adopterNo.trim().length() == 0) {
					errorMsgs.add("領養人編號: 請勿空白");
				}

				java.sql.Timestamp interactionDate = null;
				String strDay = req.getParameter("interactionDay");
				String strTime = req.getParameter("interactionTime");
				String strDate = strDay + " " + strTime;
				String strDateRegI = "^[0-9]{4}-[0-9]{2}-[0-9]{2} [1]{1}[1357]{1}:[0]{2}$";
				String strDateRegII = "^[0-9]{4}-[0-9]{2}-[0-9]{2} 09:[0]{2}$";
				System.out.println(strDate);
				if (strDate.matches(strDateRegI) || strDate.matches(strDateRegII)) {
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					// you can change format of date
					Date date = formatter.parse(strDate);
					interactionDate = new Timestamp(date.getTime());
//						interactionDate = java.sql.Timestamp.valueOf(req.getParameter("interactionDate"));
				} else {
					errorMsgs.add("預約時段錯誤");
				}

				String interactionStatus = req.getParameter("interactionStatus");

				Integer adoptDesire = null;

				if (req.getParameter("adoptDesire") == null) {
					adoptDesire = 2;
				} else {
					adoptDesire = new Integer(req.getParameter("adoptDesire"));
				}

				InteractionVO interactionVO = new InteractionVO();
				interactionVO.setPetNo(petNo);
				interactionVO.setAdopterNo(adopterNo);
				interactionVO.setInteractionDate(interactionDate);
				interactionVO.setInteractionStatus(interactionStatus);
				interactionVO.setAdoptDesire(adoptDesire);

//				 Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("interactionVO", interactionVO); // 含有輸入格式錯誤的interactionVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/adopt/interaction/addInteraction.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				InteractionService interactionSvc = new InteractionService();
				interactionVO = interactionSvc.addInteraction(petNo, adopterNo, interactionDate, interactionStatus,
						adoptDesire);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("interactionVO", interactionVO); // 資料庫取出的interactionVO物件,存入req
				String url = "/front-end/adopt/interaction/listInteractionByAdopter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllInteraction.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/adopt/interaction/addInteraction.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllAdopter.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String interactionNo = req.getParameter("interactionNo");
				if (interactionNo == null || interactionNo.trim().length() == 0) {
					errorMsgs.add("寵物編號: 請勿空白");
				}

				/*************************** 2.開始刪除資料 ***************************************/
				InteractionService interactionSvc = new InteractionService();
				interactionSvc.deleteInteraction(interactionNo);
				;

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/adopt/interaction/listAllInteraction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/adopt/interaction/listAllInteraction.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete_Frontend".equals(action)) { // 來自listAllAdopter.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String interactionNo = req.getParameter("interactionNo");
				if (interactionNo == null || interactionNo.trim().length() == 0) {
					errorMsgs.add("寵物編號: 請勿空白");
				}

				/*************************** 2.開始刪除資料 ***************************************/
				InteractionService interactionSvc = new InteractionService();
				interactionSvc.deleteInteraction(interactionNo);
				

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/adopt/interaction/listInteractionByAdopter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/adopt/interaction/listInteractionByAdopter.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

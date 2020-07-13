package com.HotelOrder.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HotelOrderDAO implements HotelOrderDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 新增
	private static final String INSERT_STMT = "INSERT INTO HOTELORDER (ORDERNO,MEMNO,PETNO,ROOMTYPENO,ORDERDATE,ROOMNO,ROOMTYPEPRICE,CHECKINDATE,CHECKOUTDATE,TOTALPRICE,HOTELORDERSTATUS)"
			+ "VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(HOTELORDER_SEQ.NEXTVAL), 6, '0'),?,?,?,?,?,?,?,?,?,?)";
	// 修改
	private static final String UPDATE_STMT = "UPDATE HOTELORDER SET MEMNO=?,PETNO=?,ROOMTYPENO=?,ROOMNO=?,ROOMTYPEPRICE=?,CHECKINDATE=?,CHECKOUTDATE=?,TOTALPRICE=?,HOTELORDERSTATUS=? WHERE ORDERNO=?";
	// 刪除
	private static final String DELETE_STMT = "DELETE FROM HOTELORDER WHERE ORDERNO=?";
	// 查詢(單筆)
	private static final String GET_ONE_STMT = "SELECT * FROM HOTELORDER WHERE ORDERNO=?";
	// 查詢(全部)
	private static final String GET_ALL_STMT = "SELECT * FROM HOTELORDER ORDER BY ORDERNO";

	private static final String GET_ONE_MEM_ORDER_STMT = "SELECT * FROM  HOTELORDER WHERE MEMNO=?";
	// 修改訂單狀態(變成已確認)
	private static final String STATUS_CHANGE = "UPDATE HOTELORDER SET HOTELORDERSTATUS=? WHERE ORDERNO=?";
	// 修改訂單狀態(變成已取消)
	private static final String STATUS_CANCEL = "UPDATE HOTELORDER SET HOTELORDERSTATUS=2 WHERE ORDERNO=?";

	@Override
	public void insert(HotelOrderVO hotelOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, hotelOrderVO.getMemNo());
			pstmt.setString(2, hotelOrderVO.getPetNo());
			pstmt.setString(3, hotelOrderVO.getRoomTypeNo());
			pstmt.setTimestamp(4, hotelOrderVO.getOrderDate());
			pstmt.setInt(5, hotelOrderVO.getRoomNo());
			pstmt.setInt(6, hotelOrderVO.getRoomTypePrice());
			pstmt.setTimestamp(7, hotelOrderVO.getCheckInDate());
			pstmt.setTimestamp(8, hotelOrderVO.getCheckOutDate());
			pstmt.setString(9, hotelOrderVO.getTotalPrice());
			pstmt.setInt(10, hotelOrderVO.getHotelOrderStatus());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(HotelOrderVO hotelOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, hotelOrderVO.getMemNo());
			pstmt.setString(2, hotelOrderVO.getPetNo());
			pstmt.setString(3, hotelOrderVO.getRoomTypeNo());
			pstmt.setInt(4, hotelOrderVO.getRoomNo());
			pstmt.setInt(5, hotelOrderVO.getRoomTypePrice());
			pstmt.setTimestamp(6, hotelOrderVO.getCheckInDate());
			pstmt.setTimestamp(7, hotelOrderVO.getCheckOutDate());
			pstmt.setString(8, hotelOrderVO.getTotalPrice());
			pstmt.setInt(9, hotelOrderVO.getHotelOrderStatus());
			pstmt.setString(10, hotelOrderVO.getOrderNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(String orderNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, orderNo);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public HotelOrderVO findByPrimaryKey(String orderNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HotelOrderVO hotelOrderVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, orderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				hotelOrderVO = new HotelOrderVO();
				hotelOrderVO.setOrderNo(rs.getString("orderNo"));
				hotelOrderVO.setMemNo(rs.getString("memNo"));
				hotelOrderVO.setPetNo(rs.getString("petNo"));
				hotelOrderVO.setRoomTypeNo(rs.getString("roomTypeNo"));
				hotelOrderVO.setOrderDate(rs.getTimestamp("orderDate"));
				hotelOrderVO.setRoomNo(rs.getInt("roomNo"));
				hotelOrderVO.setRoomTypePrice(rs.getInt("roomTypePrice"));
				hotelOrderVO.setCheckInDate(rs.getTimestamp("checkInDate"));
				hotelOrderVO.setCheckOutDate(rs.getTimestamp("checkOutDate"));
				hotelOrderVO.setTotalPrice(rs.getString("totalPrice"));
				hotelOrderVO.setHotelOrderStatus(rs.getInt("hotelOrderStatus"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return hotelOrderVO;
	}

	@Override
	public List<HotelOrderVO> getAll() {
		List<HotelOrderVO> list = new ArrayList<HotelOrderVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HotelOrderVO hotelOrderVO = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				hotelOrderVO = new HotelOrderVO();
				hotelOrderVO.setOrderNo(rs.getString("orderNo"));
				hotelOrderVO.setMemNo(rs.getString("memNo"));
				hotelOrderVO.setPetNo(rs.getString("petNo"));
				hotelOrderVO.setRoomTypeNo(rs.getString("roomTypeNo"));
				hotelOrderVO.setOrderDate(rs.getTimestamp("orderDate"));
				hotelOrderVO.setRoomNo(rs.getInt("roomNo"));
				hotelOrderVO.setRoomTypePrice(rs.getInt("roomTypePrice"));
				hotelOrderVO.setCheckInDate(rs.getTimestamp("checkInDate"));
				hotelOrderVO.setCheckOutDate(rs.getTimestamp("checkOutDate"));
				hotelOrderVO.setTotalPrice(rs.getString("totalPrice"));
				hotelOrderVO.setHotelOrderStatus(rs.getInt("hotelOrderStatus"));
				list.add(hotelOrderVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	@Override
	public List<HotelOrderVO> getOneMemOrder(String memNo) {
		List<HotelOrderVO> list = new ArrayList<HotelOrderVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HotelOrderVO hotelOrderVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MEM_ORDER_STMT);

			pstmt.setString(1, memNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				hotelOrderVO = new HotelOrderVO();
				hotelOrderVO.setOrderNo(rs.getString("orderNo"));
				hotelOrderVO.setMemNo(rs.getString("memNo"));
				hotelOrderVO.setPetNo(rs.getString("petNo"));
				hotelOrderVO.setRoomTypeNo(rs.getString("roomTypeNo"));
				hotelOrderVO.setOrderDate(rs.getTimestamp("orderDate"));
				hotelOrderVO.setRoomNo(rs.getInt("roomNo"));
				hotelOrderVO.setRoomTypePrice(rs.getInt("roomTypePrice"));
				hotelOrderVO.setCheckInDate(rs.getTimestamp("checkInDate"));
				hotelOrderVO.setCheckOutDate(rs.getTimestamp("checkOutDate"));
				hotelOrderVO.setTotalPrice(rs.getString("totalPrice"));
				hotelOrderVO.setHotelOrderStatus(rs.getInt("hotelOrderStatus"));
				list.add(hotelOrderVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	@Override
	public void confirm(HotelOrderVO hotelOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(STATUS_CHANGE);

			pstmt.setInt(1, hotelOrderVO.getHotelOrderStatus());
			pstmt.setString(2, hotelOrderVO.getOrderNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void cancel(String orderNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(STATUS_CANCEL);

			pstmt.setString(1, orderNo);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 後台FullCalendar用
	@Override
	public List<HotelOrderVO> getFullCalendarInfo() {
		List<HotelOrderVO> list = new ArrayList<HotelOrderVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HotelOrderVO hotelOrderVO = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				hotelOrderVO = new HotelOrderVO();
				hotelOrderVO.setTitle(rs.getString("memNo"));
				hotelOrderVO.setStart(rs.getTimestamp("checkInDate"));
				hotelOrderVO.setEnd(rs.getTimestamp("checkOutDate"));
				list.add(hotelOrderVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String subject, String messageText) {
		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
			// ●須將myGmail的【安全性較低的應用程式存取權】打開
			final String myGmail = "weiyuwang465@gmail.com";
			final String myGmail_password = "d0916888703";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			// 設定信中的內容
			message.setText(messageText);

			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}

}

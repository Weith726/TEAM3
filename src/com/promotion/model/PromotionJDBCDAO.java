package com.promotion.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PromotionJDBCDAO implements PromotionDAO_IN {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "DAVID";
	private static final String passwd = "123456";
	private static final String GETALL = "SELECT* FROM PROMOTION ORDER BY PROMOTIONNO";
	private static final String GETONE = "SELECT* FROM PROMOTION WHERE PROMOTIONNO=?";
	private static final String INSERT = "INSERT INTO PROMOTION(PROMOTIONNO,PROMOTIONNAME,STARTDAY,ENDDAY) VALUES(PROMOTION_SEQ.NEXTVAL,?,?,?)";
	private static final String UPDATE = "UPDATE FROM PROMOTION SET PROMOTIONNAME=?,STARTDAY=?,ENDDAY=? WHERE PROMOTIONNO=?";
	private static final String FINDSEQ="SELECT PROMOTION_SEQ.CURRVAL FROM DUAL";
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<PromotionVO> getAll() {
		List<PromotionVO> list = new ArrayList<PromotionVO>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		PromotionVO promotion = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(GETALL);
			rs = psmt.executeQuery();
			while (rs.next()) {
				promotion = new PromotionVO();
				promotion.setPromotionno(rs.getInt(1));
				promotion.setPromotionname(rs.getString(2));
				promotion.setStartday(rs.getDate(3));
				promotion.setEndday(rs.getDate(4));
				list.add(promotion);
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
			if (psmt != null) {
				try {
					psmt.close();
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
	public PromotionVO getOnepromotion(Integer promotionno) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		PromotionVO promotion = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(GETONE);
			psmt.setInt(1,promotionno);
			rs = psmt.executeQuery();
			while (rs.next()) {
				promotion = new PromotionVO();
				promotion.setPromotionno(rs.getInt(1));
				promotion.setPromotionname(rs.getString(2));
				promotion.setStartday(rs.getDate(3));
				promotion.setEndday(rs.getDate(4));
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
			if (psmt != null) {
				try {
					psmt.close();
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
		return promotion;
	}

	@Override
	public Integer addPromotion(PromotionVO promotion) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Integer seq = null;
		try {
			String[] cols ={"PROMOTIONNO"};
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			psmt = con.prepareStatement(INSERT,cols);
			psmt.setString(1, promotion.getPromotionname());
			psmt.setDate(2, promotion.getStartday());
			psmt.setDate(3, promotion.getEndday());
			psmt.executeUpdate();
			con.commit();
			rs =psmt.getGeneratedKeys();
			rs.next();
			//新增成功
//			psmt.close();
//			psmt = con.prepareStatement(FINDSEQ);
//			rs = psmt.executeQuery();
//			rs.next();
//			//拿到SEQ
		    seq = rs.getInt(1);
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
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
		return seq;
	}
	@Override
	public void updatePromotion(PromotionVO promotion) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(UPDATE);
			psmt.setString(1, promotion.getPromotionname());
			psmt.setDate(2, promotion.getStartday());
			psmt.setDate(3, promotion.getEndday());
			psmt.setInt(4, promotion.getPromotionno());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
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
}

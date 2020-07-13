package com.promotionDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PromoDetailDAO implements PromoDetailDAO_IN{
	private static final String INSERT="INSERT INTO PROMOTIONDETAIL VALUES(?,?,?)";
	private static final String GETONE="SELECT* FROM PROMOTIONDETAIL WHERE PROMOTIONNO=?";
	private static final String GETPROMOTIONPRICE="SELECT PRODUCTID,PROMOTIONPRICE  FROM  PROMOTIONDETAIL PD  ORDER BY PRODUCTID";
	private static final String UPDATE="UPDATE PROMOTIONDETAIL SET PROMOTIONPRICE=? WHERE PROMOTIONNO=? AND PRODUCTID=?";
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addpromotion(List<PromoDetailVO> list) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT);
			for(PromoDetailVO promotiondetail:list) {
				psmt.setInt(1,promotiondetail.getPromotionno());
				psmt.setInt(2, promotiondetail.getProductid());
				psmt.setInt(3, promotiondetail.getPromotionprice());
				psmt.executeUpdate();
			}
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
	@Override
	public List<PromoDetailVO> getOne(Integer promoDetailid) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<PromoDetailVO> list = new ArrayList<PromoDetailVO>();
		PromoDetailVO promoDvo = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETONE);
			psmt.setInt(1,promoDetailid);
			rs = psmt.executeQuery();
			while(rs.next()) {
				promoDvo = new PromoDetailVO();
				promoDvo.setPromotionno(rs.getInt(1));
				promoDvo.setProductid(rs.getInt(2));
				promoDvo.setPromotionprice(rs.getInt(3));
				list.add(promoDvo);
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
	public List<PromoDetailVO> getPmotionprice() {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<PromoDetailVO> list = new ArrayList<PromoDetailVO>();
		PromoDetailVO promoDvo = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETPROMOTIONPRICE);
			rs = psmt.executeQuery();
			while(rs.next()) {
				promoDvo = new PromoDetailVO();
				promoDvo.setProductid(rs.getInt(1));
				promoDvo.setPromotionprice(rs.getInt(2));
				list.add(promoDvo);
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
	public void updatepromodetail(PromoDetailVO PromoDetailVO) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE);
			psmt.setInt(1,PromoDetailVO.getPromotionprice());
			psmt.setInt(2,PromoDetailVO.getPromotionno());
			psmt.setInt(3,PromoDetailVO.getProductid());
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

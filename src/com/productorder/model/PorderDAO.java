package com.productorder.model;

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

public class PorderDAO implements PorderDAO_IN{
	private static final String INSERT = "INSERT INTO PRODUCTORDER (ORDERID,MEMNO,ORDERDATE,ORDERTOTAL,DELIVERYADDRESS,ORDERSTATUS) "
			+ "VALUES(PRODUCTORDER_SEQ.NEXTVAL,?,TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI'),?,?,?)";
	private static final String GETALL = "SELECT* FROM PRODUCTORDER ORDER BY ORDERID DESC";
	private static final String FindByPriMaryKey = "SELECT* FROM PRODUCTORDER WHERE ORDERID=?";
	private static final String UPDATE = "UPDATE PRODUCTORDER SET ORDERSTATUS= ? WHERE ORDERID= ?";
	private static final String FINDSEQ="SELECT PRODUCT_SEQ.CURRVAL FROM DUAL";
	private static final String FINDBYMEMNO="SELECT* FROM PRODUCTORDER WHERE MEMNO=? ORDER BY ORDERID DESC" ;
	
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
	public List<PorderVO> getAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PorderVO> list = new ArrayList<PorderVO>();
		PorderVO porder = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				porder = new PorderVO();
				porder.setOrderid(rs.getInt(1));
				porder.setMemno(rs.getString(2));
				porder.setOrderdate(rs.getString(3));
				porder.setOrdertotal(rs.getInt(4));
				porder.setDeliveryaddress(rs.getString(5));
				porder.setOrderstatus(rs.getInt(6));
				list.add(porder);
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
			if (ps != null) {
				try {
					ps.close();
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
	public PorderVO findByPrimaryKey(Integer orderid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PorderVO porder = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(FindByPriMaryKey);
			ps.setInt(1, orderid);
			rs = ps.executeQuery();
			rs.next();
			porder = new PorderVO();
			porder = new PorderVO();
			porder.setOrderid(rs.getInt(1));
			porder.setMemno(rs.getString(2));
			porder.setOrderdate(rs.getString(3));
			porder.setOrdertotal(rs.getInt(4));
			porder.setDeliveryaddress(rs.getString(5));
			porder.setOrderstatus(rs.getInt(6));
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
			if (ps != null) {
				try {
					ps.close();
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
		return porder;
	}

	@Override
	public Integer insert(PorderVO pordervo) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer seq = null;
		try {
			String[] col ={"ORDERID"};
			con = ds.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(INSERT,col);
			ps.setString(1, pordervo.getMemno());
			ps.setInt(2, pordervo.getOrdertotal());
			ps.setString(3, pordervo.getDeliveryaddress());
			ps.setInt(4, pordervo.getOrderstatus());
			ps.executeUpdate();
			con.commit();
			rs = ps.getGeneratedKeys();
//			ps.close();
//			ps = con.prepareStatement(FINDSEQ);
//			rs = ps.executeQuery();
			rs.next();
			seq = rs.getInt(1);
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
			if (ps != null) {
				try {
					ps.close();
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
	public void update(Integer orderstatus, Integer orderid) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UPDATE);
			ps.setInt(1, orderstatus);
			ps.setInt(2, orderid);
			System.out.println(ps.executeUpdate());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
	public List<PorderVO> getorderbymem(String memno) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PorderVO porder = null;
		List<PorderVO> list = new ArrayList<PorderVO>(); 
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(FINDBYMEMNO);
			ps.setString(1, memno);
			rs = ps.executeQuery();
			while(rs.next()) {
				porder = new PorderVO();
				porder.setOrderid(rs.getInt(1));
				porder.setMemno(rs.getString(2));
				porder.setOrderdate(rs.getString(3));
				porder.setOrdertotal(rs.getInt(4));
				porder.setDeliveryaddress(rs.getString(5));
				porder.setOrderstatus(rs.getInt(6));
				list.add(porder);
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
			if (ps != null) {
				try {
					ps.close();
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
}

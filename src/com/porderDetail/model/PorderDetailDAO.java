package com.porderDetail.model;

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

public class PorderDetailDAO implements PorderDetailDAO_IN{
	
	
	private static final String INSERT="INSERT INTO PRODUCTORDERDETAIL VALUES(?,?,?,?)";
	private static final String GETBYID="SELECT* FROM PRODUCTORDERDETAIL WHERE ORDERID=?";
	
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
	public void addporderDetail(List<PorderDetailVO> list) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(INSERT);
			
			for(PorderDetailVO porderdetailvo:list) {
				ps.setInt(1,porderdetailvo.getOrderid());
				ps.setInt(2,porderdetailvo.getProid());
				ps.setInt(4,porderdetailvo.getUnitprice());
				ps.setInt(3,porderdetailvo.getQuantity());
				ps.executeUpdate();
			}
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
	public List<PorderDetailVO> getOne(Integer orderid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PorderDetailVO> list = new ArrayList<PorderDetailVO>();
		PorderDetailVO vo = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(GETBYID);
			ps.setInt(1,orderid);
			rs = ps.executeQuery();		
			while(rs.next()) {
				vo = new PorderDetailVO();
				vo.setOrderid(rs.getInt(1));
				vo.setProid(rs.getInt(2));
				vo.setQuantity(rs.getInt(3));
				vo.setUnitprice(rs.getInt(4));
				list.add(vo);
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

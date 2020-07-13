package com.memlatestinfo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class MemlatestinfoDAO implements Memlatestinfo_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private final static String INSERT_STMT = "INSERT INTO Memlatestinfo VALUES (MEMLATESTINFO_SEQ.NEXTVAL,?,?)";
	private final static String UPDATE = "UPDATE Memlatestinfo SET memNo=?,infoContent=? WHERE memLatestInfoNo=?";
	private final static String DELETE = "DELETE FROM  Memlatestinfo where memLatestInfoNo = ?";
	private final static String GET_ONE_STMT = "SELECT memLatestInfoNo,memNo,infoContent FROM Memlatestinfo WHERE memLatestInfoNo=?";
	private final static String GET_ALL_STMT = "SELECT memLatestInfoNo,memNo,infoContent FROM Memlatestinfo ORDER BY memLatestInfoNo";
	
	public void insert(MemlatestinfoVO memliVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memliVO.getMemNo());
			pstmt.setString(2, memliVO.getInfoContent());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	public void update(MemlatestinfoVO memliVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memliVO.getMemNo());
			pstmt.setString(2, memliVO.getInfoContent());
			pstmt.setInt(3, memliVO.getMemLatestInfoNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	public void delete(Integer memLatestInfoNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memLatestInfoNo);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	public MemlatestinfoVO findByPrimaryKey(Integer memLatestInfoNo) {
		MemlatestinfoVO memlatestinfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memLatestInfoNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memlatestinfoVO = new MemlatestinfoVO();
				memlatestinfoVO.setMemLatestInfoNo(rs.getInt("memLatestInfoNo"));
				memlatestinfoVO.setMemNo(rs.getString("memNo"));
				memlatestinfoVO.setInfoContent(rs.getString("infoContent"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {

				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return memlatestinfoVO;
	}

	public List<MemlatestinfoVO> getAll() {
		List<MemlatestinfoVO> list = new ArrayList<MemlatestinfoVO>();

		MemlatestinfoVO memlatestinfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memlatestinfoVO = new MemlatestinfoVO();
				memlatestinfoVO.setMemLatestInfoNo(rs.getInt("memLatestInfoNo"));
				memlatestinfoVO.setMemNo(rs.getString("memNo"));
				memlatestinfoVO.setInfoContent(rs.getString("infoContent"));
				list.add(memlatestinfoVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}
	
}

package com.accusation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.memlatestinfo.model.MemlatestinfoVO;

public class AccusationJDBCDAO implements Accusation_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "PUPPY";
	String passwd = "123456";
	
	private final static String INSERT_STMT ="INSERT INTO Accusation VALUES ('M'||LPAD(TO_CHAR(ACCUSATION_SEQ.NEXTVAL),4,'0'),?,?,(CURRENT_TIMESTAMP),?)";
	private final static String UPDATE ="UPDATE Accusation SET accusationType=?,accusationContent=?,accusationStatue=? WHERE accusationNo=?";
	private final static String DELETE = "DELETE FROM  Accusation where accusationNo = ?";
	private static final String GET_ONE_STMT ="SELECT accusationNo,accusationType,accusationContent,accusationtime,accusationStatue FROM Accusation WHERE accusationNo=?";
	private final static String GET_ALL_STMT ="SELECT * FROM Accusation ORDER BY accusationNo";
	@Override
	public void insert(AccusationVO accusationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, accusationVO.getAccusationType());
			pstmt.setString(2, accusationVO.getAccusationContent());
			pstmt.setInt(3,accusationVO.getAccusationStatue());
			

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	@Override
	public void update(AccusationVO accusationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(4,accusationVO.getAccusationNo());
			pstmt.setString(1, accusationVO.getAccusationType());
			pstmt.setString(2,accusationVO.getAccusationContent());
			pstmt.setInt(3,accusationVO.getAccusationStatue());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	@Override
	public void delete(String accusationNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, accusationNo);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	@Override
	public AccusationVO findByPrimaryKey(String accusationNo) {
		AccusationVO accusationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, accusationNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				accusationVO = new AccusationVO();
				accusationVO.setAccusationNo(rs.getString("accusationNo"));
				accusationVO.setAccusationType(rs.getString("accusationType"));
				accusationVO.setAccusationContent(rs.getString("accusationContent"));
				accusationVO.setAccusationtime(rs.getTimestamp("accusationtime"));
				accusationVO.setAccusationStatue(rs.getInt("accusationStatue"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return accusationVO;
	}
	@Override
	public List<AccusationVO> getAll() {
		List<AccusationVO> list = new ArrayList<AccusationVO>();

		AccusationVO accusationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				accusationVO = new AccusationVO();
				accusationVO.setAccusationNo(rs.getString("accusationNo"));
				accusationVO.setAccusationType(rs.getString("accusationType"));
				accusationVO.setAccusationContent(rs.getString("accusationContent"));
				accusationVO.setAccusationtime(rs.getTimestamp("accusationtime"));
				accusationVO.setAccusationStatue(rs.getInt("accusationStatue"));
				list.add(accusationVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

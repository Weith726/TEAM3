package com.div.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.doc.model.DocVO;

public class DivDAO implements DivDAO_interface {

	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO division (divno,divname) VALUES (division_seq.NEXTVAL, ?)";
	private static final String GET_ALL_STMT = "SELECT divno,divname FROM division";
	private static final String GET_ONE_STMT = "SELECT divno,divname FROM division where divno = ?";
	private static final String GET_Docs_ByDivno_STMT = "SELECT docno, divno, docname, roomno, seniority, intro, docpic, docstatus FROM doctor where divno = ? order by docno";
	
	private static final String DELETE_DOCs = "DELETE FROM doctor where divno = ?";
	private static final String DELETE_DIV = "DELETE FROM division where divno = ?";	
	
	private static final String UPDATE = "UPDATE division set divname=? where divno = ?";

	@Override
	public void insert(DivVO divVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, divVO.getDivname());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void update(DivVO divVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, divVO.getDivno());
			pstmt.setString(2, divVO.getDivname());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void delete(String divno) {
		int updateCount_DOCs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			// 1���]�w�� pstm.executeUpdate()���e
			con.setAutoCommit(false);

			// ���R����v
			pstmt = con.prepareStatement(DELETE_DOCs);
			pstmt.setString(1, divno);
			updateCount_DOCs = pstmt.executeUpdate();
			// �A�R����O
			pstmt = con.prepareStatement(DELETE_DIV);
			pstmt.setString(1, divno);
			pstmt.executeUpdate();

			// 2���]�w�� pstm.executeUpdate()����
			con.commit();
			con.setAutoCommit(true);
			System.out.println("�R����O�s��" + divno + "��,�@����v" + updateCount_DOCs
					+ "��P�ɳQ�R��");
			
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3���]�w�����exception�o�ͮɤ�catch�϶���
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public DivVO findByPrimaryKey(String divno) {

		DivVO divVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, divno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// divVO �]�٬� Domain objects
				divVO = new DivVO();
				divVO.setDivno(rs.getString("divno"));
				divVO.setDivname(rs.getString("divname"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return divVO;
	}

	@Override
	public List<DivVO> getAll() {
		List<DivVO> list = new ArrayList<DivVO>();
		DivVO divVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				divVO = new DivVO();
				divVO.setDivno(rs.getString("divno"));
				divVO.setDivname(rs.getString("divname"));
				list.add(divVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	@Override
	public Set<DocVO> getDocsByDivno(String divno) {
		Set<DocVO> set = new LinkedHashSet<DocVO>();
		DocVO docVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Docs_ByDivno_STMT);
			pstmt.setString(1, divno);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				docVO = new DocVO();
				docVO.setDocno(rs.getString("docno"));
				docVO.setDivno(rs.getString("divno"));
				docVO.setDocname(rs.getString("docname"));
				docVO.setRoomno(rs.getInt("roomno"));
				docVO.setSeniority(rs.getInt("seniority"));
				docVO.setIntro(rs.getString("intro"));
				docVO.setDocpic(rs.getBytes("docpic"));
				docVO.setDocstatus(rs.getInt("docstatus"));
				set.add(docVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}
}
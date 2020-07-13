package com.interaction.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.adoptedpets.model.AdoptedPetsVO;
import com.petshelter.model.PetShelterVO;

public class InteractionDAO implements InteractionDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO INTERACTION (INTERACTIONNO, PETNO, ADOPTERNO, INTERACTIONDATE, INTERACTIONSTATUS, ADOPTDESIRE) VALUES ('IA'||LPAD(to_char(INTERACTIONNO_SEQ.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT INTERACTIONNO, PETNO, ADOPTERNO, INTERACTIONDATE, INTERACTIONSTATUS, ADOPTDESIRE FROM INTERACTION";
	private static final String GET_ONE_STMT = "SELECT INTERACTIONNO, PETNO, ADOPTERNO, INTERACTIONDATE, INTERACTIONSTATUS, ADOPTDESIRE FROM INTERACTION WHERE INTERACTIONNO =?";
	private static final String GET_INTERACTION_BYADOTERNO = "SELECT INTERACTIONNO, PETNO, ADOPTERNO, INTERACTIONDATE, INTERACTIONSTATUS, ADOPTDESIRE FROM INTERACTION WHERE ADOPTERNO =?";
	
	private static final String DELETE_INTERACTION = "DELETE FROM INTERACTION WHERE INTERACTIONNO = ?";

	private static final String UPDATE = "UPDATE INTERACTION SET PETNO = ?, ADOPTERNO = ?, INTERACTIONDATE = ?, INTERACTIONSTATUS = ?, ADOPTDESIRE = ? WHERE INTERACTIONNO = ?";

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
	public void insert(InteractionVO interactionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, interactionVO.getPetNo());
			pstmt.setString(2, interactionVO.getAdopterNo());
			pstmt.setTimestamp(3, interactionVO.getInteractionDate());
			pstmt.setString(4, interactionVO.getInteractionStatus());
			pstmt.setInt(5, interactionVO.getAdoptDesire());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(InteractionVO interactionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, interactionVO.getPetNo());
			pstmt.setString(2, interactionVO.getAdopterNo());
			pstmt.setTimestamp(3, interactionVO.getInteractionDate());
			pstmt.setString(4, interactionVO.getInteractionStatus());
			pstmt.setInt(5, interactionVO.getAdoptDesire());
			pstmt.setString(6, interactionVO.getInteractionNo());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String interactionNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 刪除收容住所
			pstmt = con.prepareStatement(DELETE_INTERACTION);
			pstmt.setString(1, interactionNo);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
	public InteractionVO findByPrimaryKey(String interactionNo) {
		InteractionVO interactionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, interactionNo);
				
			rs = pstmt.executeQuery();

			while (rs.next()) {
				interactionVO = new InteractionVO();

				interactionVO.setInteractionNo(rs.getString("INTERACTIONNO"));
				interactionVO.setPetNo(rs.getString("PETNO"));
				interactionVO.setAdopterNo(rs.getString("ADOPTERNO"));
				interactionVO.setInteractionDate(rs.getTimestamp("INTERACTIONDATE"));
				interactionVO.setInteractionStatus(rs.getString("INTERACTIONSTATUS"));
				interactionVO.setAdoptDesire(rs.getInt("ADOPTDESIRE"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
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
		return interactionVO;
	}

	@Override
	public List<InteractionVO> getAll() {
		List<InteractionVO> list = new ArrayList<InteractionVO>();
		InteractionVO interactionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				interactionVO = new InteractionVO();

				interactionVO.setInteractionNo(rs.getString("INTERACTIONNO"));
				interactionVO.setPetNo(rs.getString("PETNO"));
				interactionVO.setAdopterNo(rs.getString("ADOPTERNO"));
				interactionVO.setInteractionDate(rs.getTimestamp("INTERACTIONDATE"));
				interactionVO.setInteractionStatus(rs.getString("INTERACTIONSTATUS"));
				interactionVO.setAdoptDesire(rs.getInt("ADOPTDESIRE"));

				list.add(interactionVO); // Store the row in the list

			}

			// Handle any SQL errors
		} catch (SQLException se) {
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
	
	@Override
	public Set<InteractionVO> findByAdopterNo(String adopterNo){
		Set<InteractionVO> set = new LinkedHashSet<InteractionVO>();
		InteractionVO interactionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_INTERACTION_BYADOTERNO);
			pstmt.setString(1, adopterNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				interactionVO = new InteractionVO();

				interactionVO.setInteractionNo(rs.getString("INTERACTIONNO"));
				interactionVO.setPetNo(rs.getString("PETNO"));
				interactionVO.setAdopterNo(rs.getString("ADOPTERNO"));
				interactionVO.setInteractionDate(rs.getTimestamp("INTERACTIONDATE"));
				interactionVO.setInteractionStatus(rs.getString("INTERACTIONSTATUS"));
				interactionVO.setAdoptDesire(rs.getInt("ADOPTDESIRE"));

				set.add(interactionVO); // Store the row in the list

			}

			// Handle any SQL errors
		} catch (SQLException se) {
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
		return set;
	}
}

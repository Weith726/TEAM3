package com.petshelter.model;

import java.util.List;
import java.util.Set;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.adoptedpets.model.AdoptedPetsVO;

public class PetShelterDAO implements PetShelterDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO PETSHELTER (SHELTERNO, CLEANDATE, SHELTERSTATUS) VALUES ('S'||LPAD(to_char(SHELTERNO_SEQ.NEXTVAL), 4, '0'), ?, ?)";
	private static final String GET_ALL_STMT = "SELECT SHELTERNO, CLEANDATE, SHELTERSTATUS FROM PETSHELTER";
	private static final String GET_ONE_STMT = "SELECT SHELTERNO, CLEANDATE, SHELTERSTATUS FROM PETSHELTER WHERE SHELTERNO = ?";
	private static final String GET_ADOPTEDPETS_BYSHELTERNO_STMT = "SELECT PETNO, ADOPTERNO, SHELTERNO, PETBREED, ADOPTEDDATE, PETPIC, ADOPTDATE, INTERVIEWDATE, INTERVIEWINFO, PETSPECIES, PETGENDER, ADOPTSTATUS FROM ADOPTEDPETS WHERE SHELTERNO = ? ORDER BY PETNO ASC";
	
	private static final String DELETE_ADOPTEDPETS = "DELETE FROM ADOPTEDPETS WHERE SHELTERNO = ?";
	private static final String DELETE_PETSHELTER = "DELETE FROM PETSHELTER WHERE SHELTERNO = ?";	
	
	private static final String UPDATE = "UPDATE PETSHELTER SET CLEANDATE=?, SHELTERSTATUS=? WHERE SHELTERNO = ?";
	
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
	
	@Override
	public void insert(PetShelterVO petshelterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setDate(1, petshelterVO.getCleanDate());
			pstmt.setInt(2, petshelterVO.getShelterStatus());

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
	public void update(PetShelterVO petshelterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDate(1, petshelterVO.getCleanDate());
			pstmt.setInt(2, petshelterVO.getShelterStatus());
			pstmt.setString(3, petshelterVO.getShelterNo());

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
	public void delete(String shelterNo) {
		int updateCount_AdoptedPets;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除收容寵物
			pstmt = con.prepareStatement(DELETE_ADOPTEDPETS);
			pstmt.setString(1, shelterNo);
			updateCount_AdoptedPets = pstmt.executeUpdate();
			// 再刪除收容住所
			pstmt = con.prepareStatement(DELETE_PETSHELTER);
			pstmt.setString(1, shelterNo);
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
	public PetShelterVO findByPrimaryKey(String shelterNo) {
		PetShelterVO petshelterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, shelterNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// petshelterVO 也稱為 Domain objects
				petshelterVO = new PetShelterVO();
				petshelterVO.setShelterNo(rs.getString("SHELTERNO"));
				petshelterVO.setCleanDate(rs.getDate("CLEANDATE"));
				petshelterVO.setShelterStatus(rs.getInt("SHELTERSTATUS"));
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
		return petshelterVO;
	}

	@Override
	public List<PetShelterVO> getAll() {
		List<PetShelterVO> list = new ArrayList<PetShelterVO>();
		PetShelterVO petshelterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
//			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "EA101", "EA101");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				petshelterVO = new PetShelterVO();
				petshelterVO.setShelterNo(rs.getString("SHELTERNO"));
				petshelterVO.setCleanDate(rs.getDate("CLEANDATE"));
				petshelterVO.setShelterStatus(rs.getInt("SHELTERSTATUS"));
				list.add(petshelterVO); // Store the row in the list
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
	public Set<AdoptedPetsVO> getAdoptedPetsByShelterNo(String shelterNo) {
		Set<AdoptedPetsVO> set = new LinkedHashSet<AdoptedPetsVO>();
		AdoptedPetsVO adoptedpetsVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ADOPTEDPETS_BYSHELTERNO_STMT);
			pstmt.setString(1, shelterNo);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				
				adoptedpetsVO = new AdoptedPetsVO();
				
				adoptedpetsVO.setPetNo(rs.getString("PETNO"));
				adoptedpetsVO.setAdopterNo(rs.getString("ADOPTERNO"));
				adoptedpetsVO.setShelterNo(rs.getString("SHELTERNO"));
				adoptedpetsVO.setPetBreed(rs.getString("PETBREED"));
				adoptedpetsVO.setAdoptedDate(rs.getDate("ADOPTEDDATE"));
				adoptedpetsVO.setPetPic(rs.getBytes("PETPIC"));
				adoptedpetsVO.setAdoptDate(rs.getDate("ADOPTDATE"));
				adoptedpetsVO.setInterviewDate(rs.getDate("INTERVIEWDATE"));
				adoptedpetsVO.setInterviewInfo(rs.getString("INTERVIEWINFO"));
				adoptedpetsVO.setPetSpecies(rs.getString("PETSPECIES"));
				adoptedpetsVO.setPetGender(rs.getString("PETGENDER"));
				adoptedpetsVO.setAdoptStatus(rs.getInt("ADOPTSTATUS"));
				
				set.add(adoptedpetsVO); // Store the row in the vector
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



//	public static void main (String[] args) throws Throwable {
//
//	//  GetAll Simulation
//		
//		PetShelterDAO dao = new PetShelterDAO();
//		List<PetShelterVO> all = dao.getAll();
//	
//		for(Iterator it=all.iterator();it.hasNext();) {
//		
//		PetShelterVO a = (PetShelterVO) it.next();
//		System.out.println(a.getShelterStatus());
//		System.out.println(a.getCleanDate());
//		System.out.println(a.getShelterNo());
//
//
//		}
//	}
}	

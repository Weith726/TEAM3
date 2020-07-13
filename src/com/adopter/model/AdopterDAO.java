package com.adopter.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class AdopterDAO implements AdopterDAO_interface {
	
	private static final String INSERT_STMT = "INSERT INTO ADOPTER (ADOPTERNO, ADOPTERNAME, ADOPTERGENDER, ADOPTEROCCUPATION, ADOPTERMAIL) VALUES ('APPL'||LPAD(to_char(ADOPTERNO_SEQ.NEXTVAL), 4, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT ADOPTERNO, ADOPTERNAME, ADOPTERGENDER, ADOPTEROCCUPATION, UPPER(ADOPTERMAIL) ADOPTERMAIL FROM ADOPTER";
	private static final String GET_ONE_STMT = "SELECT ADOPTERNO, ADOPTERNAME, ADOPTERGENDER, ADOPTEROCCUPATION, UPPER(ADOPTERMAIL) ADOPTERMAIL FROM ADOPTER WHERE ADOPTERNO = ?";
	private static final String GET_MAIL = "SELECT ADOPTERNO, ADOPTERGENDER, ADOPTEROCCUPATION, UPPER(ADOPTERMAIL) ADOPTERMAIL FROM ADOPTER WHERE ADOPTERNAME = ?";
	private static final String GET_ADOPTEDPETS_BYADOPTERNO_STMT = "SELECT PETNO, ADOPTERNO, SHELTERNO, PETBREED, ADOPTEDDATE, PETPIC, ADOPTDATE, INTERVIEWDATE, INTERVIEWINFO, PETSPECIES, PETGENDER, ADOPTSTATUS FROM ADOPTEDPETS WHERE SHELTERNO = ? ORDER BY ADOPTERNO ASC";
	
	private static final String DELETE_ADOPTEDPETS = "DELETE FROM ADOPTEDPETS WHERE ADOPTERNO = ?";
	private static final String DELETE_ADOPTER = "DELETE FROM ADOPTER WHERE ADOPTERNO = ?";	
	
	private static final String UPDATE = "UPDATE ADOPTER SET ADOPTERNAME=?, ADOPTERGENDER=?, ADOPTEROCCUPATION=?, ADOPTERMAIL=? WHERE ADOPTERNO = ?";
	
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
	public void insert(AdopterVO adopterVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adopterVO.getAdopterName());
			pstmt.setString(2, adopterVO.getAdopterGender());
			pstmt.setString(3, adopterVO.getAdopterOccupation());
			pstmt.setString(4, adopterVO.getAdopterMail());

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
	public void update(AdopterVO adopterVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, adopterVO.getAdopterName());
			pstmt.setString(2, adopterVO.getAdopterGender());
			pstmt.setString(3, adopterVO.getAdopterOccupation());
			pstmt.setString(4, adopterVO.getAdopterMail());
			pstmt.setString(5, adopterVO.getAdopterNo());

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
	public void delete(String adopterNo) {
		// TODO Auto-generated method stub
		int updateCount_AdoptedPets = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();						

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			// 刪除收容寵物			
			pstmt = con.prepareStatement(DELETE_ADOPTEDPETS);
			pstmt.setString(1, adopterNo);
			pstmt.executeUpdate();			
			// 刪除領養人
			pstmt = con.prepareStatement(DELETE_ADOPTER);
			pstmt.setString(1, adopterNo);
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
	public AdopterVO findByPrimaryKey(String adopterNo) {
		// TODO Auto-generated method stub
		AdopterVO adopterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, adopterNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// adopterVO 也稱為 Domain objects
				adopterVO = new AdopterVO();
				adopterVO.setAdopterNo(rs.getString("ADOPTERNO"));
				adopterVO.setAdopterName(rs.getString("ADOPTERNAME"));
				adopterVO.setAdopterGender(rs.getString("ADOPTERGENDER"));
				adopterVO.setAdopterOccupation(rs.getString("ADOPTEROCCUPATION"));
				adopterVO.setAdopterMail(rs.getString("ADOPTERMAIL"));
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
		return adopterVO;
	}
	

	@Override
	public List<AdopterVO> getAll() {
		// TODO Auto-generated method stub
		List<AdopterVO> list = new ArrayList<AdopterVO>();
		AdopterVO adopterVO = null;

//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

//			con = ds.getConnection();
			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "EA101", "EA101");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adopterVO = new AdopterVO();
				adopterVO.setAdopterNo(rs.getString("ADOPTERNO"));
				adopterVO.setAdopterName(rs.getString("ADOPTERNAME"));
				adopterVO.setAdopterGender(rs.getString("ADOPTERGENDER"));
				adopterVO.setAdopterOccupation(rs.getString("ADOPTEROCCUPATION"));
				adopterVO.setAdopterMail(rs.getString("ADOPTERMAIL"));
				list.add(adopterVO); // Store the row in the list
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
	public Set<AdoptedPetsVO> getAdoptedPetsByAdopterNo(String adopterNo) {
		// TODO Auto-generated method stub
		Set<AdoptedPetsVO> set = new LinkedHashSet<AdoptedPetsVO>();
		AdoptedPetsVO adoptedpetsVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ADOPTEDPETS_BYADOPTERNO_STMT);
			pstmt.setString(1, adopterNo);
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
	
	@Override
	public AdopterVO findByAdopterName(String adopterName) {
		// TODO Auto-generated method stub
		AdopterVO adopterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MAIL);

			pstmt.setString(1, adopterName);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// adopterVO 也稱為 Domain objects
				adopterVO = new AdopterVO();
				adopterVO.setAdopterNo(rs.getString("ADOPTERNO"));
				adopterVO.setAdopterOccupation(rs.getString("ADOPTEROCCUPATION"));
				adopterVO.setAdopterGender(rs.getString("ADOPTERGENDER"));
				adopterVO.setAdopterMail(rs.getString("ADOPTERMAIL"));
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
		return adopterVO;
	}

}

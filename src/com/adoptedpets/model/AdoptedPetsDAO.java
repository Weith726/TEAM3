package com.adoptedpets.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_AdoptedPets;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

public class AdoptedPetsDAO implements AdoptedPetsDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO ADOPTEDPETS (PETNO, ADOPTERNO, SHELTERNO, PETBREED, ADOPTEDDATE, PETPIC, ADOPTDATE, INTERVIEWDATE, INTERVIEWINFO, PETSPECIES, PETGENDER, ADOPTSTATUS)"
			+ " VALUES ('APET'||LPAD(to_char(ADOPTEDPETS_SEQ.NEXTVAL), 4, '0'),?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE ADOPTEDPETS SET ADOPTERNO = ?, SHELTERNO = ?, PETBREED = ?, ADOPTEDDATE = ?, PETPIC = ?, ADOPTDATE = ?, INTERVIEWDATE = ?, INTERVIEWINFO = ?, PETSPECIES = ?, PETGENDER = ?, ADOPTSTATUS = ?  WHERE PETNO = ?";
	private static final String DELETE = "DELETE FROM ADOPTEDPETS WHERE PETNO = ?";
	private static final String GET_ALL_STMT = "SELECT PETNO, ADOPTERNO, SHELTERNO, PETBREED, ADOPTEDDATE, PETPIC, ADOPTDATE, INTERVIEWDATE, INTERVIEWINFO, PETSPECIES, PETGENDER, ADOPTSTATUS FROM ADOPTEDPETS ORDER BY PETNO ASC";
	private static final String GET_ONE_STMT = "SELECT PETNO, ADOPTERNO, SHELTERNO, PETBREED, ADOPTEDDATE, PETPIC, ADOPTDATE, INTERVIEWDATE, INTERVIEWINFO, PETSPECIES, PETGENDER, ADOPTSTATUS FROM ADOPTEDPETS WHERE PETNO = ?";

	@Override
	public void insert(AdoptedPetsVO adoptedpetsVO) {

//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "EA101", "EA101");

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adoptedpetsVO.getAdopterNo());
			pstmt.setString(2, adoptedpetsVO.getShelterNo());
			pstmt.setString(3, adoptedpetsVO.getPetBreed());
			pstmt.setDate(4, adoptedpetsVO.getAdoptedDate());
			pstmt.setBytes(5, adoptedpetsVO.getPetPic());
			pstmt.setDate(6, adoptedpetsVO.getAdoptDate());
			pstmt.setDate(7, adoptedpetsVO.getInterviewDate());
			pstmt.setString(8, adoptedpetsVO.getInterviewInfo());
			pstmt.setString(9, adoptedpetsVO.getPetSpecies());
			pstmt.setString(10, adoptedpetsVO.getPetGender());
			pstmt.setInt(11, adoptedpetsVO.getAdoptStatus());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public void update(AdoptedPetsVO adoptedpetsVO) {

//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "EA101", "EA101");
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, adoptedpetsVO.getAdopterNo());
			pstmt.setString(2, adoptedpetsVO.getShelterNo());
			pstmt.setString(3, adoptedpetsVO.getPetBreed());

			if (adoptedpetsVO.getAdoptedDate() != null) {
				pstmt.setDate(4, adoptedpetsVO.getAdoptedDate());
			} else {
				pstmt.setNull(4, java.sql.Types.DATE);
			}

			pstmt.setBytes(5, adoptedpetsVO.getPetPic());

			if (adoptedpetsVO.getAdoptDate() != null) {
				pstmt.setDate(6, adoptedpetsVO.getAdoptDate());
			} else {
				pstmt.setNull(6, java.sql.Types.DATE);
			}

			if (adoptedpetsVO.getInterviewDate() != null) {
				pstmt.setDate(7, adoptedpetsVO.getInterviewDate());
			} else {
				pstmt.setNull(7, java.sql.Types.DATE);
			}

			pstmt.setString(8, adoptedpetsVO.getInterviewInfo());
			pstmt.setString(9, adoptedpetsVO.getPetSpecies());
			pstmt.setString(10, adoptedpetsVO.getPetGender());
			pstmt.setInt(11, adoptedpetsVO.getAdoptStatus());
			pstmt.setString(12, adoptedpetsVO.getPetNo());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String petNo) {

//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "EA101", "EA101");
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, petNo);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public AdoptedPetsVO findByPrimaryKey(String petNo) {

		AdoptedPetsVO adoptedpetsVO = null;
		ResultSet rs = null;

//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "EA101", "EA101");
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, petNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// adoptedpetsVO 也稱為 Domain objects
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
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return adoptedpetsVO;
	}

	@Override
	public List<AdoptedPetsVO> getAll() {

		List<AdoptedPetsVO> list = new ArrayList<AdoptedPetsVO>();
		AdoptedPetsVO adoptedpetsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// adoptedpetsVO 也稱為 Domain objects
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
				list.add(adoptedpetsVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}

	@Override
	public List<AdoptedPetsVO> getAll(Map<String, String[]> map) {
		List<AdoptedPetsVO> list = new ArrayList<AdoptedPetsVO>();
		AdoptedPetsVO adoptedpetsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String finalSQL = "SELECT * FROM ADOPTEDPETS  "
					+ jdbcUtil_CompositeQuery_AdoptedPets.get_WhereCondition(map) + "ORDER BY PETNO ASC";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = " + finalSQL);
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
				list.add(adoptedpetsVO); // Store the row in the List
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
}

//	public static void main (String[] args) throws Throwable {
//		
//		AdoptedPetsDAO dao = new AdoptedPetsDAO();
//		AdoptedPetsVO aPets = new AdoptedPetsVO();

// Insert Simulation
//		aPets.setAdopterNo(null);
//		aPets.setShelterNo("S0001");
//		aPets.setPetBreed("米克斯");
//		
//		Calendar cal = new GregorianCalendar();
//	    Date creationDate = cal.getTime();		 
//		java.sql.Date sqlDate = new java.sql.Date(creationDate.getTime());
//		aPets.setAdoptedDate(sqlDate);
//
//		File picIn = new File("D:\\test.jpg");
//		FileInputStream fis = new FileInputStream(picIn);
//		byte[] picTest = new byte [(int) picIn.length()];
//		fis.read(picTest);
//		aPets.setPetPic(picTest);
//		fis.close();
//		
//		aPets.setAdoptDate(null);
//		aPets.setInterviewDate(null);
//		aPets.setInterviewInfo(null);
//		aPets.setPetSpecies("犬");
//		aPets.setPetGender("公");
//		aPets.setAdoptStatus(0);
//		
//		dao.insert(aPets);

// Update Simulation
//		AdoptedPetsVO uaPets = new AdoptedPetsVO();
//		
//		uaPets.setPetNo("APET0012");
//		
//		uaPets.setShelterNo("S0002");
//		
//		Calendar ucal = new GregorianCalendar();
//	    Date ucreationDate = ucal.getTime();		 
//		java.sql.Date usqlDate = new java.sql.Date(ucreationDate.getTime());
//		uaPets.setAdoptedDate(usqlDate);
//		
//		File upicIn = new File("D:\\test1.jpg");
//		FileInputStream ufis = new FileInputStream(upicIn);
//		byte[] upicTest = new byte [(int) upicIn.length()];
//		ufis.read(upicTest);
//		uaPets.setPetPic(upicTest);
//		ufis.close();
//				
//		dao.update(uaPets);		

// Delete Simulation
//		AdoptedPetsVO daPets = new AdoptedPetsVO();
//		
//		daPets.setPetNo("APET0012");
//		
//		dao.delete(daPets.getPetNo());

// FindByPrimaryKey Simulation

//		AdoptedPetsVO gOneaPets = dao.findByPrimaryKey("APET0013");
//		
//		System.out.println(gOneaPets.getPetNo());
//		System.out.println(gOneaPets.getAdopterNo());
//		System.out.println(gOneaPets.getShelterNo());
//		System.out.println(gOneaPets.getPetBreed());
//		System.out.println(gOneaPets.getAdoptedDate());
//		System.out.println(gOneaPets.getPetPic());
//		System.out.println(gOneaPets.getAdoptDate());
//		System.out.println(gOneaPets.getInterviewDate());
//		System.out.println(gOneaPets.getInterviewInfo());
//		System.out.println(gOneaPets.getPetSpecies());
//		System.out.println(gOneaPets.getPetGender());
//		System.out.println(gOneaPets.getAdoptStatus());

// GetAll Simulation

//		List<AdoptedPetsVO> all = dao.getAll();
//
//		for(Iterator it=all.iterator();it.hasNext();) {
//		
//		AdoptedPetsVO a = (AdoptedPetsVO) it.next();
//		System.out.println(a.getPetNo());
//		System.out.println(a.getAdopterNo());
//		System.out.println(a.getShelterNo());
//		System.out.println(a.getPetBreed());
//		System.out.println(a.getAdoptedDate());
//		System.out.println(a.getPetPic());
//		System.out.println(a.getAdoptDate());
//		System.out.println(a.getInterviewDate());
//		System.out.println(a.getInterviewInfo());
//		System.out.println(a.getPetSpecies());
//		System.out.println(a.getPetGender());
//		System.out.println(a.getAdoptStatus());
//		System.out.println("");
//			
//		}
//		
//		
//	}
//	
//}

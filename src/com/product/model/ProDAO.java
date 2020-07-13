package com.product.model;

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

public class ProDAO implements ProDAO_IN{
	
	private static final String INSERT = "INSERT INTO PRODUCT "
			+ "(PRODUCTID,KINDNO,PRODUCTNAME,PRODUCTPRICE,PRODUCTON,PRODUCTSTOCK,PRODUCTSAFE,PRODUCTPIC,PRODUCTINTRO,PRODUCTSTATUS) "
			+ "VALUES (PRODUCT_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?)";
	private static final String GETALL = "SELECT PRODUCTID,KINDNO,PRODUCTNAME,PRODUCTPRICE,to_char(PRODUCTON,'yyyy-mm-dd') PRODUCTON,PRODUCTSTOCK,PRODUCTSAFE,PRODUCTPIC,PRODUCTINTRO,PRODUCTSTATUS "
			+ "FROM PRODUCT ORDER BY PRODUCTID";
	private static final String GETALLFORSHOP = "SELECT PRODUCTID,KINDNO,PRODUCTNAME,PRODUCTPRICE,to_char(PRODUCTON,'yyyy-mm-dd') PRODUCTON,PRODUCTSTOCK,PRODUCTSAFE,PRODUCTPIC,PRODUCTINTRO,PRODUCTSTATUS "
			+ "FROM PRODUCT WHERE PRODUCTSTATUS=1  ORDER BY PRODUCTID";
	private static final String GETCAT = "SELECT PRODUCTID,KINDNO,PRODUCTNAME,PRODUCTPRICE,to_char(PRODUCTON,'yyyy-mm-dd') PRODUCTON,PRODUCTSTOCK,PRODUCTSAFE,PRODUCTPIC,PRODUCTINTRO,PRODUCTSTATUS "
			+ "FROM PRODUCT WHERE KINDNO=1 AND PRODUCTSTATUS=1 ORDER BY PRODUCTID";
	private static final String GETDOG = "SELECT PRODUCTID,KINDNO,PRODUCTNAME,PRODUCTPRICE,to_char(PRODUCTON,'yyyy-mm-dd') PRODUCTON,PRODUCTSTOCK,PRODUCTSAFE,PRODUCTPIC,PRODUCTINTRO,PRODUCTSTATUS "
			+ "FROM PRODUCT  WHERE KINDNO=0 AND PRODUCTSTATUS=1 ORDER BY PRODUCTID";
	private static final String GETANOTHER = "SELECT PRODUCTID,KINDNO,PRODUCTNAME,PRODUCTPRICE,to_char(PRODUCTON,'yyyy-mm-dd') PRODUCTON,PRODUCTSTOCK,PRODUCTSAFE,PRODUCTPIC,PRODUCTINTRO,PRODUCTSTATUS "
			+ "FROM PRODUCT WHERE KINDNO=2 AND PRODUCTSTATUS=1  ORDER BY PRODUCTID";
	private static final String GETONE = "SELECT PRODUCTID,KINDNO,PRODUCTNAME,PRODUCTPRICE,to_char(PRODUCTON,'yyyy-mm-dd') PRODUCTON,PRODUCTSTOCK,PRODUCTSAFE,PRODUCTPIC,PRODUCTINTRO,PRODUCTSTATUS "
			+ "FROM PRODUCT WHERE PRODUCTID=?";
	private static final String UPDATE = "UPDATE PRODUCT SET KINDNO =?, PRODUCTNAME = ?, PRODUCTPRICE = ?, PRODUCTON = ?, PRODUCTSTOCK = ?,  PRODUCTSAFE = ?,"
			+ " PRODUCTPIC = ?, PRODUCTINTRO = ?, PRODUCTSTATUS = ? WHERE PRODUCTID =?";
	private static final String GETSELECT = "SELECT PRODUCTID,KINDNO,PRODUCTNAME,PRODUCTPRICE,to_char(PRODUCTON,'yyyy-mm-dd') PRODUCTON,PRODUCTSTOCK,PRODUCTSAFE,PRODUCTPIC,PRODUCTINTRO,PRODUCTSTATUS "
			+ "FROM PRODUCT WHERE PRODUCTNAME LIKE ?";
	private static final String GETSELECTFORINDEX = "SELECT PRODUCTID,KINDNO,PRODUCTNAME,PRODUCTPRICE,to_char(PRODUCTON,'yyyy-mm-dd') PRODUCTON,PRODUCTSTOCK,PRODUCTSAFE,PRODUCTPIC,PRODUCTINTRO,PRODUCTSTATUS "
			+ "FROM PRODUCT WHERE PRODUCTNAME LIKE ? AND PRODUCTSTATUS=1";
	private static final String GETSELECTDOG = "SELECT PRODUCTID,KINDNO,PRODUCTNAME,PRODUCTPRICE,to_char(PRODUCTON,'yyyy-mm-dd') PRODUCTON,PRODUCTSTOCK,PRODUCTSAFE,PRODUCTPIC,PRODUCTINTRO,PRODUCTSTATUS "
			+ "FROM PRODUCT WHERE PRODUCTNAME LIKE ? AND KINDNO =0 AND PRODUCTSTATUS=1";
	private static final String GETSELECTCAT = "SELECT PRODUCTID,KINDNO,PRODUCTNAME,PRODUCTPRICE,to_char(PRODUCTON,'yyyy-mm-dd') PRODUCTON,PRODUCTSTOCK,PRODUCTSAFE,PRODUCTPIC,PRODUCTINTRO,PRODUCTSTATUS "
			+ "FROM PRODUCT WHERE PRODUCTNAME LIKE ? AND KINDNO=1 AND PRODUCTSTATUS=1";
	private static final String GETSELECTANOTHER = "SELECT PRODUCTID,KINDNO,PRODUCTNAME,PRODUCTPRICE,to_char(PRODUCTON,'yyyy-mm-dd') PRODUCTON,PRODUCTSTOCK,PRODUCTSAFE,PRODUCTPIC,PRODUCTINTRO,PRODUCTSTATUS "
			+ "FROM PRODUCT WHERE PRODUCTNAME LIKE ? AND KINDNO=2 AND PRODUCTSTATUS=1";

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
	public Integer insert(ProVO provo) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Integer seq = null;
		try {
			String[] col ={"PRODUCTID"};
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT,col);
			psmt.setInt(1, provo.getKindno());
			psmt.setString(2, provo.getProductname());
			psmt.setInt(3, provo.getProductprice());
			psmt.setDate(4, provo.getProducton());
			psmt.setInt(5, provo.getProductstock());
			psmt.setInt(6, provo.getProductsafe());
			psmt.setBytes(7, provo.getProductpic());
			psmt.setString(8, provo.getProductintro());
			psmt.setInt(9, provo.getProductstatus());
			psmt.executeUpdate();
			rs = psmt.getGeneratedKeys();
			rs.next();
			seq = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
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
		return seq;
	}

	@Override
	public void update(ProVO provo) {

		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE);
			psmt.setInt(1, provo.getKindno());
			psmt.setString(2, provo.getProductname());
			psmt.setInt(3, provo.getProductprice());
			psmt.setDate(4, provo.getProducton());
			psmt.setInt(5, provo.getProductstock());
			psmt.setInt(6, provo.getProductsafe());
			psmt.setBytes(7, provo.getProductpic());
			psmt.setString(8, provo.getProductintro());
			psmt.setInt(9, provo.getProductstatus());
			psmt.setInt(10, provo.getProductid());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
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
	public ProVO findByPrimaryKey(Integer proid) {

		ProVO provo = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETONE);
			psmt.setInt(1, proid);
			rs = psmt.executeQuery();
			rs.next();
			provo = new ProVO();
			provo.setProductid(rs.getInt(1));
			provo.setKindno(rs.getInt(2));
			provo.setProductname(rs.getString(3));
			provo.setProductprice(rs.getInt(4));
			provo.setProducton(rs.getDate(5));
			provo.setProductstock(rs.getInt(6));
			provo.setProductsafe(rs.getInt(7));
			provo.setProductpic(rs.getBytes(8));
			provo.setProductintro(rs.getString(9));
			provo.setProductstatus(rs.getInt(10));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
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
		return provo;
	}

	@Override
	public List<ProVO> getAll() {

		List<ProVO> list = new ArrayList<ProVO>();
		ProVO provo = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETALL);
			rs = psmt.executeQuery();

			while (rs.next()) {
				provo = new ProVO();
				provo.setProductid(rs.getInt(1));
				provo.setKindno(rs.getInt(2));
				provo.setProductname(rs.getString(3));
				provo.setProductprice(rs.getInt(4));
				provo.setProducton(rs.getDate(5));
				provo.setProductstock(rs.getInt(6));
				provo.setProductsafe(rs.getInt(7));
				provo.setProductpic(rs.getBytes(8));
				provo.setProductintro(rs.getString(9));
				provo.setProductstatus(rs.getInt(10));
				list.add(provo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
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
	public List<ProVO> getCat() {
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO provo = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETCAT);
			rs = psmt.executeQuery();

			while (rs.next()) {
				provo = new ProVO();
				provo.setProductid(rs.getInt(1));
				provo.setKindno(rs.getInt(2));
				provo.setProductname(rs.getString(3));
				provo.setProductprice(rs.getInt(4));
				provo.setProducton(rs.getDate(5));
				provo.setProductstock(rs.getInt(6));
				provo.setProductsafe(rs.getInt(7));
				provo.setProductpic(rs.getBytes(8));
				provo.setProductintro(rs.getString(9));
				provo.setProductstatus(rs.getInt(10));
				list.add(provo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
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
	public List<ProVO> getDog() {
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO provo = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETDOG);
			rs = psmt.executeQuery();

			while (rs.next()) {
				provo = new ProVO();
				provo.setProductid(rs.getInt(1));
				provo.setKindno(rs.getInt(2));
				provo.setProductname(rs.getString(3));
				provo.setProductprice(rs.getInt(4));
				provo.setProducton(rs.getDate(5));
				provo.setProductstock(rs.getInt(6));
				provo.setProductsafe(rs.getInt(7));
				provo.setProductpic(rs.getBytes(8));
				provo.setProductintro(rs.getString(9));
				provo.setProductstatus(rs.getInt(10));
				list.add(provo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
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
	public List<ProVO> getAnother() {
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO provo = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETANOTHER);
			rs = psmt.executeQuery();

			while (rs.next()) {
				provo = new ProVO();
				provo.setProductid(rs.getInt(1));
				provo.setKindno(rs.getInt(2));
				provo.setProductname(rs.getString(3));
				provo.setProductprice(rs.getInt(4));
				provo.setProducton(rs.getDate(5));
				provo.setProductstock(rs.getInt(6));
				provo.setProductsafe(rs.getInt(7));
				provo.setProductpic(rs.getBytes(8));
				provo.setProductintro(rs.getString(9));
				provo.setProductstatus(rs.getInt(10));
				list.add(provo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
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
	public List<ProVO> getSelect(String name) {
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO provo = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETSELECT);
			psmt.setString(1, "%" + name + "%");
			rs = psmt.executeQuery();
			while (rs.next()) {
				provo = new ProVO();
				provo.setProductid(rs.getInt(1));
				provo.setKindno(rs.getInt(2));
				provo.setProductname(rs.getString(3));
				provo.setProductprice(rs.getInt(4));
				provo.setProducton(rs.getDate(5));
				provo.setProductstock(rs.getInt(6));
				provo.setProductsafe(rs.getInt(7));
				provo.setProductpic(rs.getBytes(8));
				provo.setProductintro(rs.getString(9));
				provo.setProductstatus(rs.getInt(10));
				list.add(provo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
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
	public List<ProVO> getSelectdog(String name) {
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO provo = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETSELECTDOG);
			psmt.setString(1, "%" + name + "%");
			rs = psmt.executeQuery();
			while (rs.next()) {
				provo = new ProVO();
				provo.setProductid(rs.getInt(1));
				provo.setKindno(rs.getInt(2));
				provo.setProductname(rs.getString(3));
				provo.setProductprice(rs.getInt(4));
				provo.setProducton(rs.getDate(5));
				provo.setProductstock(rs.getInt(6));
				provo.setProductsafe(rs.getInt(7));
				provo.setProductpic(rs.getBytes(8));
				provo.setProductintro(rs.getString(9));
				provo.setProductstatus(rs.getInt(10));
				list.add(provo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
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
	public List<ProVO> getSelectcat(String name) {
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO provo = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETSELECTCAT);
			psmt.setString(1, "%" + name + "%");
			rs = psmt.executeQuery();
			while (rs.next()) {
				provo = new ProVO();
				provo.setProductid(rs.getInt(1));
				provo.setKindno(rs.getInt(2));
				provo.setProductname(rs.getString(3));
				provo.setProductprice(rs.getInt(4));
				provo.setProducton(rs.getDate(5));
				provo.setProductstock(rs.getInt(6));
				provo.setProductsafe(rs.getInt(7));
				provo.setProductpic(rs.getBytes(8));
				provo.setProductintro(rs.getString(9));
				provo.setProductstatus(rs.getInt(10));
				list.add(provo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
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
	public List<ProVO> getSelectanother(String name) {
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO provo = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETSELECTANOTHER);
			psmt.setString(1, "%" + name + "%");
			rs = psmt.executeQuery();
			while (rs.next()) {
				provo = new ProVO();
				provo.setProductid(rs.getInt(1));
				provo.setKindno(rs.getInt(2));
				provo.setProductname(rs.getString(3));
				provo.setProductprice(rs.getInt(4));
				provo.setProducton(rs.getDate(5));
				provo.setProductstock(rs.getInt(6));
				provo.setProductsafe(rs.getInt(7));
				provo.setProductpic(rs.getBytes(8));
				provo.setProductintro(rs.getString(9));
				provo.setProductstatus(rs.getInt(10));
				list.add(provo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
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
	public List<ProVO> getAllforShop() {
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO provo = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETALLFORSHOP);
			rs = psmt.executeQuery();

			while (rs.next()) {
				provo = new ProVO();
				provo.setProductid(rs.getInt(1));
				provo.setKindno(rs.getInt(2));
				provo.setProductname(rs.getString(3));
				provo.setProductprice(rs.getInt(4));
				provo.setProducton(rs.getDate(5));
				provo.setProductstock(rs.getInt(6));
				provo.setProductsafe(rs.getInt(7));
				provo.setProductpic(rs.getBytes(8));
				provo.setProductintro(rs.getString(9));
				provo.setProductstatus(rs.getInt(10));
				list.add(provo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
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
	public List<ProVO> getSelectForIndex(String name) {
		List<ProVO> list = new ArrayList<ProVO>();
		ProVO provo = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GETSELECTFORINDEX);
			psmt.setString(1, "%" + name + "%");
			rs = psmt.executeQuery();
			while (rs.next()) {
				provo = new ProVO();
				provo.setProductid(rs.getInt(1));
				provo.setKindno(rs.getInt(2));
				provo.setProductname(rs.getString(3));
				provo.setProductprice(rs.getInt(4));
				provo.setProducton(rs.getDate(5));
				provo.setProductstock(rs.getInt(6));
				provo.setProductsafe(rs.getInt(7));
				provo.setProductpic(rs.getBytes(8));
				provo.setProductintro(rs.getString(9));
				provo.setProductstatus(rs.getInt(10));
				list.add(provo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
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

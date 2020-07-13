package com.HotelRoomType.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.HotelRoom.model.HotelRoomVO;

public class HotelRoomTypeDAO implements HotelRoomTypeDAO_interface {
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

	// 新增
	private static final String INSERT_STMT = "INSERT INTO HOTELROOMTYPE (roomTypeNo,roomTypeName,roomTypeDescribe,roomTypeSpace,roomTypePrice,roomTypeService,roomTypeStatus,roomTypePic) VALUES ('RT'||LPAD(to_char(HOTELROOMTYPE_SEQ.NEXTVAL), 3, '0'),?,?,?,?,?,?,?)";
	// 修改
	private static final String UPDATE_STMT = "UPDATE HOTELROOMTYPE SET roomTypeName=?,roomTypeDescribe=?,roomTypeSpace=?,roomTypePrice=?,roomTypeService=?,roomTypeStatus=?,roomTypePic=? WHERE roomTypeNo=? ";
	// 刪除
	private static final String DELETE_ROOM = "DELETE FROM HOTELROOM WHERE roomTypeNo=?";
	private static final String DELETE_ROONTYPE = "DELETE FROM HOTELROOMTYPE WHERE roomTypeNo=?";
	private static final String DELETE_HOTELORDER = "DELETE FROM HOTELORDER WHERE roomTypeNo=?";
	// 查詢(單筆)
	private static final String GET_ONE_STMT = "SELECT * FROM HOTELROOMTYPE WHERE roomTypeNo=?";
	// 查詢(多筆)
	private static final String GET_ALL_STMT = "SELECT * FROM HOTELROOMTYPE ORDER BY roomTypeNo";
	// 查詢該房型所對應的所有房間
	private static final String GET_ROOMS_BY_RTID_STMT = "SELECT * FROM HOTELROOM WHERE roomTypeNo=?";
	// 查詢照片
	private static final String GET_ROOMTYPEPIC_STMT = "SELECT roomTypePic FROM HotelRoomType WHERE roomType=?";

	@Override
	public void insert(HotelRoomTypeVO hotelRoomTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, hotelRoomTypeVO.getRoomTypeName());
			pstmt.setString(2, hotelRoomTypeVO.getRoomTypeDescribe());
			pstmt.setString(3, hotelRoomTypeVO.getRoomTypeSpace());
			pstmt.setInt(4, hotelRoomTypeVO.getRoomTypePrice());
			pstmt.setString(5, hotelRoomTypeVO.getRoomTypeService());
			pstmt.setInt(6, hotelRoomTypeVO.getRoomTypeStatus());
			pstmt.setBytes(7, hotelRoomTypeVO.getRoomTypePic());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
	public void update(HotelRoomTypeVO hotelRoomTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, hotelRoomTypeVO.getRoomTypeName());
			pstmt.setString(2, hotelRoomTypeVO.getRoomTypeDescribe());
			pstmt.setString(3, hotelRoomTypeVO.getRoomTypeSpace());
			pstmt.setInt(4, hotelRoomTypeVO.getRoomTypePrice());
			pstmt.setString(5, hotelRoomTypeVO.getRoomTypeService());
			pstmt.setInt(6, hotelRoomTypeVO.getRoomTypeStatus());
			pstmt.setBytes(7, hotelRoomTypeVO.getRoomTypePic());
			pstmt.setString(8, hotelRoomTypeVO.getRoomTypeNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
	public void delete(String roomTypeNo) {

		int updateCount_ROOM = 0;
		int updateCount_ORDER = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除房間
			pstmt = con.prepareStatement(DELETE_ROOM);
			pstmt.setString(1, roomTypeNo);
			updateCount_ROOM = pstmt.executeUpdate();

			// 再刪除旅館訂單
			pstmt = con.prepareStatement(DELETE_HOTELORDER);
			pstmt.setString(1, roomTypeNo);
			updateCount_ORDER = pstmt.executeUpdate();

			// 最後再刪除房型
			pstmt = con.prepareStatement(DELETE_ROONTYPE);
			pstmt.setString(1, roomTypeNo);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println(
					"刪除房型編號 :" + roomTypeNo + "時,共有房間 :" + updateCount_ROOM + "間同時被刪除" + updateCount_ORDER + "筆訂單被刪除");

		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException se2) {
					throw new RuntimeException("rollback error occured. " + se2.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
	public HotelRoomTypeVO findByPrimaryKey(String roomTypeNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HotelRoomTypeVO hotelRoomTypeVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, roomTypeNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				hotelRoomTypeVO = new HotelRoomTypeVO();
				hotelRoomTypeVO.setRoomTypeNo(rs.getString("roomTypeNo"));
				hotelRoomTypeVO.setRoomTypeName(rs.getString("roomTypeName"));
				hotelRoomTypeVO.setRoomTypeDescribe(rs.getString("roomTypeDescribe"));
				hotelRoomTypeVO.setRoomTypeSpace(rs.getString("roomTypeSpace"));
				hotelRoomTypeVO.setRoomTypePrice(rs.getInt("roomTypePrice"));
				hotelRoomTypeVO.setRoomTypeService(rs.getString("roomTypeService"));
				hotelRoomTypeVO.setRoomTypeStatus(rs.getInt("roomTypeStatus"));
				hotelRoomTypeVO.setRoomTypePic(rs.getBytes("roomTypePic"));
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
			if (pstmt != null) {
				try {
					pstmt.close();
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

		return hotelRoomTypeVO;
	}

	@Override
	public List<HotelRoomTypeVO> getAll() {
		List<HotelRoomTypeVO> list = new ArrayList<HotelRoomTypeVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HotelRoomTypeVO hotelRoomTypeVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				hotelRoomTypeVO = new HotelRoomTypeVO();
				hotelRoomTypeVO.setRoomTypeNo(rs.getString("roomTypeNo"));
				hotelRoomTypeVO.setRoomTypeName(rs.getString("roomTypeName"));
				hotelRoomTypeVO.setRoomTypeDescribe(rs.getString("roomTypeDescribe"));
				hotelRoomTypeVO.setRoomTypeSpace(rs.getString("roomTypeSpace"));
				hotelRoomTypeVO.setRoomTypePrice(rs.getInt("roomTypePrice"));
				hotelRoomTypeVO.setRoomTypeService(rs.getString("roomTypeService"));
				hotelRoomTypeVO.setRoomTypeStatus(rs.getInt("roomTypeStatus"));
				hotelRoomTypeVO.setRoomTypePic(rs.getBytes("roomTypePic"));
				list.add(hotelRoomTypeVO);
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
			if (pstmt != null) {
				try {
					pstmt.close();
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

	public static InputStream getPictureStream(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}

	@Override
	public Set<HotelRoomVO> getRoomsBy_RTID(String roomTypeNo) {

		Set<HotelRoomVO> set = new LinkedHashSet<HotelRoomVO>();

		HotelRoomVO hotelRoomVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ROOMS_BY_RTID_STMT);

			pstmt.setString(1, roomTypeNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				hotelRoomVO = new HotelRoomVO();
				hotelRoomVO.setRoomNo(rs.getInt("roomNo"));
				hotelRoomVO.setRoomTypeNo(rs.getString("roomTypeNo"));
				hotelRoomVO.setPetNo(rs.getString("petNo"));
				hotelRoomVO.setRoomStatus(rs.getInt("roomStatus"));
				set.add(hotelRoomVO);
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
			if (pstmt != null) {
				try {
					pstmt.close();
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

		return set;
	}

}

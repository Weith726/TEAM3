package com.HotelRoom.model;

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

import com.HotelRoomType.model.HotelRoomTypeVO;

public class HotelRoomDAO implements HotelRoomDAO_interface {
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
	private static final String INSERT_STMT = "INSERT INTO HotelRoom (roomNo,roomTypeNo,petNo,roomStatus) VALUES (?,?,?,?)";
	// 修改
	private static final String UPDATE_STMT = "UPDATE HotelRoom SET roomTypeNo=?,petNo=?,roomStatus=? WHERE roomNo=?";
	// 刪除
	private static final String DELETE_STMT = "DELETE FROM HotelRoom WHERE roomNo=?";
	// 查詢(單筆)
	private static final String GET_ONE_STMT = "SELECT * FROM HotelRoom WHERE roomNo=?";
	// 查詢(全部)
	private static final String GET_ALL_STMT = "SELECT * FROM HotelRoom ORDER BY roomNo";
	// 從單一房型查相對應的所有房間
	private static final String GETEMPTYROOM = "SELECT * FROM HOTELROOM WHERE roomTypeNo = ?";
	// 變更房間狀態(已存在)
	private static final String STATUS_CHANGE = "UPDATE HotelRoom SET roomStatus=0 WHERE roomNo=?";
	// 變更房間狀態(已移除)
	private static final String STATUS_CANCEL = "UPDATE HotelRoom SET roomStatus=1 WHERE roomNo=?";

	@Override
	public void insert(HotelRoomVO hotelRoomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, hotelRoomVO.getRoomNo()); // 房間編號
			pstmt.setString(2, hotelRoomVO.getRoomTypeNo()); // 房型編號
			pstmt.setString(3, hotelRoomVO.getPetNo()); // 寵物編號
			pstmt.setInt(4, hotelRoomVO.getRoomStatus()); // 房間狀態

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
	public void update(HotelRoomVO hotelRoomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, hotelRoomVO.getRoomTypeNo());
			pstmt.setString(2, hotelRoomVO.getPetNo());
			pstmt.setInt(3, hotelRoomVO.getRoomStatus());
			pstmt.setInt(4, hotelRoomVO.getRoomNo());

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
	public void delete(Integer roomNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, roomNo);

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
	public HotelRoomVO findByPrimaryKey(Integer roomNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HotelRoomVO hotelRoomVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, roomNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				hotelRoomVO = new HotelRoomVO();
				hotelRoomVO.setRoomNo(rs.getInt("roomNo"));
				hotelRoomVO.setRoomTypeNo(rs.getString("roomTypeNo"));
				hotelRoomVO.setPetNo(rs.getString("petNo"));
				hotelRoomVO.setRoomStatus(rs.getInt("roomStatus"));
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
		return hotelRoomVO;
	}

	@Override
	public List<HotelRoomVO> getAll() {

		List<HotelRoomVO> list = new ArrayList<HotelRoomVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HotelRoomVO hotelRoomVO;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				hotelRoomVO = new HotelRoomVO();
				hotelRoomVO.setRoomNo(rs.getInt("roomNo"));
				hotelRoomVO.setRoomTypeNo(rs.getString("roomTypeNo"));
				hotelRoomVO.setPetNo(rs.getString("petNo"));
				hotelRoomVO.setRoomStatus(rs.getInt("roomStatus"));
				list.add(hotelRoomVO);
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

	@Override
	public List<HotelRoomVO> getEmptyRoom(String roomTypeNo) {
		List<HotelRoomVO> list = new ArrayList<HotelRoomVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HotelRoomVO hotelRoomVO;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETEMPTYROOM);
			pstmt.setString(1, roomTypeNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				hotelRoomVO = new HotelRoomVO();
				hotelRoomVO.setRoomNo(rs.getInt("roomNo"));
				hotelRoomVO.setRoomTypeNo(rs.getString("roomTypeNo"));
				hotelRoomVO.setPetNo(rs.getString("petNo"));
				hotelRoomVO.setRoomStatus(rs.getInt("roomStatus"));
				list.add(hotelRoomVO);
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

	@Override
	public void confirm(Integer roomNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(STATUS_CHANGE);

			pstmt.setInt(1, roomNo);

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
	public void cancel(Integer roomNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(STATUS_CANCEL);

			pstmt.setInt(1, roomNo);

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

}

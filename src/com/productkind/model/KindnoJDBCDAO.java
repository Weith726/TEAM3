package com.productkind.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.product.model.ProVO;

public class KindnoJDBCDAO implements KindDAO_IN {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "DAVID";
	private static final String passwd = "123456";

	private final String GETALL = "SELECT * FROM PRODUCTKIND";

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<KindVO> getAll() {

		List<KindVO> list = new ArrayList<KindVO>();
		KindVO kindvo = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(GETALL);
			rs = psmt.executeQuery();
			while (rs.next()) {
				kindvo = new KindVO();
				kindvo.setKindno(rs.getInt(1));
				kindvo.setKindname(rs.getString(2));
				list.add(kindvo);
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
			if (psmt != null) {
				try {
					psmt.close();
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
}

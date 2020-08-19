package test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DBconnection3 {
	private Connection con;
	private Statement st, st2;
	private ResultSet rs,rs2;

	public DBconnection3() {
		try {
			System.out.println("연결성공");
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/AndroidProject?characterEncoding=UTF-8&serverTimezone=UTC", "root",
					"9009908dms");
			// jdbc:mysql://위치:포트번호/데이터베이스 이름?characterEncoding=UTF-8&serverTimezone=UTC,
			// "아이디", "비번"
			st = con.createStatement();
			st2 = con.createStatement();
		} catch (Exception e) {
			System.out.println("데이터베이스 연결 오류: " + e.getMessage());
		}
	}

	public void doprint() {
		try {
			rs = st.executeQuery("select* from eatlist");
			while (rs.next()) {
				String id=rs.getString("_id");
				String print = "id= " + rs.getString("_id");
				
				
				
			}
		} catch (Exception e) {
			System.out.println("출력오류" + e.getMessage());
		}
	}
	public void doprint2() {
		try {
			String date="2020-08-10";
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = formatter.parse(date);
			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			System.out.println(sqlDate);
			st.execute("update eatlist set eatdate="+sqlDate+ " where _id=2");

		} catch (Exception e) {
			System.out.println("출력오류" + e.getMessage());
		}
	}
}

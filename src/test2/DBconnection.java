package test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBconnection {
	private Connection con;
	private Statement st;
	private ResultSet rs;

	public DBconnection() {
		try {
			System.out.println("연결성공");
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://192.168.0.8:3306/AndroidProject?characterEncoding=UTF-8&serverTimezone=UTC", "root",
					"9009908dms");
			// jdbc:mysql://위치:포트번호/데이터베이스 이름?characterEncoding=UTF-8&serverTimezone=UTC,
			// "아이디", "비번"
			st = con.createStatement();

		} catch (Exception e) {
			System.out.println("데이터베이스 연결 오류: " + e.getMessage());
		}
	}


	public void insertuser2(List list) {
		for (int i = 0; i < list.size(); i++) {
			try {
				ArrayList<String> list2 = (ArrayList<String>) list.get(i);
				st.execute("insert into food(name,kcal,once,comercial,home,who) values('" + list2.get(0) + "','"
						+ list2.get(1) + "','" + list2.get(2) + "','" + list2.get(3) + "','" + list2.get(4) + "','original')");
				System.out.println("삽입성공");
			} catch (Exception e) {
				System.out.println("삽입오류" + e.getMessage());
			}
		}

	}

	public void doprint() {
		try {
			rs = st.executeQuery("select* from userid");
			while (rs.next()) {
				String print = "id= " + rs.getString("_id");
				print += ", password= " + rs.getString("password");
				System.out.println(print);
			}
		} catch (Exception e) {
			System.out.println("출력오류" + e.getMessage());
		}
	}
}

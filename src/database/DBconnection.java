package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBconnection {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBconnection() {
		try {
			System.out.println("연결성공");
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=UTC","root","9009908dms");
			//jdbc:mysql://위치:포트번호/데이터베이스 이름?characterEncoding=UTF-8&serverTimezone=UTC, "아이디", "비번"
			st=con.createStatement();
			
			
		}catch(Exception e) {
			System.out.println("데이터베이스 연결 오류: "+e.getMessage());
		}
	}
	
	public boolean isAdmin(String adminid,String adminpassword) {//관리자인지 아닌지 확인용
		try {
			String sql="select * from food where id="+adminid+" and password="+adminpassword;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			System.out.println("데이터 베이스 검색에서 오류"+e.getMessage());
		}
		return false;
		
	}
	public void insertuser(String name,String kcal,String once) {
		try {
			for(int i=0;i<1000;i++) {
			st.execute("insert into food(name,kcal,once) values('"+name+"','"+kcal+"','"+once+"')");
			}
			System.out.println("삽입성공");
		}catch(Exception e) {
			System.out.println("삽입오류"+e.getMessage());
		}
	}
	
	public void doprint() {
		try {
			rs=st.executeQuery("select* from user where id in (1,'me')");
			while(rs.next()) {
				String print="id= "+rs.getString("id");
				print+=", password= "+rs.getString("password");
				System.out.println(print);
			}
		}
		catch(Exception e) {
			System.out.println("출력오류"+e.getMessage());
		}
	}
}

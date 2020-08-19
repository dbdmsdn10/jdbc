package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBconnection2 {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	public DBconnection2() {
		try {
			System.out.println("연결성공2");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("두번째");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb?characterEncoding=UTF-8&serverTimezone=UTC", "root", "9009908dms");
			System.out.println("세번째");
			//jdbc:mysql://위치:포트번호/데이터베이스 이름?characterEncoding=UTF-8&serverTimezone=UTC, "아이디", "비번"
			st=con.createStatement();
			
			
		}catch(Exception e) {
			System.out.println("데이터베이스 연결 오류: "+e.getMessage());
		}
	}
	
	public boolean isAdmin(String adminid) {//관리자인지 아닌지 확인용
		try {
			String sql="select * from userinfo where id='"+adminid+"'";
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
	public void insertuser(String id,String name,String password) {
		try {
			
			st.execute("insert into userinfo values('"+id+"','"+name+"','"+password+"')");
			System.out.println("삽입성공");
		}catch(Exception e) {
			System.out.println("삽입오류"+e.getMessage());
		}
	}
	
	public void doprint() {
		try {
			rs=st.executeQuery("select* from userinfo");
			while(rs.next()) {
                String print="_id= "+rs.getString("id");
                print+=", name= "+rs.getString("name");
                print+=", password= "+rs.getString("password");
                System.out.println(print);
            }
		}
		catch(Exception e) {
			System.out.println("출력오류"+e.getMessage());
		}
	}
}

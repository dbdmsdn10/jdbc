package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Image {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public Image() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=UTC","root","9009908dms");
			//jdbc:mysql://위치:포트번호/데이터베이스 이름?characterEncoding=UTF-8&serverTimezone=UTC, "아이디", "비번"
			st=con.createStatement();
			System.out.println("연결성공");
			
		}catch(Exception e) {
			System.out.println("데이터베이스 연결 오류: "+e.getMessage());
		}
	}
	
	public void createtable() {
		String txtcreate="create table if not exists images(imageid int not null auto_increment primary key, image mediumblob) ";
		try {
			st.executeUpdate(txtcreate);
		}catch(Exception e) {
			System.out.println("생성오류"+e.getMessage());
		}
		finally {
			try {
				if(st!=null) {
					st.close();
				}
			}catch(Exception e){}
		}
	}
	
	public void insertimage() {
		try {
			File image=new File("spacemarine.jpg");
			FileInputStream fin=new FileInputStream(image);
			String txtinsert="insert into images(image) values(?)";
			PreparedStatement pt=con.prepareStatement(txtinsert);
			pt.setBinaryStream(1,fin,(int)image.length());
			pt.executeUpdate();
		}catch(IOException e) {
			System.out.println("파일 가져오기 오류"+e.getMessage());
		}
		catch(Exception e1) {
			System.out.println("데이터베이스 삽입 오류: "+e1.getMessage());
		}
		
	}
	public void querycode() {
		try {
		String query="select image from images limit 1";
		PreparedStatement pt=con.prepareStatement(query);
		ResultSet result=pt.executeQuery();
		result.next();
		
		FileOutputStream fout=new FileOutputStream("clonspacemarin.jpg");
		Blob blob=result.getBlob(1);
		int len=(int) blob.length();
		byte[] buf=blob.getBytes(1, len);
		fout.write(buf,0,len);
		System.out.println("저장성공");
		}catch(IOException e) {
			System.out.println("이미지 파일 저장오류"+e.getMessage());
		}
		catch(SQLException e1) {
			System.out.println("질의 파일 검색오류"+e1.getMessage());
		}
	
	}
	
	
	
	
}

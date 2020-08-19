package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Datafile {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	public Datafile() {
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
		String txtcreate="create table if not exists datafile(fileid int not null auto_increment primary key,filename text ,filecontent mediumblob) ";
		try {
			st=con.createStatement();
			st.executeUpdate(txtcreate);
		}catch(Exception e) {
		System.out.println("생성오류"+e.getMessage());
		}
	}
	
	public void insertfile() {
		try {
			File image=new File("mettable.txt");
			FileInputStream fin=new FileInputStream(image);
			String txtinsert="insert into datafile(filename,filecontent) values(?,?)";
			PreparedStatement pt=con.prepareStatement(txtinsert);
			pt.setString(1,"mettable");
			pt.setBinaryStream(2,fin,(int)image.length());
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
		String query="select filename,filecontent from datafile limit 1";
		PreparedStatement pt=con.prepareStatement(query);
		ResultSet result=pt.executeQuery();
		result.next();
		InputStream in=result.getBinaryStream(2);
		FileOutputStream fout=new FileOutputStream("clonmettable.txt");
		
		byte[] buf=new byte[1024];
		int size=0;
		while((size=in.read(buf))!=-1) {
			fout.write(buf,0,size);
		}
		fout.flush();
		
		System.out.println("저장성공");
		}catch(IOException e) {
			System.out.println("이미지 파일 저장오류"+e.getMessage());
		}
		catch(SQLException e1) {
			System.out.println("질의 파일 검색오류"+e1.getMessage());
		}
	
	}
}

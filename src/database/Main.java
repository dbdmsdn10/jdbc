package database;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//DBconnection connection2=new DBconnection();
		//System.out.println("관리자 여부: "+connection2.isAdmin("여전히"));
		//connection2.insertuser("나는","여전히" ,"시험중");
		//connection2.doprint();
		
		//DBconnection connection2=new DBconnection();
		//------------------------------------------이미지생성
//		Image image=new Image();
//		image.createtable();
//		//image.insertimage();
//		image.querycode();
		//--------------------------------------------파일생성
		Datafile datafile=new Datafile();
		datafile.createtable();
		//datafile.insertfile();
		datafile.querycode();
	}

}

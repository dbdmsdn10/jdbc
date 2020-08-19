package test2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;

//정보는 5번부터
//
//food[x][5]==이름
//food[x][4]==상용인가
//food[x][3]==외식
//food[x][10]==1회 제공량
//food[x][14]==kcal

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBconnection2 connection2 = new DBconnection2();
		// System.out.println("관리자 여부: "+connection2.isAdmin("여전히"));
		// connection2.insertuser("나는","여전히" ,"시험중");
		// connection2.doprint();
		//-------------------------------------------------------
//		String line2 = null;
//		String[][] Food = new String[29866][];
//
		List list = new ArrayList();
		File cal = new File("foodcalory.txt");
		File met2 = new File("mettable.txt");
//		long savelist[];
//		int ii = 0;
		try {
			//-----------------------------------------------------------------------------
//			BufferedReader reader = new BufferedReader(new FileReader(cal));
//			while ((line2 = reader.readLine()) != null) {
//				String[] aa = line2.split("	");
//				Food[ii] = aa;
//				ii++;
//				ArrayList<String> list2=new ArrayList<String>();
//				if (ii >5) {
//					//connection2.insertuser(aa[5], aa[14], aa[10], aa[4], aa[3]);
//					try {
//						
//						double b=Double.parseDouble(aa[14]);
//						if(b<=0) {
//							continue;
//						}
//					}catch(Exception e) {
//						System.out.println("예외");
//						continue;
//					}
//					
//					list2.add(aa[5]);
//					list2.add(aa[14]);
//					list2.add(aa[10]);
//					list2.add(aa[4]);
//					list2.add(aa[3]);
//					list.add(list2);
//				}
//				
//			}
			// ------------------------------------------------------------met table 삽입
			try {
				BufferedReader met3 = new BufferedReader(new FileReader(met2));
				String line;
				ArrayList<String[]> mettablearray = new ArrayList<String[]>();
				while ((line = met3.readLine()) != null) {
					ArrayList<String> list2=new ArrayList<String>();
					String[] a = line.split("	");
					System.out.println(a[0] + "		" + a[1]);
					list2.add(a[0]);
					list2.add(a[1]);
					list.add(list2);
				}

			} catch (IOException q) {// 파일 읽기 오류
				System.out.println("읽기 오류"+q.toString());
			} catch (Exception e) {
				System.out.println("오류");
			}

			// connection2.insertuser2(list);
			connection2.insertuser2(list);
//------------------------------------------------------------------------------
//			Calendar cal1 = Calendar.getInstance();
//			cal1.setTime(new Date());
//	        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
//	        System.out.println("current: " + df.format(cal1.getTime()));
//	        ArrayList<String> list2=new ArrayList();
//	        list2.add(df.format(cal1.getTime()));
//	        for(int i=0;i<365;i++) {
//	        	cal1.add(Calendar.DATE,-1);
//	        	list2.add(df.format(cal1.getTime()));
//	        }
//	        datetest date=new datetest();
//	        date.insertuser2(list2);
//		
//				
//--------------------------------------------------------------------------
		
		} catch (Exception e) {
			System.out.println("오류2"+e.toString());
		}


		
		
	}
}

package second12_21;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//두 대회의 주차이를 반환해주는 클래스
public class Date_Minus {
	float calDateDays;
	float minus;
	//시작대회, 마지막 대회
	 Date_Minus(String end, String start){
		 
		 String date1 = end;
		 String date2 = start;
		    
		 
		    try{ // String Type을 Date Type으로 캐스팅하면서 생기는 예외로 인해 여기서 예외처리 해주지 않으면 컴파일러에서 에러가 발생해서 컴파일을 할 수 없다.
		        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		        // date1, date2 두 날짜를 parse()를 통해 Date형으로 변환.
		        Date FirstDate = format.parse(date1);
		        Date SecondDate = format.parse(date2);
		        
		        // Date로 변환된 두 날짜를 계산한 뒤 그 리턴값으로 long type 변수를 초기화 하고 있다.
		        // 연산결과 -950400000. long type 으로 return 된다.
		        float calDate = FirstDate.getTime() - SecondDate.getTime(); 
		        
		        // Date.getTime() 은 해당날짜를 기준으로1970년 00:00:00 부터 몇 초가 흘렀는지를 반환해준다. 
		        // 이제 24*60*60*1000(각 시간값에 따른 차이점) 을 나눠주면 일수가 나온다.
		        calDateDays = (calDate / ( 24*60*60*1000))/7; 
		 
		        calDateDays = (float) Math.ceil(calDateDays);
		        
		      // System.out.println("두 날짜의 주 차이: "+calDateDays);
		        }
		        catch(ParseException e)
		        {
		            // 예외 처리
		        }


		 
		 
	 }
	 
	 
	 public float get_minues() {
		 //13주~104사이의
		 if(calDateDays>=(float)14 && calDateDays<=(float)104)
			 minus=((91-(calDateDays-13))/91); 
		 else if(calDateDays>=105 )
			 minus=0;
		 else if(calDateDays<=(float)13)
			 minus=1;
			 
			 
		return minus;
		 
		 
		 
	 }
	 /*
	 public static void main(String[] args) {
		 new  Date_Minus();
		 
	 }
	 */

}

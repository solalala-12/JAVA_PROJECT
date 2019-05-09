package second12_21;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//�� ��ȸ�� �����̸� ��ȯ���ִ� Ŭ����
public class Date_Minus {
	float calDateDays;
	float minus;
	//���۴�ȸ, ������ ��ȸ
	 Date_Minus(String end, String start){
		 
		 String date1 = end;
		 String date2 = start;
		    
		 
		    try{ // String Type�� Date Type���� ĳ�����ϸ鼭 ����� ���ܷ� ���� ���⼭ ����ó�� ������ ������ �����Ϸ����� ������ �߻��ؼ� �������� �� �� ����.
		        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		        // date1, date2 �� ��¥�� parse()�� ���� Date������ ��ȯ.
		        Date FirstDate = format.parse(date1);
		        Date SecondDate = format.parse(date2);
		        
		        // Date�� ��ȯ�� �� ��¥�� ����� �� �� ���ϰ����� long type ������ �ʱ�ȭ �ϰ� �ִ�.
		        // ������ -950400000. long type ���� return �ȴ�.
		        float calDate = FirstDate.getTime() - SecondDate.getTime(); 
		        
		        // Date.getTime() �� �ش糯¥�� ��������1970�� 00:00:00 ���� �� �ʰ� �귶������ ��ȯ���ش�. 
		        // ���� 24*60*60*1000(�� �ð����� ���� ������) �� �����ָ� �ϼ��� ���´�.
		        calDateDays = (calDate / ( 24*60*60*1000))/7; 
		 
		        calDateDays = (float) Math.ceil(calDateDays);
		        
		      // System.out.println("�� ��¥�� �� ����: "+calDateDays);
		        }
		        catch(ParseException e)
		        {
		            // ���� ó��
		        }


		 
		 
	 }
	 
	 
	 public float get_minues() {
		 //13��~104������
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

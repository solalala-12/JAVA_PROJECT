package second12_21;

import java.io.*;
import java.util.*;




public class  Decide_contest {


	LinkedHashMap<String,Float>h=new LinkedHashMap<String,Float>();
	LinkedHashMap<Integer,Float>count_h=new LinkedHashMap<Integer,Float>();

	Worldrating_v wr=Worldrating_v.getInstance();
	ArrayList<Float> wpoint_list=wr.get_wv();
	ArrayList<Float> grade_list=new ArrayList<Float>();

	//대회별 포인트를 합산할 sum
	float sum=0;
	//이름
	ArrayList<String> name_list;
	
	//1 박성현
	//2 김지우
	//2 김지현
	
	//순위와 이름을 준다.
	//순위, 이름
	Decide_contest(ArrayList<String[]>  input_list){
		String [] row;
		int grade;
		for(int i=0;i<input_list.size();i++) {
			
			row=input_list.get(i);
			grade=Integer.parseInt(row[0]);
			if(count_h.containsKey(grade))
				count_h.replace(Integer.parseInt(row[0]),count_h.get((grade))+1);
			else
				count_h.put(Integer.parseInt(row[0]),(float) 1);
			
		}

		
		for(int i=0;i<input_list.size();i++) {
			
			row=input_list.get(i);
			//grade에 맞게 worldrating 부여
			grade=Integer.parseInt(row[0]);
			
			
			if(grade==1)
				h.put(row[1], (float)45);
			else if(grade==2)
				h.put(row[1], (float)37);
			else if(grade==3)
				h.put(row[1], (float)32);
			else if(grade==4)
				h.put(row[1], (float)27);
			else if(grade==5)
				h.put(row[1], (float)24);
			else if(grade==6)
				h.put(row[1], (float)21);
			else if(grade==7)
				h.put(row[1], (float)20);
			else if(grade==8)
				h.put(row[1], (float)19);
			else if(grade==9)
				h.put(row[1], (float)18);
			else if(grade==10)
				h.put(row[1], (float)17);
			else if(grade==11)
				h.put(row[1], (float)16);
			else if(grade==12)
				h.put(row[1], (float)15);
			else if(grade==13)
				h.put(row[1], (float)14);
			else if(grade==14)
				h.put(row[1], (float)13);
			else if(grade==15)
				h.put(row[1], (float)12);
			else if(grade>=16 && grade<=30)
				h.put(row[1], (float)11);
			else if(grade>=31 && grade<=34)
				h.put(row[1], (float)10);
			else if(grade>=35 && grade<=38)
				h.put(row[1], (float)9);
			else if(grade>=39 && grade<=43)
				h.put(row[1], (float)8);
			else if(grade>=44 && grade<=50)
				h.put(row[1], (float)7);
			else if(grade>=51 && grade<=55)
				h.put(row[1], (float)6);
			else if(grade>=56 && grade<=60)
				h.put(row[1], (float)5);
			else if(grade>=61 && grade<=70)
				h.put(row[1], (float)4);
			else if(grade>=71 && grade<=80)
				h.put(row[1], (float)3);
			else if(grade>=81 && grade<=100)
				h.put(row[1], (float)2);
			else if(grade>=101 && grade<=200)
				h.put(row[1], (float)1);
			
			
			//만약 동점 순위가 존재한다면..
			//System.out.println("순위 : "+row[1]+" / 부여 받은 포인트 "+h.get(row[1]));
			
		}
		count_h.clear();
		
	}
	


	
	
	public LinkedHashMap<String,Float> get_table() {
		return h;
	}

	
	
	public boolean checkname(String s) {
		if(h.containsKey(s)) {
			return true;
		}
		else
			return false;

	}
	//대회별 포인트 합산 (경쟁력) 
	public void addsum(String s) {
		sum+=h.get(s);

	}
	//getsum
	public float  getsum() {
		return sum;
	}
	//sum초기화
	public void setsum() {
		sum=0;
	}
	
	

	
}

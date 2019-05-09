package second12_21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Rolextable {
	
	//private static Rolextable table=new Rolextable();
	
	//rolex table
	static float ro_point[][]=new float[39][200];
	Rolextable(){

		for(int i=0;i<ro_point.length;i++) {
			try {
		        // csv 데이터 파일
				File csv;
				//전반기 대회 parsing 해쉬테이블할당.
					csv = new File("C:\\Users\\sora\\PycharmProjects\\rolex_table.csv");
					
			        BufferedReader br = new BufferedReader(new FileReader(csv));
			        String line = "";
			        int j=0;
			        while ((line = br.readLine()) != null) {
			            // -1 옵션은 마지막 "," 이후 빈 공백도 읽기 위한 옵션
			        	//System.out.println(line);
			            String[] token = line.split(",", -1);
			            //첫번째 readline은 skip
			            if(j==65)
			            	break;
			            if(token[0].equals(" 0 to 10"))
			            	continue;
			            //없으면 0을 할당.
			            
			            try {
			            if(token[i].equals(""))
			            	ro_point[i][j]=0;
			            else {
			            	//0번
			            	ro_point[i][j]=Float.parseFloat(token[i]);
			            	j++;
			            	continue;
			            }
			            }catch(ArrayIndexOutOfBoundsException e) {
			            	System.out.println("인덱스에러");
			            }
			        }
			        br.close();
			        
			}catch (FileNotFoundException e) {
	        e.printStackTrace();
			} 
			catch (IOException e) {
	        e.printStackTrace();
			}
	    
			
		}
	}
	//대회별 지수에 따라 rolex 테이블 return
	//static float ro_point[][]=new float[39][65];
	public float[] getrolexlit(float a) {
		if(a>=1500)
			return ro_point[38];
		else if(1031<=a && a<=1125)
	        return ro_point[37];
		else if (936<=a)
	        return ro_point[36];
		else if (841<=a)
	        return ro_point[35];
		else if (746<=a)
	        return ro_point[34];
		else if (651<=a)
	        return ro_point[33];
		else if (621<=a)
	        return ro_point[32];
		else if (591<=a)
	        return ro_point[31];
		else if (561<=a)
	        return ro_point[30];
		else if (531<=a)
	        return ro_point[29];
		else if (501<=a)
	        return ro_point[28];
		
		else if (471<=a)
	        return ro_point[27];
		else if (441<=a)
	        return ro_point[26];
		else if (411<=a)
	        return ro_point[25];
		else if (381<=a)
	        return ro_point[24];
		else if (351<=a)
	        return ro_point[23];
		
		else if (321<=a)
	        return ro_point[22];
		else if (291<=a)
	        return ro_point[21];
		else if (261<=a)
	        return ro_point[20];
		else if (231<=a)
	        return ro_point[19];
		else if (201<=a)
	        return ro_point[18];
		
		else if (171<=a)
	        return ro_point[17];
		else if (161<=a)
	        return ro_point[16];
		else if (151<=a)
	        return ro_point[15];
		else if (141<=a)
	        return ro_point[14];
		else if (131<=a)
	        return ro_point[13];
		
		else if (121<=a)
	        return ro_point[12];
		else if (111<=a)
	        return ro_point[11];
		else if (101<=a)
	        return ro_point[10];
		else if (91<=a)
	        return ro_point[9];
		else if (81<=a)
	        return ro_point[8];
		
		else if (71<=a)
	        return ro_point[7];
		else if (61<=a)
	        return ro_point[6];
		else if (51<=a)
	        return ro_point[5];
		else if (41<=a)
	        return ro_point[4];
		else if (31<=a)
	        return ro_point[3];
		
		else if (21<=a)
	        return ro_point[2];
		else if (11<=a)
	        return ro_point[1];
		else if (0<=a)
	        return ro_point[0];
		else
			return ro_point[0];
	}
	
}


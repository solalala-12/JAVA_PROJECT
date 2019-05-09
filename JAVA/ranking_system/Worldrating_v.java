package second12_21;

import java.io.*;
import java.util.*;

public class Worldrating_v {
	
	
	private static Worldrating_v table_wv=new Worldrating_v();
	public static Worldrating_v getInstance() {
		return table_wv;
	}
	ArrayList<Float> rating_p = new ArrayList<Float>();

	Worldrating_v() {

		try {
			File csv;
			// csv 데이터 파일
			csv = new File("C:\\Users\\sora\\PycharmProjects\\java_klpga\\exam\\k_rating2014.csv");

			// 2015년도의 최종 순위를 가져온다.

			BufferedReader br = new BufferedReader(new FileReader(csv));
			String line = "";
			int row = 0, i;

			while ((line = br.readLine()) != null) {
				// -1 옵션은 마지막 "," 이후 빈 공백도 읽기 위한 옵션
				// System.out.println(line);
				String[] token = line.split(",", -1);
				// 전년도 기준 순위, worldratingvalue
				if (token[1].equals("이름"))
					continue;

				rating_p.add(Float.parseFloat(token[2]));
			}

			br.close();
			System.out.println(rating_p.size());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Float> get_wv(){
		
		return rating_p;
	}
	/*
	public static void main(String[] args) {
		new Worldrating_v();
		
		
		
	}
	*/
}

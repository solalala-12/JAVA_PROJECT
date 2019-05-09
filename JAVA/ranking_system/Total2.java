package second12_21;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Total2 {
	// 최소 대회 획득 포인트
		static final int MINGET_P = 25;
		//기간별 감가 지수
		float MINUS;
		
		ArrayList<String> major_list = new ArrayList<String>(
				//2015 메이저 11, 16, 21, 26
				//2016 메이저  13, 24, 27, 29
				//2017 메이저 12,21,22,27,29
				// 2018 메이저 5,12,20,24,26
				Arrays.asList("2015_klpga_Sa11", "2015_klpga_Sa16", "2015_klpga_Sa21", "2015_klpga_Sa26",
						"2016_klpga_Sa13", "2016_klpga_Sa24", "2016_klpga_Sa27", "2016_klpga_Sa29",
						"2017_klpga_Sa12", "2017_klpga_Sa21", "2017_klpga_Sa22", "2017_klpga_Sa27", "2017_klpga_Sa29",
						//2018 메이저
						"2018_klpga_Sa5","2018_klpga_Sa12","2018_klpga_Sa20","2018_klpga_Sa24","2018_klpga_Sa26"));
		ArrayList<String> contest_2017 = new ArrayList<String>(Arrays.asList("2015_klpga_Sa1","2015_klpga_Sa2","2015_klpga_Sa3","2015_klpga_Sa4","2015_klpga_Sa5"
				,"2015_klpga_Sa6","2015_klpga_Om7","2015_klpga_Sa8","2015_klpga_Sa9","2015_klpga_Sa10","2015_klpga_Sa11","2015_klpga_Sa12","2015_klpga_Sa13"
				,"2015_klpga_Sa14","2015_klpga_Sa15","2015_klpga_Sa16","2015_klpga_Sa17","2015_klpga_Sa18","2015_klpga_Sa19"
				,"2015_klpga_Sa20","2015_klpga_Sa21","2015_klpga_Sa22","2015_klpga_Sa23","2015_klpga_Sa24","2015_klpga_Sa26",
				"2015_klpga_Sa27","2015_klpga_Sa28","2015_klpga_Sa29","2015_klpga_Sa31",
				//2016
				"2016_klpga_Sa1","2016_klpga_Sa2","2016_klpga_Sa3","2016_klpga_Sa4","2016_klpga_Sa5"
				,"2016_klpga_Sa6","2016_klpga_Sa7","2016_klpga_Sa8","2016_klpga_Om9","2016_klpga_Sa10","2016_klpga_Sa11","2016_klpga_Sa12","2016_klpga_Sa13"
				,"2016_klpga_Sa14","2016_klpga_Sa15","2016_klpga_Sa16","2016_klpga_Sa17","2016_klpga_Sa18","2016_klpga_Sa19"
				,"2016_klpga_Sa20","2016_klpga_Sa21","2016_klpga_Sa22","2016_klpga_Sa23","2016_klpga_Sa24","2016_klpga_Sa25","2016_klpga_Sa26",
				"2016_klpga_Sa27","2016_klpga_Sa29","2016_klpga_Sa30","2016_klpga_Sa31","2016_klpga_Sa32","2016_klpga_Sa34",
				"2017_klpga_Sa1", "2017_klpga_Sa2", "2017_klpga_Sa3",
				//2017
				"2017_klpga_Sa4", "2017_klpga_Sa5", "2017_klpga_Sa6", "2017_klpga_Sa7", "2017_klpga_Om8","2017_klpga_Om9", "2017_klpga_Sa10", "2017_klpga_Sa11",
				"2017_klpga_Sa12", "2017_klpga_Sa13", "2017_klpga_Sa14", "2017_klpga_Sa15", "2017_klpga_Sa16", "2017_klpga_Sa17", "2017_klpga_Sa18",
				"2017_klpga_Sa19", "2017_klpga_Sa20", "2017_klpga_Sa21", "2017_klpga_Sa22", "2017_klpga_Sa23", "2017_klpga_Sa24",
				"2017_klpga_Sa27", "2017_klpga_Sa28", "2017_klpga_Sa29", "2017_klpga_Sa30", "2017_klpga_Sa32",
				//2018
				"2018_klpga_Sa1", "2018_klpga_Sa2", "2018_klpga_Sa3",
				"2018_klpga_Sa4", "2018_klpga_Sa5", "2018_klpga_Sa6", "2018_klpga_Sa7", "2018_klpga_Om8","2018_klpga_Sa9", "2018_klpga_Sa10", "2018_klpga_Sa11",
				"2018_klpga_Sa12", "2018_klpga_Sa13", "2018_klpga_Sa14", "2018_klpga_Sa15", "2018_klpga_Sa16", "2018_klpga_Sa17", "2018_klpga_Sa18",
				"2018_klpga_Sa19", "2018_klpga_Sa20", "2018_klpga_Sa21", "2018_klpga_Sa22", "2018_klpga_Sa23", "2018_klpga_Sa24", "2018_klpga_Sa26",
				"2018_klpga_Sa27", "2018_klpga_Sa28"));
		ArrayList<String> contestm_2017 = new ArrayList<String>(Arrays.asList("2015_klpga_Om1","2015_klpga_Om2","2015_klpga_Om3","2015_klpga_Om4","2015_klpga_Om5"
				,"2015_klpga_Om6","2015_klpga_Om7","2015_klpga_Om8","2015_klpga_Om9","2015_klpga_Om10","2015_klpga_Om11","2015_klpga_Om12","2015_klpga_Om13"
				,"2015_klpga_Om14","2015_klpga_Om15","2015_klpga_Om16","2015_klpga_Om17","2015_klpga_Om18","2015_klpga_Om19"
				,"2015_klpga_Om20","2015_klpga_Om21","2015_klpga_Om22","2015_klpga_Om23","2015_klpga_Om24","2015_klpga_Om26",
				"2015_klpga_Om27","2015_klpga_Om28","2015_klpga_Om29","2015_klpga_Om31",
				//2016
				"2016_klpga_Om1","2016_klpga_Om2","2016_klpga_Om3","2016_klpga_Om4","2016_klpga_Om5"
				,"2016_klpga_Om6","2016_klpga_Om7","2016_klpga_Om8","2016_klpga_Om9","2016_klpga_Om10","2016_klpga_Om11","2016_klpga_Om12","2016_klpga_Om13"
				,"2016_klpga_Om14","2016_klpga_Om15","2016_klpga_Om16","2016_klpga_Om17","2016_klpga_Om18","2016_klpga_Om19"
				,"2016_klpga_Om20","2016_klpga_Om21","2016_klpga_Om22","2016_klpga_Om23","2016_klpga_Om24","2016_klpga_Om25","2016_klpga_Om26",
				"2016_klpga_Om27","2016_klpga_Om29","2016_klpga_Om30","2016_klpga_Om31","2016_klpga_Om32","2016_klpga_Om34",
				"2017_klpga_Om1", "2017_klpga_Om2", "2017_klpga_Om3",
				//2017
				"2017_klpga_Om4", "2017_klpga_Om5", "2017_klpga_Om6", "2017_klpga_Om7", "2017_klpga_Om8","2017_klpga_Om9", "2017_klpga_Om10", "2017_klpga_Om11",
				"2017_klpga_Om12", "2017_klpga_Om13", "2017_klpga_Om14", "2017_klpga_Om15", "2017_klpga_Om16", "2017_klpga_Om17", "2017_klpga_Om18",
				"2017_klpga_Om19", "2017_klpga_Om20", "2017_klpga_Om21", "2017_klpga_Om22", "2017_klpga_Om23", "2017_klpga_Om24",
				"2017_klpga_Om27", "2017_klpga_Om28", "2017_klpga_Om29", "2017_klpga_Om30", "2017_klpga_Om32",
				//2018
				"2018_klpga_Om1", "2018_klpga_Om2", "2018_klpga_Om3",
				"2018_klpga_Om4", "2018_klpga_Om5", "2018_klpga_Om6", "2018_klpga_Om7", "2018_klpga_Om8","2018_klpga_Om9", "2018_klpga_Om10", "2018_klpga_Om11",
				"2018_klpga_Om12", "2018_klpga_Om13", "2018_klpga_Om14", "2018_klpga_Om15", "2018_klpga_Om16", "2018_klpga_Om17", "2018_klpga_Om18",
				"2018_klpga_Om19", "2018_klpga_Om20", "2018_klpga_Om21", "2018_klpga_Om22", "2018_klpga_Om23", "2018_klpga_Om24", "2018_klpga_Om26",
				"2018_klpga_Om27", "2018_klpga_Om28"));

	// 파일 입출력
		File csv;
		BufferedReader br;
		String line = "";
		// 선수들의 포인트 획득 횟수
		LinkedHashMap<String, Float> count_p = new LinkedHashMap<String, Float>();
		// 대회 지수를 판달할 base_hash
		LinkedHashMap<String, Float> h = new LinkedHashMap<String, Float>();

		// 대회 지수를 판달할 base_hash
		LinkedHashMap<String, Float> input_h = new LinkedHashMap<String, Float>();
		

		// 대회 지수를 판달할 base_hash
		LinkedHashMap<String, Integer> player_h = new LinkedHashMap<String, Integer>();
		
		
		//input 의 순위와 상금
		ArrayList<String[]> input_list=new	ArrayList<String[]>();
		
		
		// 이름
		ArrayList<String> name_list = new ArrayList<String>();

		//대회지수를 판단할 class
		Decide_contest decide_contest;
	
		
		LinkedHashMap<String, Float> totaltable = new LinkedHashMap<String, Float>();
		//대회 리스트와 대회 지수 저장할 TABLE
		LinkedHashMap<String, Float> gradehash = new LinkedHashMap<String, Float>();
		

		//대회 리스트와 대회 지수 저장할 TABLE
		LinkedHashMap<String, Float> newone = new LinkedHashMap<String, Float>();
		//로렉스 table
		Rolextable rolex_point = new Rolextable();
		// 선수들의 score 저장
		ArrayList<Integer> score_list = new ArrayList<Integer>();
		// 동일 순위 포인트 합계
		LinkedHashMap<Integer, Float> h2 = new LinkedHashMap<Integer, Float>();
		// 동일 순위와 합계
		LinkedHashMap<Integer, Integer> h3 = new LinkedHashMap<Integer, Integer>();
		
		//이름 카운트 누적포인트
		ArrayList<String []> player_list=new ArrayList<String []>();
		
		//대회별 저장
		ArrayList<String[]> save_table=new ArrayList<String[]>();
		
		
		//순위와 누적 평균 포인트
		Vector<Float> v = new Vector<Float>();
		
		//대회 이름과, 대회 날짜
		LinkedHashMap<String, String> date_h = new LinkedHashMap<String, String>();
		
	
	Total2(){
		
		try {
			csv = new File("C:\\Users\\sora\\PycharmProjects\\java_klpga\\exam\\date.csv");
			br = new BufferedReader(new FileReader(csv));
			while ((line = br.readLine()) != null) {
			String[] token = line.split(",", -1);
			date_h.put(token[0],token[1]);
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		
		try {
			
		
		csv = new File("C:\\Users\\sora\\PycharmProjects\\java_klpga\\exam\\k_rating2014.csv");
		br = new BufferedReader(new FileReader(csv));
		

		while ((line = br.readLine()) != null) {
			// -1 옵션은 마지막 "," 이후 빈 공백도 읽기 위한 옵션
			// System.out.println(line);
			String[] token = line.split(",", -1);
			// 전년도 기준 순위, worldratingvalue
			if (token[0].equals("순위"))
				continue;
			
			//순위와 이름을 넣어준다.
			input_list.add(new String []{token[0],token[1]});
			

		}
		br.close();
		//System.out.print("==="+input_list.size());
		decide_contest = new Decide_contest(input_list);
		// 베이스 해쉬를 넣고,

		name_list.clear();
		input_list.clear();
		
		//System.out.print(contest_2017.size());==117
		//System.out.println("★★★★2017 대회지수★★★★");
		Float grade=(float) 0;
		//for (int i = 0; i<12; i++) {
		for (int i = 0; i<contest_2017.size(); i++) {
			
			int count=0;
			
			
			//현재 대회의 이전 대회까지의 기간별 감가 선수 포인트 부여
			/*==============================================================*/
			
			
			for(int b=0;b<i;b++) {
				
			//System.out.print(i+"  , "+b);
			
				try {
		
					
					//i와 j 의 차이
					//start end
					Date_Minus dategap=new Date_Minus(date_h.get(contest_2017.get(i)),date_h.get(contest_2017.get(b)));
					MINUS=dategap.get_minues();
					//System.out.println(MINUS);
					float sum = 0;
					csv = new File(
							"C:\\Users\\sora\\PycharmProjects\\java_klpga\\exam\\total\\" + contest_2017.get(b) + ".csv");
					br = new BufferedReader(new FileReader(csv));
					float[] rtt;
					while ((line = br.readLine()) != null) {
						// -1 옵션은 마지막 "," 이후 빈 공백도 읽기 위한 옵션
						// System.out.println(line);
						String[] token = line.split(",", -1);
						// 대회별 선수 명단.
						if (token[0].equals("순위"))
							continue;
						//순위 이름
						name_list.add(token[1]);
						//count_p.put(token[1], (float) 1);
						
					
					}
					
					br.close();
				
					// 각 대회가 끝날 때 마다 대회 경쟁력 다시 계산.

					String name;
					/*
				
					// 각 대회가 끝날 때 마다 대회 경쟁력 다시 계산.
					for (int j = 0; j < name_list.size(); j++) {
						name = name_list.get(j);
						// 현대회의 선수가 worlrating을 부여 받았다면
						if (decide_contest.checkname(name)) {
							decide_contest.addsum(name);
							//System.out.println(name+" : "+decide_contest.getsum());
							count++;
							
						}
					} // for문 끝
					//System.out.println("선수 count : "+count);
					
						// 2015년의 대회 지수 출력
						 * 
						 * 
						 */
					
					grade =gradehash.get(contest_2017.get(b));
					//gradehash.put(contest_2017.get(i), grade);
					System.out.print(" "+grade);
		
					// 대회 지수 결정 끝

					// 메이저 리그이면
					if (major_list.contains(contest_2017.get(b)))
						rtt = rolex_point.getrolexlit(1500);

					else
						rtt = rolex_point.getrolexlit(grade);
					int lens=getlens(grade);

			
					decide_contest.setsum();
					name_list.clear();
					
					
					csv = new File("C:\\Users\\sora\\PycharmProjects\\java_klpga\\exam\\total\\"+contestm_2017.get(b)+".csv");
					
					name_list=new ArrayList<String>();
					
					br = new BufferedReader(new FileReader(csv));

				     while ((line = br.readLine()) != null) {
				       // -1 옵션은 마지막 "," 이후 빈 공백도 읽기 위한 옵션
				       //System.out.println(line);
				            String[] token1 = line.split(",", -1);
				            //대회별 선수 명단. 
				            if(token1[1].equals("이름"))
								continue;
				            
				            if(Integer.parseInt(token1[0])<=lens) {
				            	 name_list.add(token1[1]);
						            //순위
						            //있는등수 모두 클리어
						            score_list.add(Integer.parseInt(token1[0]));
						            
						            if(h3.containsKey(Integer.parseInt(token1[0]))) {
					        			//그score key값에 로렉스의 값을 더한다.

					        			h3.replace(Integer.parseInt(token1[0]), h3.get(Integer.parseInt(token1[0]))+1);
					        			
					        			
					        		}
						            else
						            	h3.put(Integer.parseInt(token1[0]), 1);

						            
						            //System.out.print(token[0]+ " ");
						            }
						        
				            	
				            }
				           

				        br.close();
				        
				        
				        String row[];
						for (int j = 0; j < name_list.size(); j++) {

						
								
								if (h2.containsKey(score_list.get(j))) {
									// 그score key값에 로렉스의 값을 더한다.
									sum = h2.get(score_list.get(j)) + rtt[j]*MINUS;
									h2.replace(score_list.get(j), sum);


								}

								// 1 rtt[1];
								else
									h2.put(score_list.get(j), rtt[j]*MINUS);
								
								//포인트 획득 했으면
								if (rtt[j]*MINUS != (float) 0) {
									if (count_p.containsKey(name_list.get(j))) {
										count_p.replace(name_list.get(j), count_p.get(name_list.get(j)) + 1);

										//포인트는 획득했는데 처음이라면,....
									} else
										count_p.put(name_list.get(j), (float) 1);
									
									
									
								}
								//0이면 부여못받은 걸로 ,
								else
									if(count_p.containsKey(name_list.get(j))==false)
										count_p.put(name_list.get(j), (float) 0);

								
								h.put(name_list.get(j), rtt[j]);
							}

						
						
						Set key = h3.keySet();
						Iterator it = key.iterator();

						
						// h3 키와 h2키 같은값을 h3 value값으로 나눈다.

						// System.out.println(contestm_2015[i]+" 중복된 카운트");
						while (it.hasNext()) {

							Integer temp = (Integer) it.next();
							float f = h2.get(temp);
							// mean
							float mean = f / h3.get(temp);
							for (int j = 0; j < h3.get(temp); j++) {
								v.add(mean);
							}
						}
						// 로렉스 포인트 부여.
						for (int k = 0; k < v.size(); k++) {
							h.replace(name_list.get(k), v.get(k));

						}
						
			
						refresh_table(h);
						//순위 이름 
						//array list <string[] > 
					
						Set key1 = totaltable.keySet();
						Iterator it1 = key1.iterator();

						
						// h3 키와 h2키 같은값을 h3 value값으로 나눈다.

						// System.out.println(contestm_2015[i]+" 중복된 카운트");
						while (it1.hasNext()) {

							String temp = (String) it1.next();
							if(count_p.get(temp)!=(float) 0) {
								if(count_p.get(temp)>=MINGET_P)
									input_list.add(new String[] {temp,Float.toString(totaltable.get(temp)/count_p.get(temp))});
								else if (count_p.get(temp)<MINGET_P)
									input_list.add(new String[] {temp,Float.toString(totaltable.get(temp)/25)});
							}
							else
								input_list.add(new String[] {temp,Float.toString(0)});
							
							
						}
					
						String []row1;
						
						//input_list row[1]의값을비교해서 등수를 매겨줘야한당.
						ArrayList<Integer> rank=new	ArrayList<Integer>();
						//이름//평균타수
						
						for(int x=0;x<totaltable.size();x++) {
							row=input_list.get(x);
							rank.add(1);
							
							Float f=Float.parseFloat(row[1]);
							
							 for (int y = 0; y < totaltable.size(); y++) { //기준데이터와 나머지데이터비교         
								 row1=input_list.get(y);
							 
					                if(f<Float.parseFloat(row1[1])){   //기준데이터가 나머지데이터라 비교했을때 적으면 rank[i] 카운트
					                   rank.set(x, rank.get(x)+1); //COUNT                 
					                }       
							
						}
							 //System.out.print(" "+rank.get(x));
							 //이름이랑 순위 넣어주면 됌
							 player_list.add(new String [] {Integer.toString(rank.get(x)),row[0]});
							 player_h.put(row[0], rank.get(x));
							
							 
						}
						//System.out.println("------------------------------");
						 for(int a=0;a<player_list.size();a++) {
								row=player_list.get(a);            
						        // System.out.println(row[0]+" : "+row[1]); 
								 }
						
						//System.out.print(input_list.size());
						//System.out.print(rank.size());
					
						
						String rows[];
					for(int q=0;q<input_list.size();q++) {
						
						rows=input_list.get(q);
						input_h.put(rows[0], Float.parseFloat(rows[1]));
					}
						

					
					Set key2 = totaltable.keySet();
					Iterator it2 = key2.iterator();

					//total table iterator it1
					while (it2.hasNext()) {

						String namel = (String) it2.next();
						
						if(h.containsKey(namel))
							save_table.add(new String[]{Integer.toString(count),contest_2017.get(i),Integer.toString(player_h.get(namel)),namel,Float.toString(totaltable.get(namel)),Float.toString(input_h.get(namel)),Float.toString(h.get(namel)/MINUS),Float.toString(MINUS),Float.toString(count_p.get(namel))});
						else
							save_table.add(new String[] {Integer.toString(count),contest_2017.get(i),Integer.toString(player_h.get(namel)),namel,Float.toString(totaltable.get(namel)),Float.toString(input_h.get(namel)),Float.toString(0),Float.toString(MINUS),Float.toString(count_p.get(namel))});

					}
					
						//savetable(save_table,contest_2017.get(i));
						
						save_table.clear();
						v.clear();

						
						score_list.clear();
						// point_list.clear();

						h2.clear();
						h3.clear();
						h.clear();
						input_list.clear();
						player_list.clear();
						name_list.clear();
						//save_table.clear();
						
					
				
				
			//for문 끝 total table만가져가면 됌.
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				
				
			
			
			/*================================================================================*/
			
			//위에서 가져와야 할것, totaltable + count_p
		
			float sum = 0;
			csv = new File(
					"C:\\Users\\sora\\PycharmProjects\\java_klpga\\exam\\total\\" + contest_2017.get(i) + ".csv");
			br = new BufferedReader(new FileReader(csv));
			float[] rtt;
			while ((line = br.readLine()) != null) {
				// -1 옵션은 마지막 "," 이후 빈 공백도 읽기 위한 옵션
				// System.out.println(line);
				String[] token = line.split(",", -1);
				// 대회별 선수 명단.
				if (token[0].equals("순위"))
					continue;
				//순위 이름
				name_list.add(token[1]);
	
				
			
			}
			
			br.close();
			String name;
			
		
			// 각 대회가 끝날 때 마다 대회 경쟁력 다시 계산.
			for (int j = 0; j < name_list.size(); j++) {
				name = name_list.get(j);
				// 현대회의 선수가 worlrating을 부여 받았다면
				if (decide_contest.checkname(name)) {
					decide_contest.addsum(name);
					//System.out.println(name+" ");
					count++;
					
				}
			} // for문 끝
			//System.out.println("선수 count : "+count);
			
				// 2015년의 대회 지수 출력
			grade = decide_contest.getsum();
			gradehash.put(contest_2017.get(i), grade);
			int lens=getlens(grade);
			
			// 대회 지수 결정 끝

			// 메이저 리그이면
			if (major_list.contains(contest_2017.get(i)))
				rtt = rolex_point.getrolexlit(1500);

			else
				rtt = rolex_point.getrolexlit(grade);

	
			decide_contest.setsum();
			name_list.clear();
			
			
			csv = new File("C:\\Users\\sora\\PycharmProjects\\java_klpga\\exam\\total\\"+contestm_2017.get(i)+".csv");
			
			name_list=new ArrayList<String>();
			
			br = new BufferedReader(new FileReader(csv));

		     while ((line = br.readLine()) != null) {
		       // -1 옵션은 마지막 "," 이후 빈 공백도 읽기 위한 옵션
		       //System.out.println(line);
		            String[] token1 = line.split(",", -1);
		            //대회별 선수 명단. 
		            if(token1[1].equals("이름"))
						continue;
		            if(Integer.parseInt(token1[0])<=lens) {
		              	name_list.add(token1[1]);
			            //순위
			            score_list.add(Integer.parseInt(token1[0]));
		            
		          
		            
		            if(h3.containsKey(Integer.parseInt(token1[0]))) {
	        			//그score key값에 로렉스의 값을 더한다.

	        			h3.replace(Integer.parseInt(token1[0]), h3.get(Integer.parseInt(token1[0]))+1);
	        			
	        			
	        		}
		            else
		            	h3.put(Integer.parseInt(token1[0]), 1);

		            
		            //System.out.print(token[0]+ " ");
		            }
		     }

		        br.close();
		        
		        
		        String row[];
				for (int j = 0; j < name_list.size(); j++) {
					//System.out.println(rtt[j]);
	
					// j가 65보다 작으면 
					
						if (h2.containsKey(score_list.get(j))) {
							// 그score key값에 로렉스의 값을 더한다.
							sum = h2.get(score_list.get(j)) + rtt[j];
							h2.replace(score_list.get(j), sum);


						}

						// 1 rtt[1];
						else
							h2.put(score_list.get(j), rtt[j]);
						
						//포인트 획득 했으면
						if (rtt[j] != (float) 0) {
							if (count_p.containsKey(name_list.get(j))) {
								count_p.replace(name_list.get(j), count_p.get(name_list.get(j)) + 1);

								//포인트는 획득했는데 처음이라면,....
							} else
								count_p.put(name_list.get(j), (float) 1);
							
							
						}
						//0이면 부여못받은 걸로 ,
						else
							if(count_p.containsKey(name_list.get(j))==false)
								count_p.put(name_list.get(j), (float) 0);

						
						h.put(name_list.get(j), rtt[j]);
					}

				
				
				Set key = h3.keySet();
				Iterator it = key.iterator();

				
				// h3 키와 h2키 같은값을 h3 value값으로 나눈다.

				// System.out.println(contestm_2015[i]+" 중복된 카운트");
				while (it.hasNext()) {

					Integer temp = (Integer) it.next();
					float f = h2.get(temp);
					// mean
					float mean = f / h3.get(temp);
					for (int j = 0; j < h3.get(temp); j++) {
						v.add(mean);
					}
				}
				// 로렉스 포인트 부여.
				for (int k = 0; k < v.size(); k++) {
					h.replace(name_list.get(k), v.get(k));

				}
				
	
				refresh_table(h);
				//순위 이름 
				//array list <string[] > 
			
				Set key1 = totaltable.keySet();
				Iterator it1 = key1.iterator();

				
				// h3 키와 h2키 같은값을 h3 value값으로 나눈다.

				// System.out.println(contestm_2015[i]+" 중복된 카운트");
				while (it1.hasNext()) {

					String temp = (String) it1.next();
					if(count_p.get(temp)!=(float) 0) {
						if(count_p.get(temp)>=MINGET_P)
							input_list.add(new String[] {temp,Float.toString(totaltable.get(temp)/count_p.get(temp))});
						else if (count_p.get(temp)<MINGET_P)
							input_list.add(new String[] {temp,Float.toString(totaltable.get(temp)/25)});
					}
					else
						input_list.add(new String[] {temp,Float.toString(0)});
					
					
				}
			
				String []row1;
				
				//input_list row[1]의값을비교해서 등수를 매겨줘야한당.
				ArrayList<Integer> rank=new	ArrayList<Integer>();
				//이름//평균타수
				
				for(int x=0;x<totaltable.size();x++) {
					row=input_list.get(x);
					rank.add(1);
					
					Float f=Float.parseFloat(row[1]);
					
					 for (int y = 0; y < totaltable.size(); y++) { //기준데이터와 나머지데이터비교         
						 row1=input_list.get(y);
					 
			                if(f<Float.parseFloat(row1[1])){   //기준데이터가 나머지데이터라 비교했을때 적으면 rank[i] 카운트
			                   rank.set(x, rank.get(x)+1); //COUNT                 
			                }       
					
				}
					 //System.out.print(" "+rank.get(x));
					 //이름이랑 순위 넣어주면 됌
					 player_list.add(new String [] {Integer.toString(rank.get(x)),row[0]});
					 player_h.put(row[0], rank.get(x));
					
					 
				}
				//System.out.println("------------------------------");
				 for(int a=0;a<player_list.size();a++) {
						row=player_list.get(a);            
				        // System.out.println(row[0]+" : "+row[1]); 
						 }
				
				//System.out.print(input_list.size());
				//System.out.print(rank.size());
			
				
				String rows[];
			for(int q=0;q<input_list.size();q++) {
				
				rows=input_list.get(q);
				input_h.put(rows[0], Float.parseFloat(rows[1]));
			}
				

			
			Set key2 = totaltable.keySet();
			Iterator it2 = key2.iterator();

			//total table iterator it1
			while (it2.hasNext()) {

				String namel = (String) it2.next();
				if(h.containsKey(namel))
					save_table.add(new String[]{Integer.toString(count),contest_2017.get(i),Integer.toString(player_h.get(namel)),namel,Float.toString(totaltable.get(namel)),Float.toString(input_h.get(namel)),Float.toString(h.get(namel)/MINUS),Float.toString(MINUS),Float.toString(count_p.get(namel))});
				else
					save_table.add(new String[] {Integer.toString(count),contest_2017.get(i),Integer.toString(player_h.get(namel)),namel,Float.toString(totaltable.get(namel)),Float.toString(input_h.get(namel)),Float.toString(0),Float.toString(MINUS),Float.toString(count_p.get(namel))});

			}
			
			savetable(save_table,contest_2017.get(i));
				
				
				v.clear();

				
				score_list.clear();
				// point_list.clear();

				h2.clear();
				h3.clear();
				h.clear();
				input_list.clear();
				decide_contest = new Decide_contest(player_list);
				player_list.clear();
				name_list.clear();
				count_p.clear();
				totaltable.clear();
				save_table.clear();
			
	
			}//for문 끝
		
		
		Set<String> keys = gradehash.keySet();
		Iterator<String> it = keys.iterator();

		while (it.hasNext()) {

			String key = (String) it.next();
			System.out.println(key+" "+gradehash.get(key));


		
		//savetable(gradehash,"totalgragde");
		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void refresh_table(LinkedHashMap<String, Float> h) {

		Set<String> keys = h.keySet();
		Iterator<String> it = keys.iterator();

		while (it.hasNext()) {

			String key = (String) it.next();


			// 기존 테이블이 key를 가지고 있으면

			if (totaltable.containsKey(key))
				totaltable.replace(key, totaltable.get(key) + h.get(key));
	
			// 이름을 포함하지 않으면
			// 이 대회에 처음 출전 했다는 뜻.
			else if (totaltable.containsKey(key) == false) {

				totaltable.put(key, h.get(key));
			

			}

		
			

			
		}
	}	public void print_table() {
		Iterator its = sortByValue(totaltable).iterator();


		while (its.hasNext()) {

			String key = (String) its.next();
			float value = totaltable.get(key);
			//System.out.println(key + " : " + value);

		}
	}

	public void savetable(LinkedHashMap<String, Float> hn, String s) {

		try {

			File csv1;
			csv1 = new File("C:\\Users\\sora\\PycharmProjects\\java_klpga\\exam\\each_contest\\" + s + ".csv");

			BufferedWriter fw = new BufferedWriter(new FileWriter(csv1));

			Set key = hn.keySet();
			Iterator it = key.iterator();

			while (it.hasNext()) {

				String temp = (String) it.next();
				fw.write(temp + "," + hn.get(temp));
				fw.newLine();

			}
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void savetable(ArrayList<String[]> table,String s) {
	
		try {
			//순위,이름,누적,평균
			File csv1;
			csv1 = new File("C:\\Users\\sora\\PycharmProjects\\java_klpga\\exam\\each_contest\\date_" + s + ".csv");

			BufferedWriter fw = new BufferedWriter(new FileWriter(csv1));
			String rows[];
			fw.write("count"+","+"대회명"+","+"순위"+ ","+"이름" + ","+"누적포인트"+","+"평균포인트"+","+"획득 포인트"+","+"기간별가중치"+","+"빈도"+"\n");
			for(int q=0;q<table.size();q++) {
				
				rows=table.get(q);
				fw.write(rows[0]+","+rows[1]+","+rows[2]+"," + rows[3] + ","+rows[4] + ","+rows[5] + ","+rows[6] + ","+rows[7]+","+rows[8]);
				fw.newLine();
	
			}
	
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static List sortByValue(final Map map) {

		List<String> list = new ArrayList();

		list.addAll(map.keySet());

		Collections.sort(list, new Comparator() {

			public int compare(Object o1, Object o2) {

				Object v1 = map.get(o1);

				Object v2 = map.get(o2);
				return ((Comparable) v2).compareTo(v1);

			}

		});
		return list;
	}
	
	public int getlens (float grade) {
	
	int lens=0;
	if(grade>=0 && grade<=10)
		lens=14;
	else if (grade>=11 && grade<=20 )
		lens=20;
	else if(grade>=21 && grade<=30)
		lens=27;
	else if(grade>=31 && grade<=40)
		lens=35;
	else if(grade>=41 && grade<=50)
		lens=41;
	else if(grade>=51 && grade<=60)
		lens=50;
	else if(grade>=61 && grade<=70)
		lens=55;
	else if(grade>=71 && grade<=80)
		lens=58;
	else if(grade>=81 && grade<=90)
		lens=61;
	else if(grade>=91 && grade<=100)
		lens=62;
	else if(grade>=101 && grade<=110)
		lens=62;
	else if(grade>=111 && grade<=120)
		lens=63;
	else if(grade>=121 && grade<=130)
		lens=63;
	else if(grade>=131 && grade<=140)
		lens=64;
	else if(grade>=141 && grade<=150)
		lens=64;
	else if(grade>=151)
		lens=65;
	
	return lens;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Total2();

	}
	

}

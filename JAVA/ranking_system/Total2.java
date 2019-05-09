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
	// �ּ� ��ȸ ȹ�� ����Ʈ
		static final int MINGET_P = 25;
		//�Ⱓ�� ���� ����
		float MINUS;
		
		ArrayList<String> major_list = new ArrayList<String>(
				//2015 ������ 11, 16, 21, 26
				//2016 ������  13, 24, 27, 29
				//2017 ������ 12,21,22,27,29
				// 2018 ������ 5,12,20,24,26
				Arrays.asList("2015_klpga_Sa11", "2015_klpga_Sa16", "2015_klpga_Sa21", "2015_klpga_Sa26",
						"2016_klpga_Sa13", "2016_klpga_Sa24", "2016_klpga_Sa27", "2016_klpga_Sa29",
						"2017_klpga_Sa12", "2017_klpga_Sa21", "2017_klpga_Sa22", "2017_klpga_Sa27", "2017_klpga_Sa29",
						//2018 ������
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

	// ���� �����
		File csv;
		BufferedReader br;
		String line = "";
		// �������� ����Ʈ ȹ�� Ƚ��
		LinkedHashMap<String, Float> count_p = new LinkedHashMap<String, Float>();
		// ��ȸ ������ �Ǵ��� base_hash
		LinkedHashMap<String, Float> h = new LinkedHashMap<String, Float>();

		// ��ȸ ������ �Ǵ��� base_hash
		LinkedHashMap<String, Float> input_h = new LinkedHashMap<String, Float>();
		

		// ��ȸ ������ �Ǵ��� base_hash
		LinkedHashMap<String, Integer> player_h = new LinkedHashMap<String, Integer>();
		
		
		//input �� ������ ���
		ArrayList<String[]> input_list=new	ArrayList<String[]>();
		
		
		// �̸�
		ArrayList<String> name_list = new ArrayList<String>();

		//��ȸ������ �Ǵ��� class
		Decide_contest decide_contest;
	
		
		LinkedHashMap<String, Float> totaltable = new LinkedHashMap<String, Float>();
		//��ȸ ����Ʈ�� ��ȸ ���� ������ TABLE
		LinkedHashMap<String, Float> gradehash = new LinkedHashMap<String, Float>();
		

		//��ȸ ����Ʈ�� ��ȸ ���� ������ TABLE
		LinkedHashMap<String, Float> newone = new LinkedHashMap<String, Float>();
		//�η��� table
		Rolextable rolex_point = new Rolextable();
		// �������� score ����
		ArrayList<Integer> score_list = new ArrayList<Integer>();
		// ���� ���� ����Ʈ �հ�
		LinkedHashMap<Integer, Float> h2 = new LinkedHashMap<Integer, Float>();
		// ���� ������ �հ�
		LinkedHashMap<Integer, Integer> h3 = new LinkedHashMap<Integer, Integer>();
		
		//�̸� ī��Ʈ ��������Ʈ
		ArrayList<String []> player_list=new ArrayList<String []>();
		
		//��ȸ�� ����
		ArrayList<String[]> save_table=new ArrayList<String[]>();
		
		
		//������ ���� ��� ����Ʈ
		Vector<Float> v = new Vector<Float>();
		
		//��ȸ �̸���, ��ȸ ��¥
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
			// -1 �ɼ��� ������ "," ���� �� ���鵵 �б� ���� �ɼ�
			// System.out.println(line);
			String[] token = line.split(",", -1);
			// ���⵵ ���� ����, worldratingvalue
			if (token[0].equals("����"))
				continue;
			
			//������ �̸��� �־��ش�.
			input_list.add(new String []{token[0],token[1]});
			

		}
		br.close();
		//System.out.print("==="+input_list.size());
		decide_contest = new Decide_contest(input_list);
		// ���̽� �ؽ��� �ְ�,

		name_list.clear();
		input_list.clear();
		
		//System.out.print(contest_2017.size());==117
		//System.out.println("�ڡڡڡ�2017 ��ȸ�����ڡڡڡ�");
		Float grade=(float) 0;
		//for (int i = 0; i<12; i++) {
		for (int i = 0; i<contest_2017.size(); i++) {
			
			int count=0;
			
			
			//���� ��ȸ�� ���� ��ȸ������ �Ⱓ�� ���� ���� ����Ʈ �ο�
			/*==============================================================*/
			
			
			for(int b=0;b<i;b++) {
				
			//System.out.print(i+"  , "+b);
			
				try {
		
					
					//i�� j �� ����
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
						// -1 �ɼ��� ������ "," ���� �� ���鵵 �б� ���� �ɼ�
						// System.out.println(line);
						String[] token = line.split(",", -1);
						// ��ȸ�� ���� ���.
						if (token[0].equals("����"))
							continue;
						//���� �̸�
						name_list.add(token[1]);
						//count_p.put(token[1], (float) 1);
						
					
					}
					
					br.close();
				
					// �� ��ȸ�� ���� �� ���� ��ȸ ����� �ٽ� ���.

					String name;
					/*
				
					// �� ��ȸ�� ���� �� ���� ��ȸ ����� �ٽ� ���.
					for (int j = 0; j < name_list.size(); j++) {
						name = name_list.get(j);
						// ����ȸ�� ������ worlrating�� �ο� �޾Ҵٸ�
						if (decide_contest.checkname(name)) {
							decide_contest.addsum(name);
							//System.out.println(name+" : "+decide_contest.getsum());
							count++;
							
						}
					} // for�� ��
					//System.out.println("���� count : "+count);
					
						// 2015���� ��ȸ ���� ���
						 * 
						 * 
						 */
					
					grade =gradehash.get(contest_2017.get(b));
					//gradehash.put(contest_2017.get(i), grade);
					System.out.print(" "+grade);
		
					// ��ȸ ���� ���� ��

					// ������ �����̸�
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
				       // -1 �ɼ��� ������ "," ���� �� ���鵵 �б� ���� �ɼ�
				       //System.out.println(line);
				            String[] token1 = line.split(",", -1);
				            //��ȸ�� ���� ���. 
				            if(token1[1].equals("�̸�"))
								continue;
				            
				            if(Integer.parseInt(token1[0])<=lens) {
				            	 name_list.add(token1[1]);
						            //����
						            //�ִµ�� ��� Ŭ����
						            score_list.add(Integer.parseInt(token1[0]));
						            
						            if(h3.containsKey(Integer.parseInt(token1[0]))) {
					        			//��score key���� �η����� ���� ���Ѵ�.

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
									// ��score key���� �η����� ���� ���Ѵ�.
									sum = h2.get(score_list.get(j)) + rtt[j]*MINUS;
									h2.replace(score_list.get(j), sum);


								}

								// 1 rtt[1];
								else
									h2.put(score_list.get(j), rtt[j]*MINUS);
								
								//����Ʈ ȹ�� ������
								if (rtt[j]*MINUS != (float) 0) {
									if (count_p.containsKey(name_list.get(j))) {
										count_p.replace(name_list.get(j), count_p.get(name_list.get(j)) + 1);

										//����Ʈ�� ȹ���ߴµ� ó���̶��,....
									} else
										count_p.put(name_list.get(j), (float) 1);
									
									
									
								}
								//0�̸� �ο������� �ɷ� ,
								else
									if(count_p.containsKey(name_list.get(j))==false)
										count_p.put(name_list.get(j), (float) 0);

								
								h.put(name_list.get(j), rtt[j]);
							}

						
						
						Set key = h3.keySet();
						Iterator it = key.iterator();

						
						// h3 Ű�� h2Ű �������� h3 value������ ������.

						// System.out.println(contestm_2015[i]+" �ߺ��� ī��Ʈ");
						while (it.hasNext()) {

							Integer temp = (Integer) it.next();
							float f = h2.get(temp);
							// mean
							float mean = f / h3.get(temp);
							for (int j = 0; j < h3.get(temp); j++) {
								v.add(mean);
							}
						}
						// �η��� ����Ʈ �ο�.
						for (int k = 0; k < v.size(); k++) {
							h.replace(name_list.get(k), v.get(k));

						}
						
			
						refresh_table(h);
						//���� �̸� 
						//array list <string[] > 
					
						Set key1 = totaltable.keySet();
						Iterator it1 = key1.iterator();

						
						// h3 Ű�� h2Ű �������� h3 value������ ������.

						// System.out.println(contestm_2015[i]+" �ߺ��� ī��Ʈ");
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
						
						//input_list row[1]�ǰ������ؼ� ����� �Ű�����Ѵ�.
						ArrayList<Integer> rank=new	ArrayList<Integer>();
						//�̸�//���Ÿ��
						
						for(int x=0;x<totaltable.size();x++) {
							row=input_list.get(x);
							rank.add(1);
							
							Float f=Float.parseFloat(row[1]);
							
							 for (int y = 0; y < totaltable.size(); y++) { //���ص����Ϳ� �����������ͺ�         
								 row1=input_list.get(y);
							 
					                if(f<Float.parseFloat(row1[1])){   //���ص����Ͱ� �����������Ͷ� �������� ������ rank[i] ī��Ʈ
					                   rank.set(x, rank.get(x)+1); //COUNT                 
					                }       
							
						}
							 //System.out.print(" "+rank.get(x));
							 //�̸��̶� ���� �־��ָ� ��
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
						
					
				
				
			//for�� �� total table���������� ��.
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				
				
			
			
			/*================================================================================*/
			
			//������ �����;� �Ұ�, totaltable + count_p
		
			float sum = 0;
			csv = new File(
					"C:\\Users\\sora\\PycharmProjects\\java_klpga\\exam\\total\\" + contest_2017.get(i) + ".csv");
			br = new BufferedReader(new FileReader(csv));
			float[] rtt;
			while ((line = br.readLine()) != null) {
				// -1 �ɼ��� ������ "," ���� �� ���鵵 �б� ���� �ɼ�
				// System.out.println(line);
				String[] token = line.split(",", -1);
				// ��ȸ�� ���� ���.
				if (token[0].equals("����"))
					continue;
				//���� �̸�
				name_list.add(token[1]);
	
				
			
			}
			
			br.close();
			String name;
			
		
			// �� ��ȸ�� ���� �� ���� ��ȸ ����� �ٽ� ���.
			for (int j = 0; j < name_list.size(); j++) {
				name = name_list.get(j);
				// ����ȸ�� ������ worlrating�� �ο� �޾Ҵٸ�
				if (decide_contest.checkname(name)) {
					decide_contest.addsum(name);
					//System.out.println(name+" ");
					count++;
					
				}
			} // for�� ��
			//System.out.println("���� count : "+count);
			
				// 2015���� ��ȸ ���� ���
			grade = decide_contest.getsum();
			gradehash.put(contest_2017.get(i), grade);
			int lens=getlens(grade);
			
			// ��ȸ ���� ���� ��

			// ������ �����̸�
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
		       // -1 �ɼ��� ������ "," ���� �� ���鵵 �б� ���� �ɼ�
		       //System.out.println(line);
		            String[] token1 = line.split(",", -1);
		            //��ȸ�� ���� ���. 
		            if(token1[1].equals("�̸�"))
						continue;
		            if(Integer.parseInt(token1[0])<=lens) {
		              	name_list.add(token1[1]);
			            //����
			            score_list.add(Integer.parseInt(token1[0]));
		            
		          
		            
		            if(h3.containsKey(Integer.parseInt(token1[0]))) {
	        			//��score key���� �η����� ���� ���Ѵ�.

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
	
					// j�� 65���� ������ 
					
						if (h2.containsKey(score_list.get(j))) {
							// ��score key���� �η����� ���� ���Ѵ�.
							sum = h2.get(score_list.get(j)) + rtt[j];
							h2.replace(score_list.get(j), sum);


						}

						// 1 rtt[1];
						else
							h2.put(score_list.get(j), rtt[j]);
						
						//����Ʈ ȹ�� ������
						if (rtt[j] != (float) 0) {
							if (count_p.containsKey(name_list.get(j))) {
								count_p.replace(name_list.get(j), count_p.get(name_list.get(j)) + 1);

								//����Ʈ�� ȹ���ߴµ� ó���̶��,....
							} else
								count_p.put(name_list.get(j), (float) 1);
							
							
						}
						//0�̸� �ο������� �ɷ� ,
						else
							if(count_p.containsKey(name_list.get(j))==false)
								count_p.put(name_list.get(j), (float) 0);

						
						h.put(name_list.get(j), rtt[j]);
					}

				
				
				Set key = h3.keySet();
				Iterator it = key.iterator();

				
				// h3 Ű�� h2Ű �������� h3 value������ ������.

				// System.out.println(contestm_2015[i]+" �ߺ��� ī��Ʈ");
				while (it.hasNext()) {

					Integer temp = (Integer) it.next();
					float f = h2.get(temp);
					// mean
					float mean = f / h3.get(temp);
					for (int j = 0; j < h3.get(temp); j++) {
						v.add(mean);
					}
				}
				// �η��� ����Ʈ �ο�.
				for (int k = 0; k < v.size(); k++) {
					h.replace(name_list.get(k), v.get(k));

				}
				
	
				refresh_table(h);
				//���� �̸� 
				//array list <string[] > 
			
				Set key1 = totaltable.keySet();
				Iterator it1 = key1.iterator();

				
				// h3 Ű�� h2Ű �������� h3 value������ ������.

				// System.out.println(contestm_2015[i]+" �ߺ��� ī��Ʈ");
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
				
				//input_list row[1]�ǰ������ؼ� ����� �Ű�����Ѵ�.
				ArrayList<Integer> rank=new	ArrayList<Integer>();
				//�̸�//���Ÿ��
				
				for(int x=0;x<totaltable.size();x++) {
					row=input_list.get(x);
					rank.add(1);
					
					Float f=Float.parseFloat(row[1]);
					
					 for (int y = 0; y < totaltable.size(); y++) { //���ص����Ϳ� �����������ͺ�         
						 row1=input_list.get(y);
					 
			                if(f<Float.parseFloat(row1[1])){   //���ص����Ͱ� �����������Ͷ� �������� ������ rank[i] ī��Ʈ
			                   rank.set(x, rank.get(x)+1); //COUNT                 
			                }       
					
				}
					 //System.out.print(" "+rank.get(x));
					 //�̸��̶� ���� �־��ָ� ��
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
			
	
			}//for�� ��
		
		
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


			// ���� ���̺��� key�� ������ ������

			if (totaltable.containsKey(key))
				totaltable.replace(key, totaltable.get(key) + h.get(key));
	
			// �̸��� �������� ������
			// �� ��ȸ�� ó�� ���� �ߴٴ� ��.
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
			//����,�̸�,����,���
			File csv1;
			csv1 = new File("C:\\Users\\sora\\PycharmProjects\\java_klpga\\exam\\each_contest\\date_" + s + ".csv");

			BufferedWriter fw = new BufferedWriter(new FileWriter(csv1));
			String rows[];
			fw.write("count"+","+"��ȸ��"+","+"����"+ ","+"�̸�" + ","+"��������Ʈ"+","+"�������Ʈ"+","+"ȹ�� ����Ʈ"+","+"�Ⱓ������ġ"+","+"��"+"\n");
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

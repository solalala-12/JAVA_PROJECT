package java_project;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Student extends JPanel {
	// 메모장으로 부터 데이터를 읽어오기 위해 필요한 변수 i
	int i = 0;
	// 가져올 파일 이름
	private static String FILENAME = "student.javaa.txt";
	BufferedReader br = null;
	FileReader fr = null;
	// DefaultTableModel을 갖고 생성할 jtable 선언
	static JTable jt1;
	
	// table model 생성과 그 모델에 들어갈 data 선언
	String[][] data;
	student_entire sentry = new student_entire();
	DefaultTableModel dtm;

	Student() {
		setSize(300, 400);

		setVisible(true);
		setLayout(new BorderLayout());
		add(sentry, BorderLayout.CENTER);
	}
	
	
	
	class student_entire extends JPanel {
		student_edit se = new student_edit();
		student_show ss = new student_show();

		student_entire() {
			setSize(1000, 600);
			setLayout(new GridLayout(1, 2));
			add(se);
			add(ss);
			setVisible(true);
		}
	}

	// 테이블 보여주는 panel
	class student_show extends JPanel {
	
		JScrollPane scrollPane;

		student_show() {
			setSize(300, 200);
			setBorder(new TitledBorder(new LineBorder(Color.gray, 1), "수강생 목록"));
			setLayout(new FlowLayout());
			JButton reset=new JButton("테이블 초기화");
			JButton export=new JButton("테이블 저장");
			export.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					
					try {
						String f="student.txt";
						PrintWriter os = new PrintWriter(f);
					
						for (int row = 0; row < dtm.getRowCount(); row++) {
							
						    for (int col = 0; col < dtm.getColumnCount(); col++) {
						   
						    	System.out.print(dtm.getValueAt(row, col));
						    	System.out.print(",");

						    	os.print(dtm.getValueAt(row, col));
						        os.print(",");
						        
						    }
						    os.print("\n");

						}
						os.close();
					}catch (IOException a) {
			            // TODO Auto-generated catch block
			            a.printStackTrace();
			        }
		       
					
				}
				
				
				
			});
			reset.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int remove = dtm.getRowCount();
					for (int k = 0; k < remove; k++) {
						dtm.removeRow(0);
					}
					
				}
				
				
			});

			try {
				
				fr = new FileReader(FILENAME);
				br = new BufferedReader(fr);

				String s;
				String[][] data = new String[20][7];

				// br = new BufferedReader(new FileReader(FILENAME));
				int i = 0;
				while ((s = br.readLine()) != null) {
					data[i] = s.split(",");
					i++;
				}

				String column[] = { "번호","학번", "이름", "성별", "전화번호", "주소", "특이사항" };

				dtm = new DefaultTableModel(data, column);

				jt1 = new JTable(dtm);
				//jt.setSize(500,500);
				jt1.setRowHeight(25);
				jt1.setFont(new Font("Gothics", Font.ITALIC, 15));
		
				scrollPane = new JScrollPane(jt1);
				scrollPane.setPreferredSize(new Dimension(700,580));
				// 처음 실행할 때는 빈 화면을 보여주기 위해 데이블 모델의 모든 데이터를 삭제한다. 
				int remove = dtm.getRowCount();
				for (int k = 0; k < remove; k++) {
					dtm.removeRow(0);
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();
					if (fr != null)
						fr.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			add(scrollPane);
			add(reset);
			add(export);
			setVisible(true);
		}
	}

	// 학생을 추가할 수 있는 기능을 가진 panel
	class student_edit extends JPanel {
		String sex;

		student_edit() {
			setSize(100, 100);
			// 테두리설정
			setBorder(new TitledBorder(new LineBorder(Color.gray, 2), "학생추가"));
			setLayout(new GridLayout(7, 3, 20, 50));
			setSize(200,200);
		
			// 학번 textfield
			JTextField num_tf = new JTextField(7);

			// 이름 textfield
			JTextField name_tf = new JTextField(7);
			
			// 성별 radio button
			JRadioButton sex_rb1 = new JRadioButton("여");
			JRadioButton sex_rb2 = new JRadioButton("남");
			
			 sex_rb1.addActionListener(new ActionListener() {
				 
			 @Override public void actionPerformed(ActionEvent e) {
				 sex="여";
			  }

			  });
			 sex_rb2.addActionListener(new ActionListener() {
				 
				 @Override public void actionPerformed(ActionEvent e) {
					 sex="남";
				  }

				  });
			 
			
			ButtonGroup group = new ButtonGroup();
			group.add(sex_rb1);
			group.add(sex_rb2);

			// 휴대폰 textfield
			JTextField pnum_tf = new JTextField(7);
			
			// 주소 textfield
			JTextField ad_tf = new JTextField(7);
			
			// 특이사항 textfield
			JTextField point_tf = new JTextField(7);
			JButton jb = new JButton("확인");
			JButton jb2 = new JButton("파일불러오기");
			
			add(new Label("학번"));
			add(num_tf);
			add(new Label(""));
			add(new Label("이름"));
			add(name_tf);
			add(new Label(""));
			add(new Label("성별"));
			add(sex_rb1);
			add(sex_rb2);
			add(new Label("휴대폰"));
			add(pnum_tf);
			add(new Label(""));
			add(new Label("주소"));
			add(ad_tf);
			add(new Label(""));
			add(new Label("특이사항"));
			add(point_tf);
			add(new Label(""));
			add(new Label(""));
			add(jb);
			add(jb2);

			
			jb2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					// Menubar에서 선택된 과목을 가져오기 위해 Menubar 객체를 가져왔다. 
					Menubar mm = Menubar.getInstance();

					// Menubar 객체의 flag 값이 true 이면 자바 A과목을 불러오고,
			        // 그렇지 않으면 자바 B과목을 불러온다. 
					if (mm.getFlag()) {
						FILENAME="student.javaa.txt";
						fileIO(FILENAME);
						
					} else {
						FILENAME="student.javab.txt";
						fileIO(FILENAME);
					}
				}
			});
			// 확인 버튼이 클릭되면 jtable에 리스트가 추가되게 한다.
			jb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				// 번호, 학번,이름,성별,휴대폰,주소,특이사항
				String num = Integer.toString(i + 1);
				String id = num_tf.getText();
				String name = name_tf.getText();
				String sex1=sex;
				String pnum=pnum_tf.getText();
				String address=ad_tf.getText();
				String point=point_tf.getText();
				Object rowData[] = { num, id, name, sex,pnum,address,point };
				dtm.addRow(rowData);
				i++;
				num_tf.setText("");
				name_tf.setText("");
				pnum_tf.setText("");
				ad_tf.setText("");
				point_tf.setText("");
				}
				});
		
			
		
			
			// 파일 불러오기
		}
		// 파일을 읽어오기 위한 메소드 fileIO
		public void fileIO(String filename) {
			try {
			// 데이터 모델인 dtm의 데이터를 모두 삭제
			int remove = dtm.getRowCount();
			for (int k = 0; k < remove; k++) {
				dtm.removeRow(0);
			}

			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			String s;
			data = new String[20][6];

			// 새로 읽어들인 data를 dtm에 addRow 한다. 
			i = 0;
			while ((s = br.readLine()) != null) {
				data[i] = s.split(",");
				dtm.addRow(data[i]);
				i++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();
					if (fr != null)
						fr.close();
					} catch (IOException ex) {
						ex.printStackTrace();
						}
				}
			}
		}
		
	
	}



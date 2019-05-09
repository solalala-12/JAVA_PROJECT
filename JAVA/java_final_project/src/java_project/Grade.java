package java_project;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Grade extends JPanel {

   // 메모장으로 부터 데이터를 읽어오기 위해 필요한 변수 i
   int i = 0;

   // 가져올 파일 이름
   String FILENAME = "grade.javaa.txt";
   BufferedReader br = null;
   FileReader fr = null;

   // table model 생성과 그 모델에 들어갈 data 선언
   String[][] data;
   DefaultTableModel dtm;
   grade_entire gentry = new grade_entire();

   // DefaultTableModel을 갖고 생성할 jtable 선언
   JTable jt;

   // rank 벡터는 성적의 등수를 산정하기 위해 선언
   Vector<Integer> rank = new Vector<Integer>();

   // sum은 점수의 총합을 구하기 위해 선언
   float sum = 0;

   // Grade 생성자
   Grade() {
      setSize(1200, 700);

      setVisible(true);
      setLayout(new BorderLayout());

      add(gentry, BorderLayout.CENTER);
   }

   // 성적 프로그램의 전체를 담당하고 있는 패널
   class grade_entire extends JPanel {
      grade_edit ge = new grade_edit();
      grade_show gs = new grade_show();
      grade_graph gg = new grade_graph();
      grade_statistics sta = new grade_statistics();

      grade_entire() {
         setSize(1000, 600);
         setLayout(new GridLayout(1, 3));
         add(ge);
         add(gs);

         JPanel jp = new JPanel();
         jp.setLayout(new GridLayout(2, 1));
         jp.add(sta);
         jp.add(gg);

         add(jp);
         setVisible(true);
      }
   }

   // 학생을 추가할 수 있는 기능을 가진 panel
   class grade_edit extends JPanel {

      grade_edit() {

         setSize(100, 100);
         // 테두리설정
         setBorder(new TitledBorder(new LineBorder(Color.gray, 2), "점수 입력"));
         setLayout(new FlowLayout());

         // 학번 textfield
         JTextField num_tf = new JTextField(7);

         // 이름 textfield
         JTextField name_tf = new JTextField(7);

         // 점수 textfield
         JTextField grade_tf = new JTextField(7);

         // 확인버튼
         JButton jb = new JButton("확인");

         add(new Label("학번"));
         add(num_tf);
         add(new Label("이름"));
         add(name_tf);
         add(new Label("점수"));
         add(grade_tf);
         add(jb);

         // 확인 버튼이 클릭되면 jtable에 리스트가 추가되게 한다.
         jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               // 번호, 학번,이름,점수 받아오기
               String num = Integer.toString(i + 1);
               String id = num_tf.getText();
               String name = name_tf.getText();
               String grade = grade_tf.getText();
               Object rowData[] = { num, id, name, grade };
               dtm.addRow(rowData);
               i++;

               num_tf.setText("");
               name_tf.setText("");
               grade_tf.setText("");
            }
         });
         setVisible(true);
      }
   }

   // 학생들의 학점을 계산해 보여주는 panel
   class grade_show extends JPanel {

      // grade_show 생성자에서도 쓰이고, 다른 클래스에서도 쓰이기 때문에 전역변수로 선언
      JScrollPane scrollPane;

      grade_show() {
         setSize(300, 150);
         setBorder(new TitledBorder(new LineBorder(Color.gray, 2), "성적 확인"));
         setLayout(new FlowLayout());

         // 파일 불러오기
         try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String s;
            data = new String[20][6];

            i = 0;
            while ((s = br.readLine()) != null) {
               data[i] = s.split(",");
               i++;
            }

            String column[] = { "번호", "학번", "이름", "점수", "등수", "학점" };

            dtm = new DefaultTableModel(data, column);

            jt = new JTable(dtm);
            jt.setRowHeight(25);
            jt.setFont(new Font("Gothics", Font.ITALIC, 18));
            scrollPane = new JScrollPane(jt);

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

         // 여러 가지 기능을 담당하는 버튼들
         JButton filebutton1 = new JButton("파일 불러오기");
         JButton score_rank = new JButton("등수 보기");
         JButton grade_rank = new JButton("학점 계산");
         JButton score_sort = new JButton("점수 정렬");

         // 위 버튼 4개에 리스너 설정

         // "파일 불러오기" 버튼에 리스너를 단다. 선택된 과목에 따른 파일을 읽어온다.
         filebutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               // Menubar에서 선택된 과목을 가져오기 위해 Menubar 객체를 가져왔다.
               Menubar mm = Menubar.getInstance();

               // Menubar 객체의 flag 값이 true 이면 자바 A과목을 불러오고,
               // 그렇지 않으면 자바 B과목을 불러온다.
               if (mm.getFlag()) {

                  fileIO("grade.javaa.txt");

               } else {

                  fileIO("grade.javab.txt");
               }
            }
         });

         // "점수 정렬" 버튼에 리스너를 단다. 점수를 내림차순으로 정렬한다.
         score_sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               TableRowSorter<TableModel> sorter = new TableRowSorter<>(jt.getModel());
               jt.setRowSorter(sorter);
               ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
               sortKeys.add(new RowSorter.SortKey(3, SortOrder.DESCENDING));
               sorter.setSortKeys(sortKeys);
               sorter.sort();
            }
         });

         // "등수 보기" 버튼에 리스너를 단다.
         score_rank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               // 학생의 점수를 rank 벡터에 add하고,
               rank.removeAllElements();
               for (int j = 0; j < dtm.getRowCount(); j++) {
                  rank.add(Integer.parseInt((String) dtm.getValueAt(j, 3)));
               }

               // rank를 거꾸로 정렬한다. 98, 96, 90,.. 로 정렬된다.
               Collections.sort(rank);
               Collections.reverse(rank);

               // rank의 값을 하나씩 꺼내와서, 그 값과 점수가 같은 학생을 찾고,
               // rank의 인덱스를 등수 column에 setValue 한다. 등수 완성!
               for (int j = 0; j < dtm.getRowCount(); j++) {
                  dtm.setValueAt(rank.indexOf(((Integer.parseInt((String) dtm.getValueAt(j, 3))))) + 1, j, 4);
               }
            }
         });

         // "학점 계산" 버튼에 리스너를 단다.
         // 전체 학생수의 상위 15% 는 A+, 상위 30%는 Ao,... 로 설정해두었다.
         // 전체 학생수가 변경될 경우, 학점은 재산정된다.
         grade_rank.addActionListener(new ActionListener() {
            String grade = "";

            @Override
            public void actionPerformed(ActionEvent arg0) {

               // 학점 계산하기
               for (int j = 0; j < dtm.getRowCount(); j++) {

                  // order는 학생의 등수를 가져오는 변수이다. 학생 수 만큼 반복해서 값이 업데이트된다.
                  int order = rank.indexOf((Integer.parseInt((String) dtm.getValueAt(j, 3)))) + 1;

                  if (order <= data.length * 0.15)
                     grade = "A+";
                  else if (order <= data.length * 0.3)
                     grade = "Ao";
                  else if (order <= data.length * 0.45)
                     grade = "B+";
                  else if (order <= data.length * 0.6)
                     grade = "Bo";
                  else if (order <= data.length * 0.75)
                     grade = "C+";
                  else if (order <= data.length * 0.90)
                     grade = "Co";
                  else
                     grade = "F";

                  // 데이터 모델의 6번째 행에 위에서 구한 학점을 setValue해준다.
                  dtm.setValueAt(grade, j, 5);
               }
            }
         });

         
         add(scrollPane);

         // 성적 보여주는 것 아래에 버튼들을 배치
         JPanel jp0 = new JPanel(new GridLayout(4, 1, 0, 20));

         JPanel jp1 = new JPanel(new GridLayout(1, 1));
         jp1.add(score_sort);

         JPanel jp2 = new JPanel(new GridLayout(1, 2));
         jp2.add(score_rank);
         jp2.add(grade_rank);

         JButton store=new JButton("파일 저장하기");
         store.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String f="grade.txt";
					PrintWriter os = new PrintWriter(f);
					for (int row = 0; row < dtm.getRowCount(); row++) {
						 for (int col = 0; col < dtm.getColumnCount(); col++) {
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
         
         jp0.add(filebutton1);
         jp0.add(store);
         jp0.add(jp2);
         jp0.add(jp1);

         add(jp0);
         setVisible(true);
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

   // 성적 통계를 보여주는 panel
   class grade_statistics extends JPanel {

      // 통계 panel에 추가할 버튼과 라벨 선언.
      // 생성자와 리스너에도 쓰이기 때문에 전역 변수로 올려주었다.
      JButton b1 = new JButton("새로고침");
      JLabel la1 = new JLabel("수강생 " + dtm.getRowCount() + " 명", SwingConstants.CENTER);
      float sum = summation();
      JLabel la2 = new JLabel("평균 " + sum / dtm.getRowCount() + " 점", SwingConstants.CENTER);
      JLabel la3 = new JLabel("상위 50 % 에 해당하는 학생의 점수는    점 입니다.", SwingConstants.CENTER);

      grade_statistics() {

         setBorder(new TitledBorder(new LineBorder(Color.gray, 2), "통계량"));
         setLayout(new GridLayout(5, 1));

         // GridLayout에 바로 "새로고침" 버튼을 추가하면 버튼이 너무 커져서 jpanel을 하나 더 만들었다.
         JPanel jp = new JPanel(new FlowLayout());
         jp.add(b1);
         b1.setHorizontalAlignment(JButton.CENTER);
         b1.setBackground(Color.GRAY);
         b1.setForeground(Color.white);

         // 버튼과 라벨들의 폰트를 설정해주었다.
         b1.setFont(new Font("Gothics", Font.ITALIC, 20));
         la1.setFont(new Font("Gothics", Font.ITALIC, 30));
         la2.setFont(new Font("Gothics", Font.ITALIC, 30));
         la3.setFont(new Font("Gothics", Font.ITALIC, 20));

         // 슬라이더, 성적의 분포를 슬라이더를 통해 확인할 수 있다.
         JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
         slider.setPaintLabels(true);
         slider.setPaintTicks(true);
         slider.setPaintTrack(true);
         slider.setMajorTickSpacing(10);
         slider.setMinorTickSpacing(1);
         slider.setForeground(Color.BLUE);

         // 슬라이더에 리스너 설정
         slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               // 슬라이더로부터 값을 받아오되, 상위 몇 %를 나타내기 위해 100에서 값을 빼주었다.
               int percentage = 100 - slider.getValue();

               String per_score = "";

               // 슬라이더의 퍼센트와 학생들의 등수를 비교한다.
               // 만약 상위 20%의 성적을 슬라이더로 가리키면, 상위 20% 등수에 해당하는 학생의 성적이 라벨3에 나타난다.
               // 상위 20% 등수에 해당하는 학생이 없으면 그냥 공백으로 출력된다.
               for (int j = 0; j < dtm.getRowCount(); j++) {
                  if ((int) (dtm.getRowCount() * percentage * 0.01) == (int) dtm.getValueAt(j, 4)) {
                     per_score = (String) dtm.getValueAt(j, 3);
                  }
               }
               la3.setText("상위 " + percentage + " % 에 해당하는 학생의 점수는 " + per_score + " 점 입니다.");
            }
         });

         // "새로 고침" 버튼의 리스너
         // 데이터의 총 행수와, 평균을 구한다.
         b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               la1.setText("수강생 " + dtm.getRowCount() + " 명");

               // 학생들의 점수의 총합은 summation이라는 메소드를 통해 얻어온다.
               float sum = summation();
               la2.setText("평균 " + sum / dtm.getRowCount() + " 점");
            }
         });

         // 버튼과 라벨, 슬라이더를 단다.
         add(jp);
         add(la1);
         add(la2);
         add(slider);
         add(la3);

         setVisible(true);
      }
   }

   // 학생들의 점수의 총합을 구하는 메소드
   public float summation() {
      sum = 0;
      for (int j = 0; j < dtm.getRowCount(); j++) {
         sum += Integer.parseInt((String) dtm.getValueAt(j, 3));
      }
      return sum;
   }
   
   // 성적 분포를 보여주는 panel
   class grade_graph extends JPanel {

      // 히스토그램을 그리기 위해 필요한 변수들, 리스너에도 필요하기 때문에 전역변수로 선언하였다. 
      int[] values = new int[10];
      String[] labels = new String[] { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" };
      Color[] colors = new Color[] { Color.yellow, Color.orange, Color.pink,  Color.magenta, Color.red, 
                                   Color.green, Color.CYAN, Color.blue, Color.GRAY, Color.darkGray };
      int score;
      
      grade_graph() {
   
         setBorder(new TitledBorder(new LineBorder(Color.gray, 2), "그래프"));
         setLayout(new BorderLayout());

         // "히스토그램" 버튼 배치
         JButton histogram = new JButton("성적 히스토그램");
         histogram.setBackground(Color.GRAY);
         histogram.setForeground(Color.WHITE);
         histogram.setFont(new Font("Gothics", Font.ITALIC, 20));
         
         JPanel jp=new JPanel(new FlowLayout());
         jp.add(histogram);
         add(jp, BorderLayout.NORTH);
         
         // value 배열 초기화, 초기화해주지 않으면, 리스너를 호출할 때마다 배열 값이 누적된다. 
         for(int k=0;k<10;k++) {
             values[k]=0;
         }
         
         // value 값을 구하기 위한 for문, 10점대, 20점대, 30점대,...의 분포를 보려고 한다. 
         for(int k=0;k<dtm.getRowCount();k++) {
            
            score=Integer.parseInt((String)dtm.getValueAt(k, 3));
            if(score< 20) values[0]++;
            else if(score< 30) values[1]++;
            else if(score< 40) values[2]++;
            else if(score< 50) values[3]++;
            else if(score< 60) values[4]++;
            else if(score< 70) values[5]++;
            else if(score< 80) values[6]++;
            else if(score< 90) values[7]++;
            else if(score< 100) values[8]++;
            else if(score==100) values[9]++;
         }
      
         // BarChart 객체를 생성후 add
         BarChart bc = new BarChart(values, labels, colors,"histogram");
         add(bc, BorderLayout.CENTER);
         
         // "히스토그램" 버튼에 리스너 설정, 
         // 데이터가 변경되고 버튼을 누르면, 변경된 그래프가 repaint 된다. 
         histogram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
               for(int k=0;k<10;k++) {
                   values[k]=0;
               }
               
               for(int k=0;k<dtm.getRowCount();k++) {
                  
                  score=Integer.parseInt((String)dtm.getValueAt(k, 3));
                  if(score< 20) values[0]++;
                  else if(score< 30) values[1]++;
                  else if(score< 40) values[2]++;
                  else if(score< 50) values[3]++;
                  else if(score< 60) values[4]++;
                  else if(score< 70) values[5]++;
                  else if(score< 80) values[6]++;
                  else if(score< 90) values[7]++;
                  else if(score< 100) values[8]++;
                  else if(score==100) values[9]++;
               }
               repaint();
            }
         });
      }
   }

   class BarChart extends JPanel {
       
        private int[] values;
        private String[] labels;
        private Color[] colors;
        private String title;
       
        public BarChart(int[] values, String[] labels, Color[] colors, String title) {
          this.labels = labels;
          this.values = values;
          this.colors = colors;
          this.title = title;
        }
       
        public void paintComponent(Graphics g) {
          super.paintComponent(g);
          if (values == null || values.length == 0) {
            return;
          }
       
          int minValue = 0;
          int maxValue = 0;
          for (int i = 0; i < values.length; i++) {
            if (minValue > values[i]) {
              minValue = values[i];
            }
            if (maxValue < values[i]) {
              maxValue = values[i];
            }
          }
       
          Dimension dim = getSize();
          int panelWidth = dim.width;
          int panelHeight = dim.height;
          int barWidth = panelWidth / values.length;
       
          Font titleFont = new Font("Italic", Font.ITALIC, 18);
          FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
       
          Font labelFont = new Font("Bold", Font.BOLD, 14);
          FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
       
          int titleWidth = titleFontMetrics.stringWidth(title);
          int stringHeight = titleFontMetrics.getAscent();
          int stringWidth = (panelWidth - titleWidth) / 2;
          g.setFont(titleFont);
          g.drawString(title, stringWidth, stringHeight);
       
          int top = titleFontMetrics.getHeight();
          int bottom = labelFontMetrics.getHeight();
          if (maxValue == minValue) {
            return;
          }
          
          double scale = (panelHeight - top - bottom) / (maxValue - minValue);
          stringHeight = panelHeight - labelFontMetrics.getDescent();
          g.setFont(labelFont);
          
          for (int j = 0; j < values.length; j++) {
            int valueP = j * barWidth + 1;
            int valueQ = top;
            int height = (int) (values[j] * scale);
            if (values[j] >= 0) {
              valueQ += (int) ((maxValue - values[j]) * scale);
            } else {
              valueQ += (int) (maxValue * scale);
              height = -height;
            }
       
            g.setColor(colors[j]);
            g.fillRect(valueP, valueQ, barWidth - 2, height);
            g.setColor(Color.black);
            g.drawRect(valueP, valueQ, barWidth - 2, height);
       
            int labelWidth = labelFontMetrics.stringWidth(labels[j]);
            stringWidth = j * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(labels[j], stringWidth, stringHeight);
          }
       }
   }
}
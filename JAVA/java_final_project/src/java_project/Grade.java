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

   // �޸������� ���� �����͸� �о���� ���� �ʿ��� ���� i
   int i = 0;

   // ������ ���� �̸�
   String FILENAME = "grade.javaa.txt";
   BufferedReader br = null;
   FileReader fr = null;

   // table model ������ �� �𵨿� �� data ����
   String[][] data;
   DefaultTableModel dtm;
   grade_entire gentry = new grade_entire();

   // DefaultTableModel�� ���� ������ jtable ����
   JTable jt;

   // rank ���ʹ� ������ ����� �����ϱ� ���� ����
   Vector<Integer> rank = new Vector<Integer>();

   // sum�� ������ ������ ���ϱ� ���� ����
   float sum = 0;

   // Grade ������
   Grade() {
      setSize(1200, 700);

      setVisible(true);
      setLayout(new BorderLayout());

      add(gentry, BorderLayout.CENTER);
   }

   // ���� ���α׷��� ��ü�� ����ϰ� �ִ� �г�
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

   // �л��� �߰��� �� �ִ� ����� ���� panel
   class grade_edit extends JPanel {

      grade_edit() {

         setSize(100, 100);
         // �׵θ�����
         setBorder(new TitledBorder(new LineBorder(Color.gray, 2), "���� �Է�"));
         setLayout(new FlowLayout());

         // �й� textfield
         JTextField num_tf = new JTextField(7);

         // �̸� textfield
         JTextField name_tf = new JTextField(7);

         // ���� textfield
         JTextField grade_tf = new JTextField(7);

         // Ȯ�ι�ư
         JButton jb = new JButton("Ȯ��");

         add(new Label("�й�"));
         add(num_tf);
         add(new Label("�̸�"));
         add(name_tf);
         add(new Label("����"));
         add(grade_tf);
         add(jb);

         // Ȯ�� ��ư�� Ŭ���Ǹ� jtable�� ����Ʈ�� �߰��ǰ� �Ѵ�.
         jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               // ��ȣ, �й�,�̸�,���� �޾ƿ���
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

   // �л����� ������ ����� �����ִ� panel
   class grade_show extends JPanel {

      // grade_show �����ڿ����� ���̰�, �ٸ� Ŭ���������� ���̱� ������ ���������� ����
      JScrollPane scrollPane;

      grade_show() {
         setSize(300, 150);
         setBorder(new TitledBorder(new LineBorder(Color.gray, 2), "���� Ȯ��"));
         setLayout(new FlowLayout());

         // ���� �ҷ�����
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

            String column[] = { "��ȣ", "�й�", "�̸�", "����", "���", "����" };

            dtm = new DefaultTableModel(data, column);

            jt = new JTable(dtm);
            jt.setRowHeight(25);
            jt.setFont(new Font("Gothics", Font.ITALIC, 18));
            scrollPane = new JScrollPane(jt);

            // ó�� ������ ���� �� ȭ���� �����ֱ� ���� ���̺� ���� ��� �����͸� �����Ѵ�.
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

         // ���� ���� ����� ����ϴ� ��ư��
         JButton filebutton1 = new JButton("���� �ҷ�����");
         JButton score_rank = new JButton("��� ����");
         JButton grade_rank = new JButton("���� ���");
         JButton score_sort = new JButton("���� ����");

         // �� ��ư 4���� ������ ����

         // "���� �ҷ�����" ��ư�� �����ʸ� �ܴ�. ���õ� ���� ���� ������ �о�´�.
         filebutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               // Menubar���� ���õ� ������ �������� ���� Menubar ��ü�� �����Դ�.
               Menubar mm = Menubar.getInstance();

               // Menubar ��ü�� flag ���� true �̸� �ڹ� A������ �ҷ�����,
               // �׷��� ������ �ڹ� B������ �ҷ��´�.
               if (mm.getFlag()) {

                  fileIO("grade.javaa.txt");

               } else {

                  fileIO("grade.javab.txt");
               }
            }
         });

         // "���� ����" ��ư�� �����ʸ� �ܴ�. ������ ������������ �����Ѵ�.
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

         // "��� ����" ��ư�� �����ʸ� �ܴ�.
         score_rank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               // �л��� ������ rank ���Ϳ� add�ϰ�,
               rank.removeAllElements();
               for (int j = 0; j < dtm.getRowCount(); j++) {
                  rank.add(Integer.parseInt((String) dtm.getValueAt(j, 3)));
               }

               // rank�� �Ųٷ� �����Ѵ�. 98, 96, 90,.. �� ���ĵȴ�.
               Collections.sort(rank);
               Collections.reverse(rank);

               // rank�� ���� �ϳ��� �����ͼ�, �� ���� ������ ���� �л��� ã��,
               // rank�� �ε����� ��� column�� setValue �Ѵ�. ��� �ϼ�!
               for (int j = 0; j < dtm.getRowCount(); j++) {
                  dtm.setValueAt(rank.indexOf(((Integer.parseInt((String) dtm.getValueAt(j, 3))))) + 1, j, 4);
               }
            }
         });

         // "���� ���" ��ư�� �����ʸ� �ܴ�.
         // ��ü �л����� ���� 15% �� A+, ���� 30%�� Ao,... �� �����صξ���.
         // ��ü �л����� ����� ���, ������ ������ȴ�.
         grade_rank.addActionListener(new ActionListener() {
            String grade = "";

            @Override
            public void actionPerformed(ActionEvent arg0) {

               // ���� ����ϱ�
               for (int j = 0; j < dtm.getRowCount(); j++) {

                  // order�� �л��� ����� �������� �����̴�. �л� �� ��ŭ �ݺ��ؼ� ���� ������Ʈ�ȴ�.
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

                  // ������ ���� 6��° �࿡ ������ ���� ������ setValue���ش�.
                  dtm.setValueAt(grade, j, 5);
               }
            }
         });

         
         add(scrollPane);

         // ���� �����ִ� �� �Ʒ��� ��ư���� ��ġ
         JPanel jp0 = new JPanel(new GridLayout(4, 1, 0, 20));

         JPanel jp1 = new JPanel(new GridLayout(1, 1));
         jp1.add(score_sort);

         JPanel jp2 = new JPanel(new GridLayout(1, 2));
         jp2.add(score_rank);
         jp2.add(grade_rank);

         JButton store=new JButton("���� �����ϱ�");
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

      // ������ �о���� ���� �޼ҵ� fileIO
      public void fileIO(String filename) {

         try {
            // ������ ���� dtm�� �����͸� ��� ����
            int remove = dtm.getRowCount();
            for (int k = 0; k < remove; k++) {
               dtm.removeRow(0);
            }

            fr = new FileReader(filename);
            br = new BufferedReader(fr);

            String s;
            data = new String[20][6];

            // ���� �о���� data�� dtm�� addRow �Ѵ�.
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

   // ���� ��踦 �����ִ� panel
   class grade_statistics extends JPanel {

      // ��� panel�� �߰��� ��ư�� �� ����.
      // �����ڿ� �����ʿ��� ���̱� ������ ���� ������ �÷��־���.
      JButton b1 = new JButton("���ΰ�ħ");
      JLabel la1 = new JLabel("������ " + dtm.getRowCount() + " ��", SwingConstants.CENTER);
      float sum = summation();
      JLabel la2 = new JLabel("��� " + sum / dtm.getRowCount() + " ��", SwingConstants.CENTER);
      JLabel la3 = new JLabel("���� 50 % �� �ش��ϴ� �л��� ������    �� �Դϴ�.", SwingConstants.CENTER);

      grade_statistics() {

         setBorder(new TitledBorder(new LineBorder(Color.gray, 2), "��跮"));
         setLayout(new GridLayout(5, 1));

         // GridLayout�� �ٷ� "���ΰ�ħ" ��ư�� �߰��ϸ� ��ư�� �ʹ� Ŀ���� jpanel�� �ϳ� �� �������.
         JPanel jp = new JPanel(new FlowLayout());
         jp.add(b1);
         b1.setHorizontalAlignment(JButton.CENTER);
         b1.setBackground(Color.GRAY);
         b1.setForeground(Color.white);

         // ��ư�� �󺧵��� ��Ʈ�� �������־���.
         b1.setFont(new Font("Gothics", Font.ITALIC, 20));
         la1.setFont(new Font("Gothics", Font.ITALIC, 30));
         la2.setFont(new Font("Gothics", Font.ITALIC, 30));
         la3.setFont(new Font("Gothics", Font.ITALIC, 20));

         // �����̴�, ������ ������ �����̴��� ���� Ȯ���� �� �ִ�.
         JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
         slider.setPaintLabels(true);
         slider.setPaintTicks(true);
         slider.setPaintTrack(true);
         slider.setMajorTickSpacing(10);
         slider.setMinorTickSpacing(1);
         slider.setForeground(Color.BLUE);

         // �����̴��� ������ ����
         slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               // �����̴��κ��� ���� �޾ƿ���, ���� �� %�� ��Ÿ���� ���� 100���� ���� ���־���.
               int percentage = 100 - slider.getValue();

               String per_score = "";

               // �����̴��� �ۼ�Ʈ�� �л����� ����� ���Ѵ�.
               // ���� ���� 20%�� ������ �����̴��� ����Ű��, ���� 20% ����� �ش��ϴ� �л��� ������ ��3�� ��Ÿ����.
               // ���� 20% ����� �ش��ϴ� �л��� ������ �׳� �������� ��µȴ�.
               for (int j = 0; j < dtm.getRowCount(); j++) {
                  if ((int) (dtm.getRowCount() * percentage * 0.01) == (int) dtm.getValueAt(j, 4)) {
                     per_score = (String) dtm.getValueAt(j, 3);
                  }
               }
               la3.setText("���� " + percentage + " % �� �ش��ϴ� �л��� ������ " + per_score + " �� �Դϴ�.");
            }
         });

         // "���� ��ħ" ��ư�� ������
         // �������� �� �����, ����� ���Ѵ�.
         b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               la1.setText("������ " + dtm.getRowCount() + " ��");

               // �л����� ������ ������ summation�̶�� �޼ҵ带 ���� ���´�.
               float sum = summation();
               la2.setText("��� " + sum / dtm.getRowCount() + " ��");
            }
         });

         // ��ư�� ��, �����̴��� �ܴ�.
         add(jp);
         add(la1);
         add(la2);
         add(slider);
         add(la3);

         setVisible(true);
      }
   }

   // �л����� ������ ������ ���ϴ� �޼ҵ�
   public float summation() {
      sum = 0;
      for (int j = 0; j < dtm.getRowCount(); j++) {
         sum += Integer.parseInt((String) dtm.getValueAt(j, 3));
      }
      return sum;
   }
   
   // ���� ������ �����ִ� panel
   class grade_graph extends JPanel {

      // ������׷��� �׸��� ���� �ʿ��� ������, �����ʿ��� �ʿ��ϱ� ������ ���������� �����Ͽ���. 
      int[] values = new int[10];
      String[] labels = new String[] { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" };
      Color[] colors = new Color[] { Color.yellow, Color.orange, Color.pink,  Color.magenta, Color.red, 
                                   Color.green, Color.CYAN, Color.blue, Color.GRAY, Color.darkGray };
      int score;
      
      grade_graph() {
   
         setBorder(new TitledBorder(new LineBorder(Color.gray, 2), "�׷���"));
         setLayout(new BorderLayout());

         // "������׷�" ��ư ��ġ
         JButton histogram = new JButton("���� ������׷�");
         histogram.setBackground(Color.GRAY);
         histogram.setForeground(Color.WHITE);
         histogram.setFont(new Font("Gothics", Font.ITALIC, 20));
         
         JPanel jp=new JPanel(new FlowLayout());
         jp.add(histogram);
         add(jp, BorderLayout.NORTH);
         
         // value �迭 �ʱ�ȭ, �ʱ�ȭ������ ������, �����ʸ� ȣ���� ������ �迭 ���� �����ȴ�. 
         for(int k=0;k<10;k++) {
             values[k]=0;
         }
         
         // value ���� ���ϱ� ���� for��, 10����, 20����, 30����,...�� ������ ������ �Ѵ�. 
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
      
         // BarChart ��ü�� ������ add
         BarChart bc = new BarChart(values, labels, colors,"histogram");
         add(bc, BorderLayout.CENTER);
         
         // "������׷�" ��ư�� ������ ����, 
         // �����Ͱ� ����ǰ� ��ư�� ������, ����� �׷����� repaint �ȴ�. 
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
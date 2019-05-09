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
	// �޸������� ���� �����͸� �о���� ���� �ʿ��� ���� i
	int i = 0;
	// ������ ���� �̸�
	private static String FILENAME = "student.javaa.txt";
	BufferedReader br = null;
	FileReader fr = null;
	// DefaultTableModel�� ���� ������ jtable ����
	static JTable jt1;
	
	// table model ������ �� �𵨿� �� data ����
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

	// ���̺� �����ִ� panel
	class student_show extends JPanel {
	
		JScrollPane scrollPane;

		student_show() {
			setSize(300, 200);
			setBorder(new TitledBorder(new LineBorder(Color.gray, 1), "������ ���"));
			setLayout(new FlowLayout());
			JButton reset=new JButton("���̺� �ʱ�ȭ");
			JButton export=new JButton("���̺� ����");
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

				String column[] = { "��ȣ","�й�", "�̸�", "����", "��ȭ��ȣ", "�ּ�", "Ư�̻���" };

				dtm = new DefaultTableModel(data, column);

				jt1 = new JTable(dtm);
				//jt.setSize(500,500);
				jt1.setRowHeight(25);
				jt1.setFont(new Font("Gothics", Font.ITALIC, 15));
		
				scrollPane = new JScrollPane(jt1);
				scrollPane.setPreferredSize(new Dimension(700,580));
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
			add(scrollPane);
			add(reset);
			add(export);
			setVisible(true);
		}
	}

	// �л��� �߰��� �� �ִ� ����� ���� panel
	class student_edit extends JPanel {
		String sex;

		student_edit() {
			setSize(100, 100);
			// �׵θ�����
			setBorder(new TitledBorder(new LineBorder(Color.gray, 2), "�л��߰�"));
			setLayout(new GridLayout(7, 3, 20, 50));
			setSize(200,200);
		
			// �й� textfield
			JTextField num_tf = new JTextField(7);

			// �̸� textfield
			JTextField name_tf = new JTextField(7);
			
			// ���� radio button
			JRadioButton sex_rb1 = new JRadioButton("��");
			JRadioButton sex_rb2 = new JRadioButton("��");
			
			 sex_rb1.addActionListener(new ActionListener() {
				 
			 @Override public void actionPerformed(ActionEvent e) {
				 sex="��";
			  }

			  });
			 sex_rb2.addActionListener(new ActionListener() {
				 
				 @Override public void actionPerformed(ActionEvent e) {
					 sex="��";
				  }

				  });
			 
			
			ButtonGroup group = new ButtonGroup();
			group.add(sex_rb1);
			group.add(sex_rb2);

			// �޴��� textfield
			JTextField pnum_tf = new JTextField(7);
			
			// �ּ� textfield
			JTextField ad_tf = new JTextField(7);
			
			// Ư�̻��� textfield
			JTextField point_tf = new JTextField(7);
			JButton jb = new JButton("Ȯ��");
			JButton jb2 = new JButton("���Ϻҷ�����");
			
			add(new Label("�й�"));
			add(num_tf);
			add(new Label(""));
			add(new Label("�̸�"));
			add(name_tf);
			add(new Label(""));
			add(new Label("����"));
			add(sex_rb1);
			add(sex_rb2);
			add(new Label("�޴���"));
			add(pnum_tf);
			add(new Label(""));
			add(new Label("�ּ�"));
			add(ad_tf);
			add(new Label(""));
			add(new Label("Ư�̻���"));
			add(point_tf);
			add(new Label(""));
			add(new Label(""));
			add(jb);
			add(jb2);

			
			jb2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					// Menubar���� ���õ� ������ �������� ���� Menubar ��ü�� �����Դ�. 
					Menubar mm = Menubar.getInstance();

					// Menubar ��ü�� flag ���� true �̸� �ڹ� A������ �ҷ�����,
			        // �׷��� ������ �ڹ� B������ �ҷ��´�. 
					if (mm.getFlag()) {
						FILENAME="student.javaa.txt";
						fileIO(FILENAME);
						
					} else {
						FILENAME="student.javab.txt";
						fileIO(FILENAME);
					}
				}
			});
			// Ȯ�� ��ư�� Ŭ���Ǹ� jtable�� ����Ʈ�� �߰��ǰ� �Ѵ�.
			jb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				// ��ȣ, �й�,�̸�,����,�޴���,�ּ�,Ư�̻���
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
		
			
		
			
			// ���� �ҷ�����
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
		
	
	}



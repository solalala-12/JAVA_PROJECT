package java_project;

import javax.swing.*;
import javax.swing.table.*;


import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.*;


public class Main extends JFrame {

	Grade grade = new Grade();
	Student student = new Student();
	Menubar menubar = Menubar.getInstance();


	Main() {

		setTitle("Class Manager");
		setSize(1500, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		// tabpane 생성하기
		JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP);
		pane.addTab("성적관리", grade);
		pane.addTab("학생관리", student);
		c.add(menubar, BorderLayout.NORTH);
		c.add(pane, BorderLayout.CENTER);
		
	}

	
	

	public static void main(String[] args) {
		new Main();
	}
}

//프로그램의 상담을 담당하는 클래스, 한 객체만 생성해야 하기 때문에 singleton 패턴을 적용했다.
class Menubar extends JPanel {

	JComboBox subject_cb = new JComboBox();
	// 자바A를 선택했는지, 자바 B를 선택했는지 구별하기 위해 flag 변수를 생성했다.
	private boolean flag = true;

	private static Menubar singleton = new Menubar();

	public static Menubar getInstance() {
		return singleton;
	}

	private Menubar() {
		setSize(1000, 10);
		setVisible(true);

		// 담당교수 textfield
		JTextField tf = new JTextField(10);

		subject_cb.addItem("자바A");
		subject_cb.addItem("자바B");

		// 과목 콤보박스에 설정할 리스너
		// 자바A가 선택되면 flag가 true로 변경되고, 그렇지 않으면 flag가 false이다. 
		subject_cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				String item = (String) jcb.getSelectedItem();

				if (item.equals("자바A"))
					flag = true;
				else
					flag = false;
			}
		});

		// 년도를 선택할 combobox
		String[] year_list = { "2017", "2018" };
		JComboBox year_cb = new JComboBox<String>(year_list);

		// 학기를 선택할 radiobutton
		JRadioButton f_rb = new JRadioButton("1학기");
		JRadioButton s_rb = new JRadioButton("2학기");

		// radiobutton 그룹 생성
		ButtonGroup group = new ButtonGroup();
		group.add(f_rb);
		group.add(s_rb);

		// 확인 버튼
		JButton submitbutton = new JButton("확인");
		
		// 확인 버튼에 설정하는 리스너 
		// 학기와 담당교수를 입력하지 않으면 경고창이 뜬다. 
		// 모두 입력하고 "확인" 버튼을 누르면 다시 수정 할 수 없게 컴포넌트들이 비활성화된다.
		submitbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (f_rb.isSelected() == false && s_rb.isSelected() == false) {
					JOptionPane.showMessageDialog(f_rb, "해당 학기를 선택하세요");
					return;
				}
				
				if (tf.getText().equals("")) {
					JOptionPane.showMessageDialog(tf, "담당 교수를 입력하세요");
					return;
				}

				if (submitbutton.getText().equals("확인")) {
					subject_cb.setEnabled(false);
					year_cb.setEnabled(false);
					f_rb.setEnabled(false);
					s_rb.setEnabled(false);
					tf.setEnabled(false);
					submitbutton.setText("수정하기");
				} else {
					subject_cb.setEnabled(true);
					year_cb.setEnabled(true);
					f_rb.setEnabled(true);
					s_rb.setEnabled(true);
					tf.setEnabled(true);
					submitbutton.setText("확인");
				}
			}
		});

		// 과목 label과 combobox
		add(new JLabel("과목"));
		add(subject_cb);

		// 년도 labal과 combobox
		add(new JLabel("년도"));
		add(year_cb);

		// 학기 radiobutton
		add(f_rb);
		add(s_rb);

		// 담당교수 label과 textfield
		add(new JLabel("담당교수"));
		add(tf);
		add(submitbutton);
	}

	// Menubar 객체의 flag를 반한하는 getFlag 메소드 
	public boolean getFlag() {
		return flag;
	}
}
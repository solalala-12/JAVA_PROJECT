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

		// tabpane �����ϱ�
		JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP);
		pane.addTab("��������", grade);
		pane.addTab("�л�����", student);
		c.add(menubar, BorderLayout.NORTH);
		c.add(pane, BorderLayout.CENTER);
		
	}

	
	

	public static void main(String[] args) {
		new Main();
	}
}

//���α׷��� ����� ����ϴ� Ŭ����, �� ��ü�� �����ؾ� �ϱ� ������ singleton ������ �����ߴ�.
class Menubar extends JPanel {

	JComboBox subject_cb = new JComboBox();
	// �ڹ�A�� �����ߴ���, �ڹ� B�� �����ߴ��� �����ϱ� ���� flag ������ �����ߴ�.
	private boolean flag = true;

	private static Menubar singleton = new Menubar();

	public static Menubar getInstance() {
		return singleton;
	}

	private Menubar() {
		setSize(1000, 10);
		setVisible(true);

		// ��米�� textfield
		JTextField tf = new JTextField(10);

		subject_cb.addItem("�ڹ�A");
		subject_cb.addItem("�ڹ�B");

		// ���� �޺��ڽ��� ������ ������
		// �ڹ�A�� ���õǸ� flag�� true�� ����ǰ�, �׷��� ������ flag�� false�̴�. 
		subject_cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				String item = (String) jcb.getSelectedItem();

				if (item.equals("�ڹ�A"))
					flag = true;
				else
					flag = false;
			}
		});

		// �⵵�� ������ combobox
		String[] year_list = { "2017", "2018" };
		JComboBox year_cb = new JComboBox<String>(year_list);

		// �б⸦ ������ radiobutton
		JRadioButton f_rb = new JRadioButton("1�б�");
		JRadioButton s_rb = new JRadioButton("2�б�");

		// radiobutton �׷� ����
		ButtonGroup group = new ButtonGroup();
		group.add(f_rb);
		group.add(s_rb);

		// Ȯ�� ��ư
		JButton submitbutton = new JButton("Ȯ��");
		
		// Ȯ�� ��ư�� �����ϴ� ������ 
		// �б�� ��米���� �Է����� ������ ���â�� ���. 
		// ��� �Է��ϰ� "Ȯ��" ��ư�� ������ �ٽ� ���� �� �� ���� ������Ʈ���� ��Ȱ��ȭ�ȴ�.
		submitbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (f_rb.isSelected() == false && s_rb.isSelected() == false) {
					JOptionPane.showMessageDialog(f_rb, "�ش� �б⸦ �����ϼ���");
					return;
				}
				
				if (tf.getText().equals("")) {
					JOptionPane.showMessageDialog(tf, "��� ������ �Է��ϼ���");
					return;
				}

				if (submitbutton.getText().equals("Ȯ��")) {
					subject_cb.setEnabled(false);
					year_cb.setEnabled(false);
					f_rb.setEnabled(false);
					s_rb.setEnabled(false);
					tf.setEnabled(false);
					submitbutton.setText("�����ϱ�");
				} else {
					subject_cb.setEnabled(true);
					year_cb.setEnabled(true);
					f_rb.setEnabled(true);
					s_rb.setEnabled(true);
					tf.setEnabled(true);
					submitbutton.setText("Ȯ��");
				}
			}
		});

		// ���� label�� combobox
		add(new JLabel("����"));
		add(subject_cb);

		// �⵵ labal�� combobox
		add(new JLabel("�⵵"));
		add(year_cb);

		// �б� radiobutton
		add(f_rb);
		add(s_rb);

		// ��米�� label�� textfield
		add(new JLabel("��米��"));
		add(tf);
		add(submitbutton);
	}

	// Menubar ��ü�� flag�� �����ϴ� getFlag �޼ҵ� 
	public boolean getFlag() {
		return flag;
	}
}
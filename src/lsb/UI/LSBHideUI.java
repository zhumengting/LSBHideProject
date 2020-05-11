package lsb.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import lsb.operation.Engineer;

import javax.swing.UIManager;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

import java.awt.FlowLayout;

import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.JButton;

public class LSBHideUI {

	public JFrame frame;
	public JTextArea textField;//��һ������� first text
	public JTextArea textField_1;//�ڶ�������� second text
	public JTextArea textField_2;//����������� first text
	public JLabel lblNewLabel;//
	public JLabel lblNewLabelR;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LSBHideUI window = new LSBHideUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LSBHideUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 100, 850, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//get a panel named "bank"
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "LSB", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		Engineer a=new Engineer(this);
		
		// �˵���
		JMenuBar menuBar = new JMenuBar();
	    JMenu file = new JMenu("�˵�(F)");
	    // �������Ƿ�ΪF������ALT + F ���Դ����ò˵�
	    file.setMnemonic('F');

	    JMenuItem open = new JMenuItem("Search");
	    open.addActionListener(a);
	    JMenuItem quit = new JMenuItem("Exit");
	    quit.addActionListener(a);

	    file.add(open);
	    // ���ò˵��ָ���
	    file.addSeparator();
	    file.add(quit);

	    menuBar.add(file);

	    // ���ò˵�����ʹ�����ַ�ʽ���ò˵������Բ�ռ�ò��ֿռ�
	    frame.setJMenuBar(menuBar);
	    
		//create another panel named "Customer Information"
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "ԭͼ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 93, 325, 415);
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		lblNewLabel = new JLabel("ԭͼ");
//		lblNewLabel.setBounds(panel_1.getBounds());
		lblNewLabel.setFont(new Font("Broadway", Font.PLAIN, 22));
		panel_1.add(lblNewLabel);
		
		//create another panel named "Customer Information"
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Ƕ����Ϣ���ͼ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(495, 93, 325, 415);
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER));
				
		lblNewLabelR = new JLabel("Ƕ����Ϣ���ͼ");
//		lblNewLabelR.setBounds(panel_2.getBounds());
		lblNewLabelR.setFont(new Font("Broadway", Font.PLAIN, 22));
		panel_2.add(lblNewLabelR);
		
		JButton btnNewButton = new JButton("Read");
		btnNewButton.setBounds(10, 15,100, 29);
		btnNewButton.addActionListener(a);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Insert");
		btnNewButton_1.setBounds(125, 15, 80, 29);
		btnNewButton_1.addActionListener(a);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Save");
		btnNewButton_2.setBounds(10, 54, 100, 29);
		btnNewButton_2.addActionListener(a);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Get");
		btnNewButton_3.setBounds(125, 54, 80, 29);
		btnNewButton_3.addActionListener(a);
		panel.add(btnNewButton_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
	    scrollPane_1.setBounds(335, 100, 160, 100);
	    panel.add(scrollPane_1);
		
		textField = new JTextArea();
		textField.setBounds(335, 100, 160, 100);
		textField.setLineWrap(true);        //�����Զ����й��� 
		textField.setWrapStyleWord(true);            // ������в����ֹ���
		textField.setEditable(false);
		scrollPane_1.setViewportView(textField);
		textField.setText("��Ƕ����Ϣ��󳤶�:");
		
		JScrollPane scrollPane_2 = new JScrollPane();
	    scrollPane_2.setBounds(335, 210, 160, 100);
	    panel.add(scrollPane_2);
		
		textField_1 = new JTextArea();
		textField_1.setBounds(335, 210, 160, 100);
		textField_1.setLineWrap(true);        //�����Զ����й��� 
		textField_1.setWrapStyleWord(true);            // ������в����ֹ���
		scrollPane_2.setViewportView(textField_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
	    scrollPane_3.setBounds(335, 320, 160, 100);
	    panel.add(scrollPane_3);
		
		textField_2 = new JTextArea();
		textField_2.setBounds(335, 320, 160, 100);
		textField_2.setLineWrap(true);        //�����Զ����й��� 
		textField_2.setWrapStyleWord(true);            // ������в����ֹ���
		textField_2.setEditable(false);
		scrollPane_3.setViewportView(textField_2);
		textField_2.setText("��ȡ������Ϣ:");
	}
}

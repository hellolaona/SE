package Fuli;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Fuli3 extends JFrame {

	private static final long serialVersionUID = 3347254632537686808L;
	private JLabel a1; // 标签
	private JLabel a2;
	private JLabel a3;
	private JLabel a4;
	private JTextField b1; // 本金
	private JTextField b2; // 利率
	private JTextField b3; // 年份
	private JTextField b4; // 期望值
	private JButton c1;
	private JButton c2;
	private JButton c3;

	private JTextArea text; // 显示纯文本的多行区域

	private JPanel toolbar = new JPanel();

	private JPanel toolbar2 = new JPanel();
	boolean isadd = false;
	JRadioButtonMenuItem mrButton = new JRadioButtonMenuItem("每年添加一定的本金");

	public Fuli3() {
		setTitle("复利存款应用程序");
		creatComponents(); // 创建零件
		layoutComponents(); // 设计零件
		registerHandlers(); // 鼠标监控
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}

	private void creatComponents() {
		a1 = new JLabel("本 金 ");
		a2 = new JLabel("年利率 ");
		a3 = new JLabel("年 数 ");
		a4 = new JLabel("期望值");
		b1 = new JTextField(10);
		b2 = new JTextField(10);
		b3 = new JTextField(10);
		b4 = new JTextField(10);
		c1 = new JButton("复利");
		c2 = new JButton("清除");
		c3 = new JButton("单利");
		text = new JTextArea();

	}

	private void layoutComponents() {
		setLayout(new FlowLayout());
		JPanel panel1 = new JPanel();
		panel1.add(a1);
		panel1.add(b1);
		JPanel panel2 = new JPanel();
		panel2.add(a2);
		panel2.add(b2);
		JPanel panel3 = new JPanel();
		panel3.add(a3);
		panel3.add(b3);
		JPanel panel6 = new JPanel();
		panel6.add(a4);
		panel6.add(b4);

		JPanel leftpanel = new JPanel(new GridLayout(3, 1));
		leftpanel.add(panel1);
		leftpanel.add(panel2);
		leftpanel.add(panel3);

		leftpanel.add(panel6);
		JScrollPane panel5 = new JScrollPane(text);
		panel5.setPreferredSize(new Dimension(400, 200));

		toolbar.add(c1);
		toolbar.add(c2);
		toolbar.add(c3);
		toolbar2.add(mrButton);

		add(leftpanel);
		// add(mrButton);
		add(toolbar2, BorderLayout.NORTH);
		add(panel5);
		add(toolbar, BorderLayout.EAST);
	}

	private void registerHandlers() {
		c1ActionEventHander hander1 = new c1ActionEventHander();
		c1.addActionListener(hander1);
		c2ActionEventHander hander2 = new c2ActionEventHander();
		c2.addActionListener(hander2);
		c3ActionEventHander hander3 = new c3ActionEventHander();
		c3.addActionListener(hander3);
		mrButtonActionEventHander hander4 = new mrButtonActionEventHander();
		mrButton.addActionListener(hander4);

	}

	private class c1ActionEventHander implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double principal;
			double amount;
			double rate;
			int n;
			NumberFormat currencyformatter = NumberFormat.getCurrencyInstance(); // 字符串转化为数字
			String output = "年" + "/" + "复利存款";
//			int year = 1;
//
//		//	principal = Double.parseDouble(b1.getText()); // b1本金
//			rate = Double.parseDouble(b2.getText()); // b2利率
//			n = Integer.parseInt(b3.getText()); // b3年份
//			amount = Double.parseDouble(b4.getText()); // b4期望值
			int year = 1;
//			rate = Double.parseDouble(b2.getText());
//			n = Integer.parseInt(b3.getText());
				
			if (b1.getText().equals("")&& b2.getText() != null
					&& b3.getText() != null && b4.getText() != null) {
				
				rate = Double.parseDouble(b2.getText());
				n = Integer.parseInt(b3.getText());
					amount = Double.parseDouble(b4.getText());
					principal = 0;
					while (year <= n) {
						principal = amount / (Math.pow(1 + rate, year));
						year = year + 1;
					}

					output = "本金" + currencyformatter.format(principal) + "\n";
					text.setText(output);
				
			}

			else if (b2.getText().equals("") && b1.getText() != null
					&& b3.getText() != null && b4.getText() != null) // 求利率
			{
				principal = Double.parseDouble(b1.getText()); // b1本金
				rate =0;
				n = Integer.parseInt(b3.getText()); // b3年份
				amount = Double.parseDouble(b4.getText()); // b4期望值
				rate = java.lang.StrictMath.pow(amount / principal, 1.0 / n) - 1;
				output = "利率" + rate + "\n";
				text.setText(output);
			}

			else if (b3.getText().equals("") && b1.getText() != null
					&& b2.getText() != null && b4.getText() != null) // 求年份
			{
				principal = Double.parseDouble(b1.getText()); // b1本金
				amount = Double.parseDouble(b4.getText()); // b4期望值
				rate = Double.parseDouble(b2.getText());
				int n2=1;
                  while(principal<amount){
                	  principal=principal * Math.pow(1 + rate, n2);
                	  n2+=1;
                  }
                  output = "至少年数" + n2+"\n";
  				text.setText(output);
			}

			else if (b4.getText().equals("") && b1.getText() != null
					&& b3.getText() != null && b2.getText() != null) // 求期望值
			{
				rate = Double.parseDouble(b2.getText());
				n = Integer.parseInt(b3.getText());
				principal = Double.parseDouble(b1.getText()); // b1本金
				while (year <= n) {
						amount = principal * Math.pow(1 + rate, year);
						output += String.valueOf(year) + "\t\t\t"
								+ currencyformatter.format(amount) + "\n";
						year = year + 1;
					}

					text.setText(output);
			}
		}

	}

	private class c2ActionEventHander implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			b1.setText("");
			b2.setText("");
			b3.setText("");
			text.setText("");
		}
	}

	private class c3ActionEventHander implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double principal;
			double amount;
			double rate;
			int n;
			NumberFormat currencyformatter = NumberFormat.getCurrencyInstance();
			String output = "年" + "/" + "单利存款";
			int year = 1;

			rate = Double.parseDouble(b2.getText());
			n = Integer.parseInt(b3.getText());
			if (b1.getText().equals("")) {
				if (b4.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入本金或者期望值");

				} else {
					amount = Double.parseDouble(b4.getText());
					principal = 0;
					while (year <= n) {
						principal = amount / (1 + rate * year);
						year = year + 1;
					}

					output = "本金" + currencyformatter.format(principal) + "\n";
					text.setText(output);
				}
			} else {
				principal = Double.parseDouble(b1.getText()); // 字符串转化为数字

				if (b4.getText().equals("")) {
					while (year <= n) {
						amount = principal * (1 + rate * year);
						output += String.valueOf(year) + "\t\t\t"
								+ currencyformatter.format(amount) + "\n";
						year = year + 1;
					}

					text.setText(output);
				} else {
					JOptionPane.showMessageDialog(null, "本金和期望值输入一个即可");
				}
			}

		}
	}

	private class mrButtonActionEventHander implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (isadd == false) {
				isadd = true;
			}
			if (isadd == true) {
				isadd = false;
			}
		
		}
	}

	public static void main(String[] args) {

		int Windowswidth = 500;
		int Windowsheight = 400;
		Fuli3 frame = new Fuli3();
		frame.setVisible(true);
		frame.setSize(Windowswidth, Windowsheight);
		frame.setResizable(false);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		frame.setLocation((width - Windowswidth) / 2,
				(height - Windowsheight) / 2); // 窗口居中
	}
}
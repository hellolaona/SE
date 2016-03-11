package ch1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class fuli extends JFrame {

    private static final long serialVersionUID = 3347254632537686808L;
    private JLabel a1;             //标签
    private JLabel a2;
    private JLabel a3;
    private JTextField b1;           //本金
    private JTextField b2;           //利率
    private JTextField b3;          //年份
    private JButton c1;             
    private JButton c2;
    private JButton c3;

    private JTextArea text;        // 显示纯文本的多行区域

    public fuli() {
        creatComponents();           //创建零件
        layoutComponents();          //设计零件
        registerHandlers();          //鼠标监控
        setTitle("复利存款应用程序");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    private void creatComponents() {
        a1 = new JLabel("本 金 ");
        a2 = new JLabel("年利率 ");
        a3 = new JLabel("年 数 ");
        b1 = new JTextField(10);
        b2 = new JTextField(10);
        b3 = new JTextField(10);
        c1 = new JButton("复利");
        c2 = new JButton("清除");
        c3=new JButton("单利");
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
        JPanel panel4 = new JPanel();
        panel4.add(c1);
        panel4.add(c2);
        panel4.add(c3);
        JPanel leftpanel = new JPanel(new GridLayout(4, 1));
        leftpanel.add(panel1);
        leftpanel.add(panel2);
        leftpanel.add(panel3);
        leftpanel.add(panel4);
        JScrollPane panel5 = new JScrollPane(text);
        panel5.setPreferredSize(new Dimension(200, 150));
        add(leftpanel);
        add(panel5);
    }

    private void registerHandlers() {
        c1ActionEventHander hander1 = new c1ActionEventHander();
        c1.addActionListener(hander1);
        c2ActionEventHander hander2 = new c2ActionEventHander();
        c2.addActionListener(hander2);
        c3ActionEventHander hander3=new c3ActionEventHander();
        c3.addActionListener(hander3);

    }

    private class c1ActionEventHander implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double principal=0;
            double amount;
            double rate;
            int n;
            NumberFormat currencyformatter = NumberFormat.getCurrencyInstance();        //字符串转化为数字
            String output = "年" + "/" + "复利存款";
            int year = 1;
            principal = Double.parseDouble(b1.getText());                            //字符串转化为数字
            rate = Double.parseDouble(b2.getText());
            n = Integer.parseInt(b3.getText());
            while (year <= n) {
                amount = principal * Math.pow(1 + rate, year);
                output += String.valueOf(year) + "\t" + currencyformatter.format(amount) + "\n";
                year = year + 1;
            }
            text.setText(output);
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
            principal = Double.parseDouble(b1.getText());
            rate = Double.parseDouble(b2.getText());
            n = Integer.parseInt(b3.getText());
            while (year <= n) {
                amount = principal * (1 + rate* year);
                output += String.valueOf(year) + "\t" + currencyformatter.format(amount) + "\n";
                year = year + 1;
            }
            text.setText(output);
        }
    }
    public static void main(String[] args) {
        fuli frame = new fuli();
        frame.setVisible(true);
        frame.setSize(450, 200);
        frame.setResizable(false);
    }
}

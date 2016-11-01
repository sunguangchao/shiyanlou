import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * Created by 11981 on 2016/10/31.
 * �Ľ������Ͽ�����ʾ���в�������Ĺ��ܣ����ڻ�������ʾ����
 */
public class Calculator {

    // ������1��Ϊ�˳���İ�ȫ����ֵһ�����ã�������������Ϊ0��
    String str1 = "0";

    // ������2
    String str2 = "0";

    // �����
    String signal = "+";

    // ������
    String result = "";

    // ����k1��k2Ϊ״̬����

    // ����1����ѡ�����뷽�򣬽�Ҫд��str2��str2
    int k1 = 1;
    // ����2���ڼ�¼���ż��Ĵ�������� k2>1 ˵�����е��� 2+3-9+8 �����Ķ��������
    int k2 = 1;
    // ����3���ڱ�ʶ str1 �Ƿ���Ա���0 ������1ʱ���ԣ�������1ʱ���ܱ���0
    int k3 = 1;
    // ����4���ڱ�ʶ str2 �Ƿ���Ա���0
    int k4 = 1;
    // ����5���ڿ���С����ɷ�¼�룬����1ʱ���ԣ���Ϊ1ʱ�������С���㱻����
    int k5 = 1;
    // store�����������ڼĴ��������ڼ�¼�Ƿ��������·��ż�
    JButton store;

    @SuppressWarnings("rawtypes")
    Vector vt = new Vector(20, 10);

    // ��������UI������󲢳�ʼ��

    JFrame frame = new JFrame("Calculator");

    JTextField result_TextField = new JTextField(result,20);
    JButton clear_Button = new JButton("clear");

    // ���ּ�0��9
    JButton button0 = new JButton("0");
    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    JButton button4 = new JButton("4");
    JButton button5 = new JButton("5");
    JButton button6 = new JButton("6");
    JButton button7 = new JButton("7");
    JButton button8 = new JButton("8");
    JButton button9 = new JButton("9");

    // �������ť���Ӽ��˳��Լ�С�����
    JButton button_dot = new JButton(".");
    JButton button_add = new JButton("+");
    JButton button_subtract = new JButton("-");
    JButton button_multiply = new JButton("*");
    JButton button_division = new JButton("/");

    JButton button_equal = new JButton("=");



    public Calculator(){
        // Ϊ��ť���õ�Ч����������ͨ����Ӧ�ļ��̰�������������
        button0.setMnemonic(KeyEvent.VK_0);
        // �����ı���Ϊ�Ҷ��룬ʹ����ͽ����������ʾ
        result_TextField.setHorizontalAlignment(JTextField.RIGHT);


        JPanel pan = new JPanel();
        // ���ø������Ĳ���Ϊ�������У��߾�Ϊ5����
        pan.setLayout(new GridLayout(4,4,5,5));

        pan.add(button7);
        pan.add(button8);
        pan.add(button9);
        pan.add(button_division);
        pan.add(button4);
        pan.add(button5);
        pan.add(button6);
        pan.add(button_multiply);
        pan.add(button1);
        pan.add(button2);
        pan.add(button3);
        pan.add(button_subtract);
        pan.add(button0);
        pan.add(button_dot);
        pan.add(button_equal);
        pan.add(button_add);
        pan.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JPanel pan2 = new JPanel();
        pan2.setLayout(new BorderLayout());
        pan2.add(result_TextField,BorderLayout.WEST);
        pan2.add(clear_Button,BorderLayout.EAST);

        frame.setLocation(300,200);// ���������ڳ�������Ļ�ϵ�λ��
        frame.setVisible(false);// ���ô��岻�ܵ���С

        frame.getContentPane().setLayout(new BorderLayout());//??
        frame.getContentPane().add(pan,BorderLayout.NORTH);
        frame.getContentPane().add(pan2,BorderLayout.CENTER);

        frame.pack();//??
        frame.setVisible(true);

        class Listener implements ActionListener{
            @SuppressWarnings("unchecked")
            public void actionPerformed(ActionEvent e){
                String ss = ((JButton)e.getSource()).getText();
                store = (JButton)e.getSource();
                vt.add(store);
                if(k1 == 1){
                    if(k3 == 1){
                        str1 = "";
                        k5 = 1;// ��ԭ����k5״̬
                    }
                    str1 = str1 + ss;
                    k3 = k3 +1;
                    result_TextField.setText(str1);
                }else if (k1 == 2) {
                    if (k4 == 1) {
                        str2 = "";
                        k5 = 1;// ��ԭ����k5״̬
                    }
                    str2 = str2 + ss;
                    k4 = k4 + 1;
                    result_TextField.setText(str2);
                }
            }
        }


        class Listener_signal implements ActionListener{
            @SuppressWarnings("unchecked")
            public void actionPerformed(ActionEvent e){
                String ss2 = ((JButton)e.getSource()).getText();
                store = (JButton)e.getSource();
                vt.add(store);
                if(k2 == 1){
                    k1 = 2;
                    k5 = 1;
                    signal = ss2;
                    k2 = k2 + 1;
                }else{
                    int a = vt.size();
                    JButton c = (JButton)vt.get(a - 2);
                    if(!(c.getText().equals("+"))&&
                            !(c.getText().equals("-"))&&
                            !(c.getText().equals("*"))&&
                            !(c.getText().equals("/")))
                    {
                        cal();
                        str1 = result;
                        k1 = 2;
                        k5 = 1;
                        k4 = 1;
                        signal = ss2;
                    }
                    k2 = k2 + 1;

                }
            }
        }
        class Listener_clear implements ActionListener{
            @SuppressWarnings("unchecked")
            public void actionPerformed(ActionEvent e){

                store = (JButton)e.getSource();
                vt.add(store);
                k5 = 1;
                k2 = 1;
                k1 = 1;
                k3 = 1;
                k4 = 1;
                str1 = "0";
                str2 = "0";
                signal = "";
                result = "";
                result_TextField.setText(result);
                vt.clear();

            }

        }

        class Listener_equal implements ActionListener{
            @SuppressWarnings("unchecked")
            public void actionPerformed(ActionEvent e){

                store = (JButton)e.getSource();
                vt.add(store);
                cal();
                k1 = 1;
                k2 = 1;
                k3 = 1;
                k4 = 1;

                str1 = result;
            }
        }
        class Listener_dot implements ActionListener{
            @SuppressWarnings("unchecked")
            public void actionPerformed(ActionEvent e){
                store = (JButton)e.getSource();
                vt.add(store);
                if(k5 == 1) {
                    String ss2 = ((JButton) e.getSource()).getText();
                    if (k1 == 1) {
                        if (k3 == 1) {
                            str1 = "";
                            k5 = 1;
                        }
                        str1 = str1 + ss2;
                        k3 += 1;
                        result_TextField.setText(result);
                    } else if (k1 == 2) {
                        if (k4 == 1) {
                            str2 = "";
                            k5 = 1;

                        }
                        str2 = str2 + ss2;
                        k4 += 1;
                        result_TextField.setText(result);
                    }
                }
                k5 = k5 + 1;
            }


        }

        Listener_equal listener_equal = new Listener_equal();
        Listener listener = new Listener();
        Listener_signal listener_signal = new Listener_signal();
        // ���������
        Listener_clear listener_clear = new Listener_clear();
        Listener_dot listener_dot = new Listener_dot();

        button7.addActionListener(listener);
        button8.addActionListener(listener);
        button9.addActionListener(listener);
        button_division.addActionListener(listener_signal);
        button4.addActionListener(listener);
        button5.addActionListener(listener);
        button6.addActionListener(listener);
        button_multiply.addActionListener(listener_signal);
        button1.addActionListener(listener);
        button2.addActionListener(listener);
        button3.addActionListener(listener);
        button_subtract.addActionListener(listener_signal);
        button0.addActionListener(listener);
        button_dot.addActionListener(listener_dot);
        button_equal.addActionListener(listener_equal);
        button_add.addActionListener(listener_signal);
        clear_Button.addActionListener(listener_clear);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void cal(){
        double a2;
        double b2;
        String c = signal;
        double result2 = 0;

        if (c.equals("")) {
            result_TextField.setText("Please input operator");

        } else {
            // �ֶ�����С���������
            if (str1.equals("."))
                str1 = "0.0";
            if (str2.equals("."))
                str2 = "0.0";
            a2 = Double.valueOf(str1).doubleValue();
            b2 = Double.valueOf(str2).doubleValue();

            if (c.equals("+")) {
                result2 = a2 + b2;
            }if (c.equals("-")) {
                result2 = a2 - b2;
            }
            if (c.equals("*")) {
                result2 = a2 * b2;
            }
            if (c.equals("/")) {
                if (b2 == 0) {
                    result2 = 0;
                } else {
                    result2 = a2 / b2;
                }
    }


            result = ((new Double(result2)).toString());

            result_TextField.setText(result);
        }
    }
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        // ���ó�����ʾ�Ľ����񣬿���ȥ��
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calculator cal = new Calculator();
    }




}

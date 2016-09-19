package util;

import gui.panel.WorkingPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 11981 on 2016/9/9.
 */
public class CenterPanel extends JPanel {
    private double rate;//�������
    private JComponent c;//��ʾ�Ŀؼ�
    private boolean strech;//�Ƿ�����

    public CenterPanel(double rate, boolean strech) {//������Ĺ��캯������˶�̬
        this.setLayout(null);
        this.rate = rate;
        this.strech = strech;
    }

    public CenterPanel(double rate) {
        this(rate, true);
    }

    /*
    * ��repaint�����У��ͻ�ʹ�þ��Զ�λ�ķ�ʽ����������м�λ�á�
    * ���strech��true���ͻ�������������Ĵ�С����������Ĵ�С���ﵽ�����Ч��
    * ���strech��false, ��ʹ�������preferredSize����������Ч����
    */
    public void repaint() {

        if (null != c) {
            Dimension containerSize = this.getSize();
            Dimension componentSize = c.getPreferredSize();//getPreferedSize�����ǻ�ȡ�����ѡ��С

            if (strech)
                c.setSize((int) (containerSize.width * rate), (int) (containerSize.height * rate));
            else
                c.setSize(componentSize);
            /*
            * setSize:���� Dimension ����Ĵ�С����Ϊָ���Ŀ�Ⱥ͸߶ȣ���˫���ȱ�ʾ����
            * ע�⣬��� width �� height ���� Integer.MAX_VALUE��
            * ������������Ϊ Integer.MAX_VALUE��
            */

            c.setLocation(containerSize.width / 2 - c.getSize().width / 2,
                    containerSize.height / 2 - c.getSize().height / 2);

        }
        super.repaint();
    }

    /*
    * ʹ��show������ʾ���,show�����е�˼·�ǣ�
    * �Ȱ���������е�������Ƴ���Ȼ����µ�����ӽ��������ҵ���updateUI���н�����Ⱦ��
    * updateUI�ᵼ��swingȥ����repaint()������
    */
    public void show(JComponent p) {
        this.c = p;
        Component[] cs = getComponents();
        for (Component c : cs) {
            remove(c);
        }
        add(p);

        if (p instanceof WorkingPanel)
            //instanceof �����������������ʱָ�������Ƿ����ض����һ��ʵ����
            // instanceofͨ������һ������ֵ��ָ������������Ƿ�������ض�����������������һ��ʵ����
            ((WorkingPanel) p).updateData();
        this.updateUI();
    }

    public static void main(String[] args)
    {
        JFrame f = new JFrame();
        f.setSize(200,200);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(0.85,true);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        JButton b = new JButton("abc");
        cp.show(b);
    }


}

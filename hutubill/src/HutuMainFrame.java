import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 11981 on 2016/9/9.
 */
public class HutuMainFrame {
    public static void main(String[] args)
    {
        JFrame f = new JFrame();
        f.setSize(500,450);
        f.setTitle("һ����Ϳ��");
        f.setLocationRelativeTo(null);
        f.setResizable(false);//���ÿ�ܵĴ�С�Ƿ������û��ı�
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //���û��ر�������ʱ�������˳�
        JToolBar tb = new JToolBar();
        JButton bSpend = new JButton("����һ��");
        JButton bRecond = new JButton("��һ��");
        JButton bCategory = new JButton("���ѷ���");
        JButton bReport = new JButton("�����ѱ���");
        JButton bConfig = new JButton("����");
        JButton bBackup = new JButton("����");
        JButton bRecover = new JButton("�ָ�");

        tb.add(bSpend);
        tb.add(bRecond);
        tb.add(bCategory);
        tb.add(bReport);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);
        f.setLayout(new BorderLayout());
        f.add(tb, BorderLayout.NORTH);
        f.add(new JPanel(),BorderLayout.CENTER);
        f.setVisible(true);//��ʾ���

        bSpend.addActionListener(new ActionListener() {//��ť���������
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        bRecond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        bCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        bConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        bBackup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        bRecover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}

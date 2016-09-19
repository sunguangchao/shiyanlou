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
        f.setTitle("一本糊涂账");
        f.setLocationRelativeTo(null);
        f.setResizable(false);//设置框架的大小是否允许用户改变
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //在用户关闭这个框架时，程序退出
        JToolBar tb = new JToolBar();
        JButton bSpend = new JButton("消费一览");
        JButton bRecond = new JButton("记一笔");
        JButton bCategory = new JButton("消费分类");
        JButton bReport = new JButton("月消费报表");
        JButton bConfig = new JButton("设置");
        JButton bBackup = new JButton("备份");
        JButton bRecover = new JButton("恢复");

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
        f.setVisible(true);//显示框架

        bSpend.addActionListener(new ActionListener() {//按钮加入监听器
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

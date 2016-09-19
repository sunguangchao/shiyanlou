package gui.panel;

import gui.panel.page.SpendPage;
import service.SpendService;
import util.CircleProgressBar;
import util.ColorUtil;

import javax.swing.*;
import java.awt.*;

import static util.GUIUtil.setColor;
import static util.GUIUtil.showPanel;

/**
 * Created by 11981 on 2016/9/9.
 */
public class SpendPanel extends WorkingPanel{//�������Ϊpublic
    public static SpendPanel instance = new SpendPanel();
    //��������Ϊ����ģʽ
    JLabel lMonthSpend = new JLabel("��������");
    JLabel lTodaySpend = new JLabel("��������");
    JLabel lAvgSpendPerDay = new JLabel("�վ�����");
    JLabel lMonthLeft = new JLabel("����ʣ��");
    JLabel lDayAvgAvailable = new JLabel("�վ�����");
    JLabel lMonthLeftDay = new JLabel("������ĩ");

    JLabel vMonthSpend = new JLabel("��2300");
    JLabel vTodaySpend = new JLabel("��25");
    JLabel vAvgSpendPerDay = new JLabel("��120");
    JLabel vMonthAvailable = new JLabel("��2084");
    JLabel vDayAvgAvailable = new JLabel("��389");
    JLabel vMonthLeftDay = new JLabel("15��");

    CircleProgressBar bar;
    public SpendPanel()
    {
        this.setLayout(new BorderLayout());
        bar = new CircleProgressBar();
        bar.setBackgroundColor(ColorUtil.blueColor);

        setColor(ColorUtil.grayCColor,lMonthSpend,lTodaySpend,lAvgSpendPerDay,lMonthSpend,lDayAvgAvailable,
                lMonthLeftDay,vAvgSpendPerDay,vMonthAvailable,vDayAvgAvailable,vMonthLeftDay);
        setColor(ColorUtil.blueColor,vMonthSpend,vTodaySpend);

        vMonthSpend.setFont(new Font("΢���ź�", Font.BOLD, 23));
        vTodaySpend.setFont(new Font("΢���ź�", Font.BOLD, 23));
        this.add(center(),BorderLayout.CENTER);
        this.add(south(),BorderLayout.SOUTH);


    }
    private JPanel center()
    {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(west(),BorderLayout.WEST);
        p.add(east());
        return p;
    }
    private Component east()
    {
        return bar;
    }
    private Component west(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4,1));
        p.add(lMonthSpend);
        p.add(vMonthSpend);
        p.add(lTodaySpend);
        p.add(vTodaySpend);
        return p;
    }

    private JPanel south() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 4));

        p.add(lAvgSpendPerDay);
        p.add(lMonthLeft);
        p.add(lDayAvgAvailable);
        p.add(lMonthLeftDay);
        p.add(vAvgSpendPerDay);
        p.add(vMonthAvailable);
        p.add(vDayAvgAvailable);
        p.add(vMonthLeftDay);

        return p;
    }
    public static void main(String[] args)
    {
        showPanel(SpendPanel.instance);
    }
    public void updateData()
    {
        SpendPage spend = new SpendService().getSpendPage();
        vMonthSpend.setText(spend.monthSpend);
        vTodaySpend.setText(spend.todaySpend);
        vAvgSpendPerDay.setText(spend.avgSpendPerDay);
        vMonthAvailable.setText(spend.monthAvailable);
        vDayAvgAvailable.setText(spend.dayAvgAvailable);
        vMonthLeftDay.setText(spend.monthLeftDay);

        bar.setProgress(spend.usagePercentage);
        if (spend.isOverSpend) {
            vMonthAvailable.setForeground(ColorUtil.warningColor);
            vMonthSpend.setForeground(ColorUtil.warningColor);
            vTodaySpend.setForeground(ColorUtil.warningColor);

        } else {
            vMonthAvailable.setForeground(ColorUtil.grayCColor);
            vMonthSpend.setForeground(ColorUtil.blueColor);
            vTodaySpend.setForeground(ColorUtil.blueColor);
        }
        bar.setForegroundColor(ColorUtil.getByPercentage(spend.usagePercentage));
        addListener();


    }
    public void addListener()
    {

    }


}

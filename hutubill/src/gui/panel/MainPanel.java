package gui.panel;

import gui.panel.listener.ToolBarListener;
import util.CenterPanel;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;


/**
 * Created by 11981 on 2016/9/10.
 */
public class MainPanel extends JPanel {
    static {
        GUIUtil.useLNF();//皮肤
    }
    public static MainPanel instance = new MainPanel();
    public JToolBar tb = new JToolBar();//控件声明为public
    public JButton bSpend = new JButton();
    public JButton bRecord = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();

    public CenterPanel workingPanel;

    private MainPanel()
    {
        GUIUtil.setImageIcon(bSpend,"home.png","消费一览");//设置图标
        GUIUtil.setImageIcon(bRecord, "record.png", "记一笔");
        GUIUtil.setImageIcon(bCategory, "category2.png", "消费分类");
        GUIUtil.setImageIcon(bReport, "report.png", "月消费报表");
        GUIUtil.setImageIcon(bConfig, "config.png", "设置");
        GUIUtil.setImageIcon(bBackup, "backup.png", "备份");
        GUIUtil.setImageIcon(bRecover, "restore.png", "恢复");

        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bCategory);
        tb.add(bReport);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);
        tb.setFloatable(false);
        workingPanel = new CenterPanel(0.8);

        setLayout(new BorderLayout());
        add(tb,BorderLayout.NORTH);
        addListener();

    }
    private void addListener() {
        ToolBarListener listener = new ToolBarListener();

        bSpend.addActionListener(listener);
        bRecord.addActionListener(listener);
        bCategory.addActionListener(listener);
        bReport.addActionListener(listener);
        bConfig.addActionListener(listener);
        bBackup.addActionListener(listener);
        bRecover.addActionListener(listener);

    }
    public static void main(String[] args) {
        GUIUtil.showPanel(MainPanel.instance, 1);
    }
}

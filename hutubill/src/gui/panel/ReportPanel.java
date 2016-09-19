package gui.panel;

import entity.Record;
import service.ReportService;
import util.ChartUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.util.*;

import static util.GUIUtil.showPanel;

/**
 * Created by 11981 on 2016/9/11.
 */
public class ReportPanel extends WorkingPanel {

    public static ReportPanel instance = new ReportPanel();

    JLabel l = new JLabel();

    public ReportPanel() {
        this.setLayout(new BorderLayout());
        java.util.List<Record> rs = new ReportService().listThisMonthRecords();
        //如果没有java.util会报错
        Image i = ChartUtil.getImage(rs, 400, 300);
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);
        this.add(l);
        addListener();
    }

    public static void main(String[] args) {
        showPanel(ReportPanel.instance);
    }

    @Override
    public void updateData() {
        java.util.List<Record> rs = new ReportService().listThisMonthRecords();
        Image i = ChartUtil.getImage(rs, 350, 250);
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);
    }

    @Override
    public void addListener() {

    }


}

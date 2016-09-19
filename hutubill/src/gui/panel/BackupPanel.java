package gui.panel;

import gui.panel.listener.BackupListener;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;

/**
 * Created by 11981 on 2016/9/11.
 */
public class BackupPanel extends WorkingPanel {

    static {
        GUIUtil.useLNF();
    }

    public static BackupPanel instance = new BackupPanel();
    JButton bBackup = new JButton("±¸·Ý");

    public BackupPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, bBackup);
        this.add(bBackup);
        addListener();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(BackupPanel.instance);
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        BackupListener listener = new BackupListener();
        bBackup.addActionListener(listener);
    }
}

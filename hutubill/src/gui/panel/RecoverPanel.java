package gui.panel;

import gui.panel.listener.RecoverListener;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;

/**
 * Created by 11981 on 2016/9/11.
 */
public class RecoverPanel extends WorkingPanel{
    static{
        GUIUtil.useLNF();
    }
    public static RecoverPanel instance = new RecoverPanel();

    JButton bRecover =new JButton("»Ö¸´");

    public RecoverPanel() {

        GUIUtil.setColor(ColorUtil.blueColor, bRecover);
        this.add(bRecover);

        addListener();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecoverPanel.instance);
    }

    @Override
    public void updateData() {
        // TODO Auto-generated method stub

    }

    @Override
    public void addListener() {
        RecoverListener listener = new RecoverListener();
        bRecover.addActionListener(listener);
    }
}

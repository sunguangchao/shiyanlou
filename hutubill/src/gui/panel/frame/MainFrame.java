package gui.panel.frame;

import gui.panel.MainPanel;

import javax.swing.*;

/**
 * Created by 11981 on 2016/9/11.
 */
public class MainFrame extends JFrame{
    public static MainFrame instance = new MainFrame();

    private MainFrame(){
        this.setSize(500,450);
        this.setTitle("Ò»±¾ºýÍ¿ÕË");
        this.setContentPane(MainPanel.instance);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

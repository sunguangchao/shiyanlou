package startup;

import gui.panel.MainPanel;
import gui.panel.SpendPanel;
import gui.panel.frame.MainFrame;
import util.GUIUtil;

import javax.swing.*;

/**
 * Created by 11981 on 2016/9/11.
 */
public class Bootstrap{
    public static void main(String[] args) throws Exception
    {
        GUIUtil.useLNF();

        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                MainFrame.instance.setVisible(true);
                MainPanel.instance.workingPanel.show(SpendPanel.instance);
            }
        });
    }
}

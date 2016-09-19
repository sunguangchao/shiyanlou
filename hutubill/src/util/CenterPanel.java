package util;

import gui.panel.WorkingPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 11981 on 2016/9/9.
 */
public class CenterPanel extends JPanel {
    private double rate;//拉伸比例
    private JComponent c;//显示的控件
    private boolean strech;//是否拉伸

    public CenterPanel(double rate, boolean strech) {//和下面的构造函数组成了多态
        this.setLayout(null);
        this.rate = rate;
        this.strech = strech;
    }

    public CenterPanel(double rate) {
        this(rate, true);
    }

    /*
    * 在repaint方法中，就会使用绝对定位的方式把组件放在中间位置。
    * 如果strech是true，就会根据整个容器的大小，设置组件的大小，达到拉伸的效果
    * 如果strech是false, 就使用组件的preferredSize，即非拉伸效果。
    */
    public void repaint() {

        if (null != c) {
            Dimension containerSize = this.getSize();
            Dimension componentSize = c.getPreferredSize();//getPreferedSize方法是获取组的首选大小

            if (strech)
                c.setSize((int) (containerSize.width * rate), (int) (containerSize.height * rate));
            else
                c.setSize(componentSize);
            /*
            * setSize:将此 Dimension 对象的大小设置为指定的宽度和高度（以双精度表示）。
            * 注意，如果 width 或 height 大于 Integer.MAX_VALUE，
            * 则将其重新设置为 Integer.MAX_VALUE。
            */

            c.setLocation(containerSize.width / 2 - c.getSize().width / 2,
                    containerSize.height / 2 - c.getSize().height / 2);

        }
        super.repaint();
    }

    /*
    * 使用show方法显示组件,show方法中的思路是：
    * 先把这个容器中的组件都移出，然后把新的组件加进来，并且调用updateUI进行界面渲染。
    * updateUI会导致swing去调用repaint()方法。
    */
    public void show(JComponent p) {
        this.c = p;
        Component[] cs = getComponents();
        for (Component c : cs) {
            remove(c);
        }
        add(p);

        if (p instanceof WorkingPanel)
            //instanceof 运算符是用来在运行时指出对象是否是特定类的一个实例。
            // instanceof通过返回一个布尔值来指出，这个对象是否是这个特定类或者是它的子类的一个实例。
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

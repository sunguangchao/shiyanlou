package util;

import java.awt.*;

/**
 * Created by 11981 on 2016/9/10.
 */
public class ColorUtil {
    public static Color blueColor = Color.decode("#3399FF");
    public static Color grayCColor = Color.decode("#999999");
    public static Color backgroundColor = Color.decode("#eeeeee");
    public static Color warningColor = Color.decode("#FF33333");

    public static Color getByPercentage(int per)//getByPercentage根据进度显示不同的颜色
    {
        if(per > 100)
            per = 100;
        int r = 51;
        int g = 255;
        int b = 51;
        float rate = per/100;
        r = (int) ((255-51)* rate + 51);
        g = 255 - r + 51;
        Color color = new Color(r,g,b);
        return color;
    }

}

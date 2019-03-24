package consistent_hashing;

/**
 * @Author: sunguangchao
 * @Date: 2019/3/11 9:31 AM
 */
public class StatisticsUtil {


    /**
     * 方差s^2=[(x1-x)^2 +...(xn-x)^2]/n
     * @param x
     * @return
     */
    public static double vairance(Long[] x){
        int m = x.length;
        double sum = 0;
        for (int i = 0; i < m; i++){
            sum += x[i];
        }
        //先求平均值
        double average = sum / m;
        double result = 0;
        for (int i = 0; i < m; i++){
            result += (x[i] - average) * (x[i] - average);
        }
        return result/m;
    }

    /**
     * 标准差σ=sqrt(s^2)
     * @param x
     * @return
     */
    public static double standardDeviation(Long[] x){
        int m = x.length;
        double sum = 0;
        for (int i = 0; i < m; i++){
            sum += x[i];
        }
        //先求平均值
        double average = sum / m;
        double result = 0;
        for (int i = 0; i < m; i++){
            result += (x[i] - average) * (x[i] - average);
        }
        return Math.sqrt(result/m);
    }


}

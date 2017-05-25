package shiyanlou;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by 11981 on 2017/5/25.
 * Java实现微信红包分配算法
 */
public class RedPacketUtil {
    private static final float MINMONEY = 0.01f;
    private static final float MAXMONEY = 200f;
    private static final double TIMES = 2.1;

    private List<Float> splitRedPacket(float money, int count){
        if (!isRight(money,count)){
            return null;
        }
        List<Float> list = new ArrayList<Float>();
        float max = (float)(money*TIMES/count);

        max = max > MAXMONEY?MAXMONEY:max;
        for(int i=0; i < count; i++){
            float one = randomRedPacket(money, MINMONEY, max, count-i);
            list.add(one);
            money -= one;
        }
        return list;
    }


    private float randomRedPacket(float money, float mins, float maxs, int count){
        if (count == 1)
            return (float)(Math.round(money*100))/100;

        if (mins == maxs){
            return mins;
        }
        float max = maxs>money?money:maxs;
        float one = ((float)Math.random()*(max-mins)+mins);
        one = (float)(Math.round(one*100))/100;
        float moneyOther = money - one;
        if (isRight(moneyOther, count-1)){
            return one;
        }else{
            float avg = moneyOther / (count-1);
            if (avg < MINMONEY){
                return randomRedPacket(money,mins,one,count);
            }else if(avg>MAXMONEY){
                return randomRedPacket(money,one,maxs,count);
            }
        }
        return one;
    }

    private boolean isRight(float money, int count){
        double avg = money/count;
        if (avg < MINMONEY)
            return false;
        else if (avg > MAXMONEY)
            return false;
        return true;
    }

    public static void main(String[] args){
//        RedPacketUtil util = new RedPacketUtil();
//        System.out.print(util.splitRedPacket(200, 100));
        System.out.println("please input a number");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt(), count = num, NUM = 20;
        float[] index = new float[NUM];
        while (num-->0)
        {
            RedPacketUtil util = new RedPacketUtil();
            List<Float> temp =  util.splitRedPacket(20, NUM);
            for (int i=0; i < temp.size(); i++)
            {
                index[i] += temp.get(i);
            }
        }
        for (int i=0; i<NUM; i++){
            System.out.println("the number " + i + " can get " + (index[i]/count));
        }

    }
}

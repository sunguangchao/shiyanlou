/**
 * Created by 11981 on 2016/9/24.
 */
public class StarbuzzCoffee {
    public static void main(String[] args)
    {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription()+" $"+beverage.cost());

        Beverage beverage2 = new HouseBlend();
        beverage2 = new Mocha(beverage2);//用Mocha装饰他
        beverage2 = new Mocha(beverage2);//用Mocha第二次装饰他
        System.out.println(beverage2.getDescription()+" $"+beverage2.cost());
    }
}

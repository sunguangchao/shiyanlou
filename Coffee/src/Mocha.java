/**
 * Created by 11981 on 2016/9/24.
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;//用一个实例变量记录，也就是被装饰者

    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }

    public String getDescription(){
        return beverage.getDescription() + ", Mocha";
    }
    public double cost(){
        return .20 + beverage.cost();
    }
}

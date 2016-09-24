/**
 * Created by 11981 on 2016/9/24.
 */
public class CurrentConditionDisplay implements Observer,Displayment {
    private float temperature;
    private float humidity;
    private Subject weatherData;

    public CurrentConditionDisplay(Subject weatherData){
        this.weatherData = weatherData;
        weatherData.registerObservers(this);
    }
    public void update (float temperature ,float humidity,float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }
    public void display(){
        System.out.println("Current conditions: "+ temperature+"F degree and "+humidity+"% humidity");
    }
}

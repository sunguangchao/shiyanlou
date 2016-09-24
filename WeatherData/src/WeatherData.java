import java.util.ArrayList;


/**
 * Created by 11981 on 2016/9/24.
 */

public class WeatherData implements Subject{
    private ArrayList observers;//使用一个数组来记录观察者
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData(){
        observers = new ArrayList();
    }
    public void registerObservers(Observer o)
    {
        observers.add(o);

    }
    public void removeObservers(Observer o)
    {
        int i = observers.indexOf(o);//返回第一次出现的指定子字符串在此字符串中的索引
        if(i > 0)
        {
            observers.remove(i);
        }
    }

    public void notifyObservers(){
        for(int i=0;i<observers.size();i++)
        {
            Observer observer = (Observer)observers.get(i);
            observer.update(temperature,humidity,pressure);
        }
    }

    public void measurementsChanged(){
        notifyObservers();
    }
    public void setMeasurements(float temperature,float humidity,float pressure)
    {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}

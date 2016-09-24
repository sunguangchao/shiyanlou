import java.util.ArrayList;


/**
 * Created by 11981 on 2016/9/24.
 */

public class WeatherData implements Subject{
    private ArrayList observers;//ʹ��һ����������¼�۲���
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
        int i = observers.indexOf(o);//���ص�һ�γ��ֵ�ָ�����ַ����ڴ��ַ����е�����
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

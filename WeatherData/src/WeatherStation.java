/**
 * Created by 11981 on 2016/9/24.
 */
public class WeatherStation {

    public static void main(String[] args)
    {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay currentDisplay = new CurrentConditionDisplay(weatherData);

        weatherData.setMeasurements(80,65,30.4f);
    }
}

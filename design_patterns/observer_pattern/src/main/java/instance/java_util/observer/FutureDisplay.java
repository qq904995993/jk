package instance.java_util.observer;

import instance.java_util.subject.WeatherData;

import java.util.Observable;
import java.util.Observer;

public class FutureDisplay implements Observer {

    private float temperature;
    private float humidity;
    private float pressure;

    public FutureDisplay(Observable observable) {
        observable.addObserver(this);
    }

    public void update(Observable o, Object arg) {
        WeatherData weatherData = (WeatherData) o;
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
        this.pressure = weatherData.getPressure();
        System.out.println("天气预报刷新！");
    }
}

package instance.weather_station.observer;

import instance.weather_station.subject.Subject;

public class FutureDisplay implements Observer {

    private float temperature;
    private float humidity;
    private float pressure;

    public FutureDisplay(Subject weatherDate) {
        weatherDate.registerObserver(this);
    }

    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        System.out.println("天气预报刷新！");
    }

}

package instance.weather_station.observer;

import instance.weather_station.subject.Subject;

public class CurrentConditionsDisplay implements Observer {

    private float temperature;
    private float humidity;
    private float pressure;

    public CurrentConditionsDisplay(Subject weatherDate) {
        weatherDate.registerObserver(this);
    }

    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        System.out.println("当前天气状况刷新！");
    }

}

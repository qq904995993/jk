package instance.weather_station.subject;

import instance.weather_station.observer.CurrentConditionsDisplay;
import instance.weather_station.observer.FutureDisplay;
import instance.weather_station.observer.Observer;
import instance.weather_station.observer.StatisticsDisplay;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {

    private List<Observer> observers = new ArrayList<Observer>();

    private float temperature;
    private float humidity;
    private float pressure;

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for(Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }


    //天气数据修改
    public void setMeasureMents(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        Observer current = new CurrentConditionsDisplay(weatherData);
        Observer statistics = new StatisticsDisplay(weatherData);
        Observer future = new FutureDisplay(weatherData);
        weatherData.setMeasureMents(1f, 0.14f, 0.2f);
    }

}

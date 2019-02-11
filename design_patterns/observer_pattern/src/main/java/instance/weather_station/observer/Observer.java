package instance.weather_station.observer;


/**
 * 订阅者，这里是气象布告板
 */
public interface Observer {

    void update(float temperature, float humidity, float pressure);

}

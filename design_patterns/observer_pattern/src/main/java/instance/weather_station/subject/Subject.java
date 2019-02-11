package instance.weather_station.subject;

import instance.weather_station.observer.Observer;

/**
 * 主题
 */
public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();

}

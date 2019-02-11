package instance;

public class StaticSingleton {

    private static StaticSingleton staticSingleton = new StaticSingleton();

    private StaticSingleton() {}

    public static StaticSingleton getInstance() {
        return staticSingleton;
    }

}

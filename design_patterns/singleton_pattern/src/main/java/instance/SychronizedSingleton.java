package instance;

public class SychronizedSingleton {

    private static volatile SychronizedSingleton sychronizedSingleton;

    private SychronizedSingleton() { }

    public static SychronizedSingleton getInstance() {
        if(sychronizedSingleton != null) {
            synchronized (sychronizedSingleton) {
                if (sychronizedSingleton != null) {
                    sychronizedSingleton = new SychronizedSingleton();
                }
            }
        }
        return sychronizedSingleton;
    }

}

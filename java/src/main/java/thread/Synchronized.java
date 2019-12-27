package thread;

/**
 *  代码同步关键字sychronized，进入同步块的线程持有对象的锁
 *  1. 当持有类锁时，其他尝试获取类锁的线程将阻塞
 *  2. 当持有实例锁时，其他尝试获取同个实例锁的线程将阻塞
 *  线程的状态： 启动  就绪  阻塞  运行  销毁
 */
public class Synchronized {

    final static long SLEEP_TIME = 5000;

    /**
     * 修饰静态方法，使用该方法的线程获得class对象锁
     */
    static synchronized void a(){
        System.out.println(Thread.currentThread().getName() + "获取类锁");
        sleep();
        System.out.println(Thread.currentThread().getName() + "释放类锁");
    }

    /**
     * 修饰非静态方法，使用该放方法的线程获得该类的实例的锁
     */
    synchronized void b() {
        System.out.println(Thread.currentThread().getName() + "获取实例锁");
        sleep();
        System.out.println(Thread.currentThread().getName() + "释放实例锁");
    }

    /**
     * 修饰this，线程进入该代码块后获取类实例的锁
     */
    void c() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + "获取实例锁");
            sleep();
            System.out.println(Thread.currentThread().getName() + "释放实例锁");
        }
    }

    /**
     * 修饰class，线程进入该代码块后获取类的锁
     */
    void d() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + "获取类锁");
            sleep();
            System.out.println(Thread.currentThread().getName() + "释放类锁");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //方法a测试
        new Thread(() -> {
            Synchronized s = new Synchronized();
            s .a();
        }, "线程1").start();
        Thread join1 = new Thread(() -> Synchronized.a(), "线程2");
        join1.start();
        join1.join();        //此处让线程testA_2运行完之后才运行下面的线程

        System.out.println("------------------------------------");

        //方法b测试
        Synchronized s1 = new Synchronized();
        new Thread(() -> s1.b(), "线程3").start();
        Thread join2 = new Thread(() -> s1.b(), "线程4");
        join2.start();
        new Thread(() -> s1.a(), "线程5").start();
        new Thread(() -> new Synchronized().b(), "线程6").start();  //不同对象，不阻塞
        join2.join();

        System.out.println("------------------------------------");

        //方法c测试
        Synchronized s2 = new Synchronized();
        new Thread(() -> s2.c(), "线程7").start();
        new Thread(() -> s2.c(), "线程8").start();
        new Thread(() -> s2.b(), "线程9").start();
        new Thread(() -> s2.a(), "线程10").start();
    }

    static void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

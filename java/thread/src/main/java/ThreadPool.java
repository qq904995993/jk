import java.util.concurrent.*;

/**
 *  线程池
 */
public class ThreadPool {
    public static void main(String[] args) {
        int corePoolSize = 5;                   //核心线程数量
        int maximunPoolSize = 20;               //最大线程数量
        long keepAliveTime = 1;                 //非核心线程的存活时间
        TimeUnit timeUnit = TimeUnit.MINUTES;   //非核心线程存活时间单位

        BlockingQueue<Runnable> blockingQueue1 = new LinkedBlockingQueue<>(512);  //基于链表，存取使用不同锁，效率较高
        BlockingQueue<Runnable> blockingQueue2 = new ArrayBlockingQueue<>(512);   //基于数组，存取使用相同锁，遍历效率高
        BlockingQueue<Runnable> blockingQueue3 = new SynchronousQueue<>();                 //只存储单个元素

        ThreadFactory threadFactory = Executors.defaultThreadFactory();     //线程创建工厂

        RejectedExecutionHandler rejectedExecutionHandler1 = new ThreadPoolExecutor.AbortPolicy();          //丢弃任务并抛出RejectedExecutionException异常。
        RejectedExecutionHandler rejectedExecutionHandler2 = new ThreadPoolExecutor.DiscardPolicy();        //丢弃任务，但是不抛出异常。
        RejectedExecutionHandler rejectedExecutionHandler3 = new ThreadPoolExecutor.DiscardOldestPolicy();  //丢弃队列最前面的任务，然后重新尝试执行任务
        RejectedExecutionHandler rejectedExecutionHandler4 = new ThreadPoolExecutor.CallerRunsPolicy();     //由调用线程处理该任务

        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5, 20, 1, TimeUnit.MINUTES,
                        blockingQueue1, threadFactory, rejectedExecutionHandler4);

        //future 线程处理对象，可用于取消线程、获取线程返回值
        Future future1 = threadPoolExecutor.submit(() -> {
            //runnable无返回值，需要进行异常捕获
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Future future2 = threadPoolExecutor.submit(() -> {
            //callable有返回值且默认抛出异常
            Thread.sleep(10000);
            return "ok";
        });


        //获取指定线程返回值，该方法会使得当前线程阻塞等待指定线程返回结果
        try {
            System.out.println(future1.get());      //runnable无返回值，结果为null
            System.out.println(future2.get());
        } catch (Exception e) {

        }

        threadPoolExecutor.shutdown();      //等待所有任务执行完后停止线程池

        threadPoolExecutor.shutdownNow();   //尝试停止运行中的线程后停止线程池

        ExecutorService executorService1 = Executors.newSingleThreadExecutor();         //单线程池
        ExecutorService executorService2 = Executors.newFixedThreadPool(5);   //固定线程池
        ExecutorService executorService3 = Executors.newCachedThreadPool();             //缓存线程池
        ExecutorService executorService4 = Executors.newScheduledThreadPool(5);  //周期调度线程池
    }
}

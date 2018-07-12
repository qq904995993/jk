package test;

/**
 *	synchronized 修饰静态方法时，对整个calss对象进行加锁
 */
public class SynchronizedTest2 implements Runnable{
	static int i=0;

    public static synchronized void increase(){
        i++;
    }

    /**
     * 非静态,锁定的是实例化后的对象，访问时锁不一样不会发生互斥
     */
    public synchronized void increase4Obj(){
        i++;
    }

    @Override
    public void run() {
        for(int j=0;j<1000000;j++){
            increase();
        }
    }
    
    /**
     * ti线程start时，SynchronizedTeat2.class加锁，t2进入等待，t1执行完毕时释放class锁，t2执行
     */
    public static void main(String[] args) throws InterruptedException {
        //new新实例
        Thread t1=new Thread(new SynchronizedTest2());
        //new新实例
        Thread t2=new Thread(new SynchronizedTest2());
        //启动线程
        t1.start();t2.start();

        t1.join();t2.join();
        System.out.println(i);
    }
}

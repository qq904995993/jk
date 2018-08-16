package test;

/**
 *	synchronized 修饰实例方法时，对其实例对象进行加锁
 */
public class SynchronizedTest1 implements Runnable{
	//共享资源(临界资源)
	static int i=0;

	/**
	 * synchronized 修饰实例方法
	 */
	public synchronized void increase(){
		i++;
	}
	    
	@Override
	public void run() {
		for(int j=0;j<1000000;j++){
	    	increase();
	    }
	}
	
	/**
	 * instance 加锁，先执行玩线程t1时instance加锁，线程t2进入等待状态，执行完成后释放instance锁，执行线程t2
	 */
	public static void main(String[] args) throws InterruptedException {
		SynchronizedTest1 instance = new SynchronizedTest1();
	    Thread t1 = new Thread(instance);
	    Thread t2 = new Thread(instance);
	    t1.start();
	    t2.start();
	    t1.join();
	    t2.join();
	    System.out.println(i);
	}
}

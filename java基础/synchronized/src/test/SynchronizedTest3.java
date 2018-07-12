package test;

public class SynchronizedTest3 implements Runnable{
	
	static SynchronizedTest3 instance = new SynchronizedTest3();
    
	static int i=0;
    
    @Override
    public void run() {
    	/**
    	 * ����instanceʵ������
    	 */
        synchronized(instance){
            for(int j=0;j<1000000;j++){
                    i++;
              }
        }
    }
    
    /**
     * ����SynchronizedTest3��ʵ������
    @Override
    public void run() {
        synchronized(this){
            for(int j=0;j<1000000;j++){
                    i++;
              }
        }
    }
	*/
    
    /**
	 * ����SynchronizedTest3.class����
    @Override
    public void run() {
        synchronized(SynchronizedTest3.class){
            for(int j=0;j<1000000;j++){
                    i++;
              }
        }
    }
	*/
    
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}

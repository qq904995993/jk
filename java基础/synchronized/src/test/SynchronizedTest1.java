package test;

/**
 *	synchronized ����ʵ������ʱ������ʵ��������м���
 */
public class SynchronizedTest1 implements Runnable{
	//������Դ(�ٽ���Դ)
	static int i=0;

	/**
	 * synchronized ����ʵ������
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
	 * instance ��������ִ�����߳�t1ʱinstance�������߳�t2����ȴ�״̬��ִ����ɺ��ͷ�instance����ִ���߳�t2
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

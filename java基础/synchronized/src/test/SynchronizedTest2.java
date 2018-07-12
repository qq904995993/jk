package test;

/**
 *	synchronized ���ξ�̬����ʱ��������calss������м���
 */
public class SynchronizedTest2 implements Runnable{
	static int i=0;

    public static synchronized void increase(){
        i++;
    }

    /**
     * �Ǿ�̬,��������ʵ������Ķ��󣬷���ʱ����һ�����ᷢ������
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
     * ti�߳�startʱ��SynchronizedTeat2.class������t2����ȴ���t1ִ�����ʱ�ͷ�class����t2ִ��
     */
    public static void main(String[] args) throws InterruptedException {
        //new��ʵ��
        Thread t1=new Thread(new SynchronizedTest2());
        //new��ʵ��
        Thread t2=new Thread(new SynchronizedTest2());
        //�����߳�
        t1.start();t2.start();

        t1.join();t2.join();
        System.out.println(i);
    }
}

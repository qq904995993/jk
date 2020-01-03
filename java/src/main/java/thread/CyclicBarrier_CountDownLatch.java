package thread;

import java.util.concurrent.*;

/**
 *  CyclicBarrier、CountDownLatch都能使多个线程到达某个点之后再一起执行
 *  CyclicBarrier，等待await调用数到达指定数量值后会重新开始计算，因此可以重复使用
 *  CountDownLatch调用countdown数到达指定数量值后不会重新开始计算，后续的await都是通过的
 */
public class CyclicBarrier_CountDownLatch {
    public static void main(String[] args) {
        //第二个参数的runnable，由await调用数到达指定数量值的那个线程调用
        CyclicBarrier cyclicBarrier =
                new CyclicBarrier(3, () -> System.out.println(Thread.currentThread().getName()+ ",all is await"));

        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 0; i < 4; i ++) {
            //new Thread(new CyclicBarrierThread(cyclicBarrier), "CyclicBarrier" + i).start();
            new Thread(new CountDownLatchThread(countDownLatch), "CountDownLatch" + i).start();
        }

    }

    private static class CyclicBarrierThread implements Runnable {
        CyclicBarrier cyclicBarrier;

        public CyclicBarrierThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ", begin CyclicBarrier");
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + ", await");
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", end CyclicBarrier");
        }
    }

    private static class CountDownLatchThread implements Runnable {
        private CountDownLatch countDownLatch;

        public CountDownLatchThread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ", begin CountDownLatch");
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + ", countDown");
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + ", await");
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", end CountDownLatch");
        }
    }
}

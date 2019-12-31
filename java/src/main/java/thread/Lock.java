package thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Lock锁
 * 与sychronized同样为可重入锁，线程可在执行过程中多次获取锁而不会出现死锁。
 * 如可以判断锁状态、获取锁过程可中断、按等待获取锁的线程的等待时间进行获取
 * 还提供了了读写锁
 */
public class Lock {

    java.util.concurrent.locks.Lock reentrantLock = new ReentrantLock();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 可重入锁
     * 内置计数器，加锁方法将计数器加一，解锁方法将计数器减一
     * 线程第一次加锁时计数器必须为0才能加锁成功，此后不管计数器是否为0，该线程都可重复加锁（可重入）
     */
    private void reentrant() {
        while(!reentrantLock.tryLock()) {
        }
        try {
            System.out.println("reentrantLock  first lock");
            reentrantLock.lock();   //重入，不会出现死锁
            System.out.println("reentrantLock second lock");
            Synchronized.sleep();
        } finally {
            System.out.println("reentrantLock  first unlock");
            reentrantLock.unlock();
            System.out.println("reentrantLock second unlock");
            reentrantLock.unlock();     //获取了两次锁，所以需要解锁两次其他线程才能锁定
        }
    }

    /**
     * 读锁
     * 多个线程读锁操作可以并发进行
     * 当线程进行读锁时，其他线程的写锁操作将进入等待
     */
    void read() {
        while(!readWriteLock.readLock().tryLock()) {
        }
        try {
            System.out.println("readLock lock");
            Synchronized.sleep();
        } finally {
            System.out.println("readLock unlock");
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * 写锁
     * 当线程写锁后，其他线程无法进行读锁及写锁
     */
    void white() {
        while(!readWriteLock.writeLock().tryLock()) {
        }
        try {
            System.out.println("whiteLock lock");
            Synchronized.sleep();
        } finally {
            System.out.println("whiteLock unlock");
            readWriteLock.writeLock().unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Lock lock = new Lock();

        System.out.println("测试可重入锁");
        new Thread(() -> lock.reentrant()).start();
        Thread thread = new Thread(() -> lock.reentrant());
        thread.start();
        thread.join();
        System.out.println("-------------------------------------------------------------");

        System.out.println("测试读锁");
        new Thread(() -> lock.read()).start();
        thread = new Thread(() -> lock.white());
        thread.start();
        new Thread(() -> lock.read()).start();
        thread.join();
        System.out.println("-------------------------------------------------------------");

        System.out.println("测试写锁");
        new Thread(() -> lock.white()).start();
        new Thread(() -> lock.read()).start();
        new Thread(() -> lock.white()).start();
    }

}

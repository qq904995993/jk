package network.io;

/**
 * TCP是面向连接的、可靠的、基于字节流的传输层通信协议。
 * Socket实现了TCP协议的网络通信。
 * 该例子为BIO（同步阻塞IO），每当有新的连接都需要新开一个线程处理I/O操作。
 * 此处使用固定大小线程池进行线程执行，虽然防止了大量线程创建，但是当并发量高的时候，部分连接的I/O操作将阻塞。
 * 且连接后等待I/O操作的过程也会造成线程阻塞加重
 * @see network.protocol.TCP
 * @see network.protocol.UDP
 */

public class BIO {
}



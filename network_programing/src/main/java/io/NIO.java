package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *  New IO，Non-block IO
 *  同步非阻塞IO，通过一个线程去执行selector扫描就绪的I/O进而进行I/O操作
 *  无需每个连接都分配线程去操作，在某些连接I/O操作等待过重中可以执行已经就绪的I/O操作，节省时间
 *  同步的Reactor是指程序发出读请求后，由分离器监听到可以进行读操作时（需要获得读操作条件）通知事件处理器进行读操作
 *
 *  注：JDK的NIO底层由epoll实现，该实现饱受诟病的空轮训bug会导致cpu飙升100%
 *  注：JDK的NIO编程需要了解很多的概念，编程复杂，对NIO入门非常不友好，编程模型不友好，ByteBuffer的api简直反人类
 *  Netty封装了JDK的NIO，让你用得更爽
 *  @see  netty.Netty
 */
public abstract class NIO {
    final int PORT = 8080;
    final String HOST = "localhost";
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    Selector selector;

    public void reactor() throws IOException {
        selector.select(1000);
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();
            if (key.isValid()) {
                if (key.isAcceptable()) {
                    accept(key);
                }
                if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    abstract void accept(SelectionKey key) throws IOException;

    abstract void read(SelectionKey key) throws IOException;

}

class NIOServer extends NIO {

    public NIOServer() throws IOException{
        selector = selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动...");
    }


    @Override
    void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    @Override
    void read(SelectionKey key) throws IOException {
        try {
            byteBuffer.clear();
            SocketChannel socketChannel = (SocketChannel) key.channel();
            int length = socketChannel.read(byteBuffer);
            if (length > 0) {
                byteBuffer.flip();
                String content = new String(byteBuffer.array(), 0, length);
                System.out.println("收到信息: " + content);
                socketChannel.write(byteBuffer);
            }
            socketChannel.register(selector, SelectionKey.OP_WRITE);
        } finally {
            key.channel().close();
            key.cancel();
        }
    }


    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        //如果服务器要做其他事情，可以单独开一个线程去执行reactor
        while(true) {
            server.reactor();
        }
    }

}


class NIOClient extends NIO {

    private SocketChannel socketChannel;

    public NIOClient() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress(HOST, PORT));
        System.out.println("客户端启动...");
        if(socketChannel.isConnectionPending()) {
            socketChannel.finishConnect();
            socketChannel.register(selector, SelectionKey.OP_WRITE);
        }
    }

    @Override
    void accept(SelectionKey key) throws IOException {
        throw new UnsupportedOperationException("不支持的操作！");
    }

    @Override
    void read(SelectionKey key) throws IOException {
        try {
            byteBuffer.clear();
            SocketChannel socketChannel = (SocketChannel) key.channel();
            int length = socketChannel.read(byteBuffer);
            if (length > 0) {
                byteBuffer.flip();
                String content = new String(byteBuffer.array(), 0, length);
                System.out.println("收到回声: " + content);
            }
        } finally {
            key.channel().close();
            key.cancel();
        }
    }

    void write(String content) throws Exception {
        while(!socketChannel.isConnected()) {
            Thread.sleep(1000);
            System.out.println("等待连接");
        }
        byteBuffer.clear();
        byteBuffer.put(content.getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        System.out.println("发送信息：" + content);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    public static void main(String[] args) throws Exception {
        NIOClient client = new NIOClient();
        client.write("日您吗");
        client.reactor();
    }
}



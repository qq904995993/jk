package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client extends AbstractNio {

    private SocketChannel socketChannel;

    public Client(String host, int port) throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress(host, port));
        System.out.println("客户端启动...");
        if (socketChannel.isConnectionPending()) {
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
        while (!socketChannel.isConnected()) {
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
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            pool.execute(() -> {
                try {
                    Client client = new Client("localhost", 8080);
                    client.write("日您吗");
                    client.reactor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        }
    }

}

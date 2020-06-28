package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server extends AbstractNio {

    public Server(int port) throws IOException {
        selector = selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
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
        Server server = new Server(8080);
        //如果服务器要做其他事情，可以单独开一个线程去执行reactor
        while (true) {
            server.reactor();
        }
    }

}

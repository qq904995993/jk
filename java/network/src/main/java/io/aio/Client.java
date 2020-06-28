package io.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class Client {

    public Client(String content, String host, int port) throws Exception {
        AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel.open();
        asynchronousSocketChannel.connect(new InetSocketAddress(host, port), asynchronousSocketChannel,
                new Client.ConnectCompleteHandler(content));
        System.out.println("客户端启动...");
        CountDownLatch running = new CountDownLatch(1);
        running.await();
    }

    private static class ConnectCompleteHandler implements CompletionHandler<Void, AsynchronousSocketChannel> {
        private String content;

        public ConnectCompleteHandler(String content) {
            this.content = content;
        }

        @Override
        public void completed(Void result, AsynchronousSocketChannel attachment) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(content.getBytes());
            byteBuffer.flip();
            attachment.write(byteBuffer, attachment, new Client.WriteCompleteHandler(content));
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
            System.out.println("连接失败...");
        }
    }

    private static class WriteCompleteHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {
        private String content;

        public WriteCompleteHandler(String content) {
            this.content = content;
        }

        @Override
        public void completed(Integer result, AsynchronousSocketChannel attachment) {
            System.out.println("发送信息：" + content);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            attachment.read(byteBuffer, attachment, new Client.ReadCompleteHandler(byteBuffer));
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
            System.out.println("数据发送失败...");
        }
    }

    private static class ReadCompleteHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {
        private ByteBuffer byteBuffer;

        public ReadCompleteHandler(ByteBuffer byteBuffer) {
            this.byteBuffer = byteBuffer;
        }

        @Override
        public void completed(Integer result, AsynchronousSocketChannel attachment) {
            String content = new String(byteBuffer.array(), 0, result);
            System.out.println("收到回声:" + content);
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
            System.out.println("读取数据失败...");
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client("麻麻屁", "localhost", 8080);
    }

}

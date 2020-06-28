package io.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class Server {

    public Server(int port) throws Exception {
        AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
        asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
        asynchronousServerSocketChannel.accept(null, new Server.AcceptCompleteHandler());  //proactor
        System.out.println("服务器启动...");

        //等待线程执行完毕再结束
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();
    }

    private static class AcceptCompleteHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {
        @Override
        public void completed(AsynchronousSocketChannel result, Object attachment) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            result.read(byteBuffer, result, new Server.ReadCompleteHandler(byteBuffer));
        }

        @Override
        public void failed(Throwable exc, Object attachment) {
            System.out.println("接收失败...");
            exc.printStackTrace();
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
            System.out.println("收到信息：" + content);
            byteBuffer.flip();
            attachment.write(byteBuffer, attachment, new Server.WriteCompleteHandler(content));
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
            System.out.println("读取信息失败...");
        }
    }

    private static class WriteCompleteHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {
        private String content;

        public WriteCompleteHandler(String content) {
            this.content = content;
        }

        @Override
        public void completed(Integer result, AsynchronousSocketChannel attachment) {
            System.out.println("发送回声：" + content);
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
            System.out.println("数据写入失败...");
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
    }

}

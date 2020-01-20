package network.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * AIO 异步非阻塞I/O。
 * 异步的Proactor是指程序发出读请求后，操作系统立刻异步地进行读操作了，读完之后在通知分离器，分离器激活处理器直接取用已读到的数据。
 */
public class AIO {
    final int PORT = 8080;
    final String HOST = "localhost";
    ByteBuffer byteBuffer;
}


class AIOServer extends AIO {

    public AIOServer() throws Exception {
        AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
        asynchronousServerSocketChannel.bind(new InetSocketAddress(PORT));
        asynchronousServerSocketChannel.accept(null, new AcceptCompleteHandler());  //proactor
        System.out.println("服务器启动...");

        //等待线程执行完毕再结束
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();
    }

    private static class AcceptCompleteHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {
        @Override
        public void completed(AsynchronousSocketChannel result, Object attachment) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            result.read(byteBuffer, result, new ReadCompleteHandler(byteBuffer));
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
            attachment.write(byteBuffer, attachment, new WriteCompleteHandler(content));
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
        AIOServer server = new AIOServer();
    }

}


class AIOClient extends AIO{

    public AIOClient(String content) throws Exception {
        AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel.open();
        asynchronousSocketChannel.connect(new InetSocketAddress(HOST, PORT), asynchronousSocketChannel, new ConnectCompleteHandler(content));
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
            attachment.write(byteBuffer, attachment, new WriteCompleteHandler(content));
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
            attachment.read(byteBuffer, attachment, new ReadCompleteHandler(byteBuffer));
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
        AIOClient client = new AIOClient("麻麻屁");
    }

}

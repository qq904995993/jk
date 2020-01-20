package network.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TCP是面向连接的、可靠的、基于字节流的传输层通信协议。
 * Socket实现了TCP协议的网络通信。
 * 该例子为BIO（同步阻塞IO），每当有新的连接都需要新开一个线程处理I/O操作。
 * 此处使用固定大小线程池进行线程执行，虽然防止了大量线程创建，但是当并发量高的时候，部分连接的I/O操作将阻塞。
 */

public interface TCP{

}

class Server implements TCP {
    public static void main(String[] args) throws IOException {
        java.net.ServerSocket serverSocket = null;
        try{
            serverSocket = new java.net.ServerSocket(8080);
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            while(true) {
                java.net.Socket socket = serverSocket.accept();                 //未获取到连接时，线程阻塞
                executorService.execute(new Thread(new SocketThread(socket)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(serverSocket != null) {
                serverSocket.close();
            }
        }
    }

    static class SocketThread implements Runnable {
        private java.net.Socket socket;
        public SocketThread(java.net.Socket socket) {
            this.socket = socket;
        }

        public void run() {
            InputStream is = null;
            OutputStream os = null;
            try {
                is = socket.getInputStream();
                byte[] bytes = new byte[1024];
                is.read(bytes);                         //未获取到数据时，线程阻塞
                System.out.println("服务器接收到新消息：" + new String(bytes));
                os = socket.getOutputStream();
                os.write(bytes);
                socket.shutdownOutput();

                is.close();
                os.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Client implements TCP {
    public static void main(String[] args) throws Exception {
        java.net.Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        try{
            socket = new java.net.Socket("127.0.0.1", 8080);
            os = socket.getOutputStream();
            os.write("日您吗".getBytes());
            is = socket.getInputStream();
            byte[] bytes = new byte[1024];
            is.read(bytes);                             //未获取到返回数据时，线程阻塞
            System.out.println("接收到服务器的回声：" + new String(bytes));
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
    }
}


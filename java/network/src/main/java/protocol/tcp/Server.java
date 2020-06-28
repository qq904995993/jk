package protocol.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) throws IOException {
        java.net.ServerSocket serverSocket = null;
        try {
            serverSocket = new java.net.ServerSocket(8080);
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            while (true) {
                java.net.Socket socket = serverSocket.accept();                 //未获取到连接时，线程阻塞
                executorService.execute(new Thread(new Server.SocketThread(socket)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
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

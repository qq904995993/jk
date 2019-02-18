package base;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * UDP是一种无连接、不可靠的传输层协议
 */
public class UDP {

    static class Server {
        public static void main(String[] args) {
            DatagramSocket datagramSocket = null;
            try{
                datagramSocket = new DatagramSocket(8080);
                DatagramPacket receivedDatagramPacket = new DatagramPacket(new byte[1024], 1024);
                ExecutorService executorService = Executors.newCachedThreadPool();
                while(true) {
                    datagramSocket.receive(receivedDatagramPacket);
                    executorService.execute(new Thread(new SocketThread(datagramSocket, receivedDatagramPacket)));
                }
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                if(datagramSocket != null) {
                    datagramSocket.close();
                }
            }
        }
        static class SocketThread implements Runnable {
            private DatagramSocket datagramSocket;
            private DatagramPacket receivedDatagramPacket;
            public SocketThread(DatagramSocket datagramSocket, DatagramPacket receivedDatagramPacket) {
                this.datagramSocket = datagramSocket;
                this.receivedDatagramPacket = receivedDatagramPacket;
            }

            public void run() {
                try {
                    System.out.println("服务器接收到新消息：" + new String(receivedDatagramPacket.getData()));

                    DatagramPacket sendDatagramPacket = new DatagramPacket(receivedDatagramPacket.getData(),
                            receivedDatagramPacket.getData().length,
                            receivedDatagramPacket.getAddress(), receivedDatagramPacket.getPort());
                    datagramSocket.send(sendDatagramPacket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Client {
        public static void main(String[] args) {
            DatagramSocket datagramSocket = null;
            try {
                datagramSocket = new DatagramSocket();
                InetAddress inetAddress = InetAddress.getByName("localhost");
                DatagramPacket sendDatagramPacket = new DatagramPacket("曹尼玛".getBytes(), "曹尼玛".getBytes().length,
                        inetAddress, 8080);
                datagramSocket.send(sendDatagramPacket);
                System.out.println("数据发送！");

                DatagramPacket receivedDatagramPacket = new DatagramPacket(new byte[1024], 1024);
                datagramSocket.receive(receivedDatagramPacket);
                System.out.println("接收到服务器的回声：" + new String(receivedDatagramPacket.getData()));
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                if(datagramSocket != null) {
                    datagramSocket.close();
                }
            }

        }
    }

}

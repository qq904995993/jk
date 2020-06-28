package protocol.udp;

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
                ExecutorService executorService = Executors.newFixedThreadPool(10);
                while(true) {
                    //每次接受数据要使用新的对象去接受
                    DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);
                    datagramSocket.receive(datagramPacket);
                    executorService.execute(new Thread(new SocketThread(datagramSocket, datagramPacket)));
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
                    byte[] content = receivedDatagramPacket.getData();
                    System.out.println("服务器接收到新消息：" + new String(content));

                    DatagramPacket datagramPacket = new DatagramPacket(content, content.length,
                            receivedDatagramPacket.getAddress(), receivedDatagramPacket.getPort());
                    datagramSocket.send(datagramPacket);
                    System.out.println("发出回声");
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
                InetAddress inetAddress = InetAddress.getLocalHost();
                DatagramPacket datagramPacket = new DatagramPacket("曹尼玛".getBytes(), "曹尼玛".getBytes().length,
                        inetAddress, 8080);
                datagramSocket.send(datagramPacket);
                System.out.println("数据发送！");

                datagramPacket = new DatagramPacket(new byte[1024], 1024);
                datagramSocket.receive(datagramPacket);
                System.out.println("接收到服务器的回声：" + new String(datagramPacket.getData()));
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

package protocol.tcp;

import java.io.InputStream;
import java.io.OutputStream;

public class Client {

    public static void main(String[] args) throws Exception {
        java.net.Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        try {
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

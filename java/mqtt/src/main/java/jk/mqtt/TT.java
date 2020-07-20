package jk.mqtt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TT {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 10; i ++) {
            executorService.execute(new TTT());
        }
        while(executorService.isTerminated()) {
            return;
        }
    }

    static class TTT implements Runnable {
        public void run() {
            try {
                File file = new File("F:\\tt.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                fileOutputStream.write("测试1".getBytes());
                System.out.println("ok");
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}

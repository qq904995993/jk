package jk.mqtt;

import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileJson {

    public static void main(String[] args) throws IOException {
        File file = new File("F:\\图片\\96a044310a55b31922d607214ea98226cffc171e.jpg");
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        System.out.println(new BASE64Encoder().encode(buffer).length());
    }

}

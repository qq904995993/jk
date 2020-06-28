package base;

import javax.swing.text.html.StyleSheet;

public class BaseType {
    public static void main(String[] args) {
        //数据范围小于int的数据类型（byte、char、short）的运算都会强制转换为int类型
        byte byte1 = 1, byte2 = 2;
        char char1 = 1, char2 = 2;
        short short1 = 1, short2 = 2;
        int i = byte1 + byte2;
        i = char1 + char2;
        i = short1 + short2;
        i = byte1 + char1;
        i = char1 + short1;
        i = byte1 + short1;

        //低精度类型与高精度类型运算会自动转换为高精度类型，精度排序(double>float>long>int)
        i = 1;
        float f = 1.0f;
        double e = 1.00;
        long l = 1L;
        f = i + f;
        e = f + e;
        e = l + e;
        f = l + f;
        e = f + f;

        //低精度向高精度转换不需要强制转换且数据不会有损失，高精度向低精度转换需要强制转换且数据有可能会有损失
        e = f;
        f = l;
        l = i;
    }
}

package netty;

import io.netty.buffer.ByteBuf;

public class NettyUtil {

    static String byteBuf2String(ByteBuf byteBuf) {
        ByteBuf byteBuf1 = byteBuf.copy();
        byte[] bytes = new byte[byteBuf1.readableBytes()];
        byteBuf1.readBytes(bytes);
        return new String(bytes);
    }

}

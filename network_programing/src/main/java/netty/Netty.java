package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public interface Netty {
    int PORT = 8080;
    String HOST = "localhost";

    default String byteBuf2String(ByteBuf byteBuf) {
        ByteBuf byteBuf1 = byteBuf.copy();
        byte[] bytes = new byte[byteBuf1.readableBytes()];
        byteBuf1.readBytes(bytes);
        return new String(bytes);
    }
}

class Server implements Netty {

    public Server() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boos = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            serverBootstrap.group(boos, worker).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            nioSocketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            //异步的绑定服务器；调用sync()方法阻塞等待直到绑定完成
            ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();
            System.out.println("服务器启动...");
            //阻塞直到通道关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            boos.shutdownGracefully().sync();
            worker.shutdownGracefully().sync();
        }
    }

    private class EchoServerHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf byteBuf = (ByteBuf) msg;
            String content = byteBuf2String(byteBuf);
            System.out.println("收到信息：" + content);
            ctx.write(msg);
            System.out.println("发送回声：" + content);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.out.println("读取数据时异常...");
            cause.printStackTrace();
            ctx.close();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Server();
    }
}


class Client implements Netty {
    public Client(String content) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(HOST, PORT))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect().sync();
            System.out.println("客户端启动...");
            ByteBuf byteBuf = Unpooled.buffer(1027);
            byteBuf.writeBytes(content.getBytes());
            channelFuture.channel().writeAndFlush(byteBuf).sync();
            System.out.println("发送数据：" + content);
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    private class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
            System.out.println("收到回声：" + byteBuf2String(byteBuf));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.out.println("读取数据时出现异常...");
            cause.printStackTrace();
            ctx.close();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Client("麻麻屁");
    }
}


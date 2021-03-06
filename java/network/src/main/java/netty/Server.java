package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Server {

    public Server(int port) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boos = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            serverBootstrap.group(boos, worker).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            nioSocketChannel.pipeline().addLast(new Server.EchoServerHandler());
                        }
                    });
            //异步的绑定服务器；调用sync()方法阻塞等待直到绑定完成
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
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
            String content = NettyUtil.byteBuf2String(byteBuf);
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
        new Server(8080);
    }

}

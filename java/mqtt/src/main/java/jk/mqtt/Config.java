package jk.mqtt;

public class Config {

    //broker
    //public final static String BROKER = "tcp://mq.cmitmp.com:2883";
    public final static String BROKER = "tcp://192.168.9.101:1883";


    //服务器用户名
    public final static String USERNAME = "admin";

    //服务器密码
    public final static String PASSWORD = "public";

    //发布者id
    public final static String PUBLISHER_CLIENT_ID = "publisher";

    //订阅者id
    public final static String SUBSCRIBER_CLIENT_ID = "subscriber";

    //主题
    public final static String TOPIC = "jk";

    //质量等级,最多发送一次（可能丢失）
    public final static int QOS_LOW = 0;

    //质量等级,最少发送一次（可能重复）
    public final static int QOS_MIDDLE = 1;

    //质量等级,只发送一次（效率差）
    public final static int QOS_HIGH = 2;

}

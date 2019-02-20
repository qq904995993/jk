package jk.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class CallBack implements MqttCallback {

    //与服务端连接断开时客户端的回调方法
    public void connectionLost(Throwable throwable) {
        System.out.println("连接断开!");
    }

    //消息到达后订阅端的回调方法
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("接收消息, 主题 : " + Config.TOPIC + ", 内容："
                + new String(mqttMessage.getPayload()));
    }

    //消息发送成功后发布端的回调方法
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        try {
            System.out.println("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

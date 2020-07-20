package jk.mqtt;
;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    private MqttClient client;

    private boolean hasConnect = false;

    public Client(String broker, String clientId) throws Exception {
        if(broker == null || "".equals(broker)) {
            throw new Exception("服务器地址不能为空");
        }
        if(clientId == null || "".equals(clientId)) {
            throw new Exception("客户端编号不能为空");
        }

        this.client = new MqttClient(broker, clientId, new MemoryPersistence());
    }

    public boolean isConnect() {
        return client.isConnected();
    }

    public void connect(String username, String password, Will will) throws Exception {
        if(username == null || "".equals(username)) {
            throw new Exception("用户名不能为空");
        }
        if(password == null || "".equals(password)) {
            throw new Exception("密码不能为空");
        }
        if(client.isConnected()) {
            return;
        }

        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setConnectionTimeout(10);
        mqttConnectOptions.setKeepAliveInterval(20);
        client.setCallback(new CallBack());
        if(will != null) {
            mqttConnectOptions.setWill(will.getTopic(), will.getContent(), will.getQos(), will.isRetained());
        }
        client.connect(mqttConnectOptions);
        hasConnect = true;
    }

    public void reConnect() throws Exception {
        if(!isConnect()) {
            if(!hasConnect) {
                throw new Exception("未进行连接");
            } else {
                client.reconnect();
            }


        }
    }

    public void subscribe(String[] topics, int[] qos) throws MqttException {
        client.subscribe(topics, qos);
    }

    public void publish(String topic, int qos, boolean retained, String payload) throws Exception{
        if(!isConnect()) {
            reConnect();
        }
        MqttTopic mqttTopic = client.getTopic(topic);
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);
        mqttMessage.setPayload(payload.getBytes());
        MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
        token.waitForCompletion();
    }

    public void disConnect() throws MqttException {
        if(client.isConnected()) {
            client.disconnect();
            client.close();
        }
    }
}

class Publisher {
    public static void main(String[] args) throws Exception {
        Client client = new Client(Config.BROKER, "testjk");
        client.connect(Config.USERNAME, Config.PASSWORD, null);
        client.publish(Config.TOPIC, Config.QOS_HIGH, false, "哦");
        client.disConnect();
    }
}

class Subscriber {
    public static void main(String[] args) {
        for(int i = 0; i < 60000; i ++){
            try {
                Client client = new Client(Config.BROKER, Config.SUBSCRIBER_CLIENT_ID + i);
                client.connect(Config.USERNAME, Config.PASSWORD, null);
                System.out.println("连接完成，当前连接数：" + i);
            } catch (Exception e) {

            }

        }
    }
}

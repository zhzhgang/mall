package com.zhzhgang.mall.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

import javax.jms.*;

/**
 * @author zhzhgang
 * @create 2018-01-28 21:45
 */
public class TestActiveMq {

    // queue 点对点形式
    // Producer
    @Test
    public void testQueueProducer() throws Exception {
        // 创建连接工厂对象 ConnectionFactory，需要指定 mq 服务的 IP 及端口
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.56.101:61616");

        // 使用 ConnectionFactory 创建连接对象 Connection
        Connection connection = connectionFactory.createConnection();

        // 开启连接
        connection.start();

        // 使用 Connection 对象创建 Session 对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 使用 Session 对象创建 Destination 对象
        Queue queue = session.createQueue("test-queue");

        // 使用 Session 对象创建生产者对象 Producer
        MessageProducer producer = session.createProducer(queue);

        // 创建消息对象 TextMessage
        TextMessage message = new ActiveMQTextMessage();
        message.setText("hello activemq...");

        // 发送消息
        producer.send(message);

        // 关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    // Consumer
    @Test
    public void testQueueConsumer() throws Exception {
        // 创建连接工厂对象 ConnectionFactory，需要指定 mq 服务的 IP 及端口
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.56.101:61616");

        // 使用 ConnectionFactory 创建连接对象 Connection
        Connection connection = connectionFactory.createConnection();

        // 开启连接
        connection.start();

        // 使用 Connection 对象创建 Session 对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 使用 Session 对象创建 Destination 对象，Destination 应该和消息的发送端一致
        Queue queue = session.createQueue("test-queue");

        // 使用 Session 对象创建消费者对象 Consumer
        MessageConsumer consumer = session.createConsumer(queue);

        // 向 Consumer 对象中设置 MessageListener 对象，用于接收消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                // 取消息
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        String text = textMessage.getText();
                        System.out.println("-----------zhzhgang-----------text值 = " + text + ", " + "当前类 = TestActiveMq.testQueueConsumer()");
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        System.in.read();

        // 关闭资源
        consumer.close();
        session.close();
        connection.close();
    }

    // topic 发布-订阅形式
    // Producer
    @Test
    public void testTopicProducer() throws Exception {
        // 创建连接工厂对象 ConnectionFactory，需要指定 mq 服务的 IP 及端口
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.56.101:61616");

        // 使用 ConnectionFactory 创建连接对象 Connection
        Connection connection = connectionFactory.createConnection();

        // 开启连接
        connection.start();

        // 使用 Connection 对象创建 Session 对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 使用 Session 对象创建 Destination 对象，使用 topic
        Topic topic = session.createTopic("test-topic");

        // 使用 Session 对象创建生产者对象 Producer
        MessageProducer producer = session.createProducer(topic);

        // 创建消息对象 TextMessage
        TextMessage message = new ActiveMQTextMessage();
        message.setText("hello activemq topic...");

        // 发送消息
        producer.send(message);

        // 关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    // Consumer
    @Test
    public void testTopicConsumer() throws Exception {
        // 创建连接工厂对象 ConnectionFactory，需要指定 mq 服务的 IP 及端口
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.56.101:61616");

        // 使用 ConnectionFactory 创建连接对象 Connection
        Connection connection = connectionFactory.createConnection();

        // 开启连接
        connection.start();

        // 使用 Connection 对象创建 Session 对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 使用 Session 对象创建 Destination 对象，Destination 应该和消息的发送端一致
        Topic topic = session.createTopic("test-topic");

        // 使用 Session 对象创建消费者对象 Consumer
        MessageConsumer consumer = session.createConsumer(topic);

        System.out.println("topic 消费者2");

        // 向 Consumer 对象中设置 MessageListener 对象，用于接收消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                // 取消息
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        String text = textMessage.getText();
                        System.out.println("-----------zhzhgang-----------text值 = " + text + ", " + "当前类 = TestActiveMq.testQueueConsumer()");
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        System.in.read();
        // 关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}

package TopicRouting;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TopicPublisher {

    private static final String EXCHANGE_NAME = "topic_name";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("root");
        factory.setPassword("root");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            String message = "test_message";

            channel.basicPublish(EXCHANGE_NAME, "topic.name", null, message.getBytes("UTF-8"));

            channel.basicPublish(EXCHANGE_NAME, "topic2.name", null, message.getBytes("UTF-8"));


            System.out.println(" [x] Sent '" + "':'" + message + "'");
        }
    }


}


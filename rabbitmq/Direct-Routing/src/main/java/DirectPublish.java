import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class DirectPublish {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("root");
        factory.setPassword("root");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            String message = "test_message";

            channel.basicPublish(EXCHANGE_NAME, "test", null, message.getBytes("UTF-8"));
            // will not be delivered
            channel.basicPublish(EXCHANGE_NAME, "test2", null, message.getBytes("UTF-8"));

            System.out.println(" [x] Sent '" + "':'" + message + "'");
        }
    }


}


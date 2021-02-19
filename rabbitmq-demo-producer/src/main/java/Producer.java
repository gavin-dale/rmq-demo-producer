import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Producer {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {

        // establishing connection to rabbitmq
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // creating queue
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";

        // publishing 5 messages
        // TODO: add number to actual message sent to rabbit to show they're different messages
        for(int i = 0; i < 5; i++){
            channel.basicPublish("", QUEUE_NAME,null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'" + i);
            Thread.sleep(1000);
        }
        System.exit(1);
    }
}
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.types.Field;

import java.io.FileInputStream;
import java.util.Properties;

public class Controller {
    public static void main(String[] args) throws Exception {

        // This is to test that the value is being passed
        FileInputStream input = new FileInputStream("/config/config.properties");
        Properties props = new Properties(); // empty properties object
        props.load(input); // loads the value from the config.properties file        System.out.println(props); // print the value of key

        String bootstrapServers = System.getenv("BOOTSTRAP_SERVERS");// if not set it will be null
        System.out.println("getting BOOTSTRAP_SERVERS");

        if (bootstrapServers == null) {
            System.out.println("if null is returned");
            if (args.length > 1) {
                System.out.println("greater than 1");
                bootstrapServers = args[1];
            } else {
                System.out.println("needs more arguments");
                throw new IllegalArgumentException("Not enough arguments supplied");
            }
        }
        if (args[0].equals("producer")) { // object literal calling .equals and passing it producer
            System.out.println("Starting producer");
            KafkaProducerClass myProducer = new KafkaProducerClass(props);
            myProducer.producing();
        } else if (args[0].equals("consumer")) {
            System.out.println("Starting consumer");
            KafkaConsumerClass myConsumer = new KafkaConsumerClass(props);
            myConsumer.consuming();
        } else throw new IllegalArgumentException("Unknown Argument: " + args[0] + " inputted.");
    }
}
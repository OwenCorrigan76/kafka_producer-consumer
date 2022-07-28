import java.util.Properties;
public class Controller {
    public static void main(String[] args) throws Exception {
        // This is to test that the value is being passed
        Properties props = KafkaCreateProducer.getProperties(); // new Properties object called propsKey
        props.getProperty("key"); // get the key from config.properties
        System.out.println(props); // print the value of key


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

            KafkaCreateProducer myProducer = new KafkaCreateProducer(bootstrapServers);
            System.out.println("Hey");
            myProducer.producing();
        } else if (args[0].equals("consumer")) {
            System.out.println("Starting consumer");
            KafkaCreateConsumer myConsumer = new KafkaCreateConsumer(bootstrapServers);
            myConsumer.consuming();
        } else throw new IllegalArgumentException("Unknown Argument: " + args[0] + " inputted.");
    }
}
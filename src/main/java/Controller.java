
import java.io.FileInputStream;
import java.util.Properties;

public class Controller {
    public static void main(String[] args) throws Exception {


        FileInputStream input = new FileInputStream("/config/config.properties"); // Opens a file and puts contents into input
        Properties props = new Properties(); // empty properties object
        props.load(input); // loads the value from the config.properties file and attach to props
        System.out.println(props); // print the value of all that is in props

        String bootstrapServers = System.getenv("BOOTSTRAP_SERVERS");// get from envVar
        System.out.println("getting BOOTSTRAP_SERVERS"); // print out to make sure the result is correct

        if (bootstrapServers == null) { // if there is no bootstrap server
            System.out.println("if null is returned");
            if (args.length > 1) { // if there are more than 1 argument passed in from user
                System.out.println("greater than 1"); // simple print statement
                bootstrapServers = args[1]; // bootstrap server = second argument passed in
            } else {
                System.out.println("needs more arguments"); // if there's only 1 argument passed in....
                throw new IllegalArgumentException("Not enough arguments supplied"); // throw this error
            }
        }
        if (args[0].equals("producer")) { // if first argument passed in is producer. (.equals instead of ==, it's a String)
            System.out.println("Starting producer");
            KafkaProducerClass myProducer = new KafkaProducerClass(props); // create object with the values in props
            myProducer.producing(); // run the .producing() method in KafkaProducerClass
        } else if (args[0].equals("consumer")) { // if first argument is consumer
            System.out.println("Starting consumer");
            KafkaConsumerClass myConsumer = new KafkaConsumerClass(props);// create object with the values in props
            myConsumer.consuming();// run the .consuming() method in KafkaConsumerClass
        } else throw new IllegalArgumentException("Unknown Argument: " + args[0] + " inputted."); // if none of the above, throw this error
    }
}
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class KafkaConsumerClass {  // name of the class
    Properties props; // create an empty Properties object called props

    public KafkaConsumerClass(Properties props) // constructor with Properties object as a parameter
    {
        this.props = props;
    }

    void consuming() throws InterruptedException {  // method which is called in main

        KafkaConsumer<String, String> simpleConsumer = new KafkaConsumer<>(props);
        // new KafkaConsumer object with the values from config in props


        simpleConsumer.subscribe(Arrays.asList("kafka.learning.orders"));  //Subscribe to the kafka.learning.orders topic


        while (true) {
            ConsumerRecords<String, String> messages = // put poll results in messages
                    simpleConsumer.poll(Duration.ofMillis(3000)); // poll for message every .3 seconds
            System.out.println("This is working"); // simple print statement
            //Print batch of records consumed
            for (ConsumerRecord<String, String> message : messages) //loop, fo every message in messages
                System.out.println("Message received : " + message); // print including the contents of massage
            sleep(3000); // wait for 3 seconds
        }
    }
}

   /*
    don't need below for now
    public static KafkaConsumerClass fromEnv(){
        String bootstrapServers = System.getenv("BOOTSTRAP_SERVERS");
        String topic = System.getenv("TOPIC");
        String groupId = System.getenv("GROUP_ID");
        return new KafkaConsumerClass(bootstrapServers, topic, groupId);
    }
*/
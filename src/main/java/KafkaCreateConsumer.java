import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class KafkaCreateConsumer {
    public String bootstrapServers;


    public KafkaCreateConsumer(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public static Properties getProperties() { // new method using Properties Class
        Properties props = null;
        try {
            FileInputStream input = new FileInputStream("src/main/resources/config.properties");
            props = new Properties();
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props;
    }

    //Setup Properties for consumer
    Properties props = getProperties();

    void consuming() throws InterruptedException {
       props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        System.out.println(bootstrapServers);

        //Create a Consumer
        KafkaConsumer<String, String> simpleConsumer =
                new KafkaConsumer<String, String>(props);
        //Subscribe to the kafka.learning.orders topic
        simpleConsumer.subscribe(Arrays.asList("my-topic"));
        while (true) {
            ConsumerRecords<String, String> messages =
                    simpleConsumer.poll(Duration.ofMillis(300)); // poll for message every .3 seconds
            System.out.println("Listening....");
            //Print batch of records consumed
            for (ConsumerRecord<String, String> message : messages) //loop
                System.out.println("Message received : " + message);
            sleep(3000); // wait for 3 seconds
        }
    }
}

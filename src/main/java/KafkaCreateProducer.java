import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class KafkaCreateProducer {
    public String bootstrapServers;

    private final Properties props = getProperties(); //Setup Properties for Kafka Producer

    public KafkaCreateProducer(String bootstrapServers) {  // constructor
        this.bootstrapServers = bootstrapServers;
    }

    public static Properties getProperties() { // new method using Properties Class
        Properties props = null;
        try {
            props = new Properties();
            FileInputStream input = new FileInputStream("src/main/resources/config.properties");
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props;
    }

    public void producing() { // this will produce the messages

        KafkaProducer simpleProducer = new KafkaProducer(props); // new KafkaProducer called simpleProducer
        try {
            while (true) {
                ProducerRecord<String, String> owensKafkaRecord =
                        new ProducerRecord<String, String>(
                                "my-topic",    //Topic name
                                "Sending....."         //Message Content
                        );
                System.out.println("Producer Message");// print message and ProducerRecord
                sleep(3000); // wait 3 seconds between print statements
                simpleProducer.send(owensKafkaRecord); //Publish to Kafka
                Thread.sleep(3000); // wait 3 seconds
            }
        } catch (Exception e) {
        }
    }
}
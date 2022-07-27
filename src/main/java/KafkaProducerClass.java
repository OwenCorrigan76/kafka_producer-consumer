import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;  //import Properties util

import static java.lang.Thread.sleep;

public class KafkaProducerClass {  // class name
    //Setup Properties for Kafka Producer
    Properties props; // new instance of the Properties Class called props

    public KafkaProducerClass(Properties props) {  // constructor that has a Properties parameter
        this.props = props;
    }

    public void producing() { // this will produce the messages

        //Create a Kafka producer from configuration
        try (KafkaProducer simpleProducer = new KafkaProducer(props)) {
            try {
                while (true) {
                    ProducerRecord<String, String> owensKafkaRecord = new ProducerRecord<>("kafka.learning.orders",    //Topic name
                            "Owen Sending Message Hello-World! "        //Message Content
                    );
                    System.out.println("Program is Running" + owensKafkaRecord);// to the console
                    Thread.sleep(3000); // wait 3 seconds between print statements

                    //Publish to Kafka
                    simpleProducer.send(owensKafkaRecord);

                    Thread.sleep(3000); // wait for 3 seconds
                }
            } catch (Exception e) { // error handling
            }
        } // new producer taking in props object that has config values
    }
}
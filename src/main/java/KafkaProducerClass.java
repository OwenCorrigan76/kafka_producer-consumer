import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;  //import Properties util

import static java.lang.Thread.sleep;

public class KafkaProducerClass {  // class name
    public String bootstrapServers;  //

    public KafkaProducerClass(String bootstrapServers) {  // constructor
        this.bootstrapServers = bootstrapServers;
    }

    //Setup Properties for Kafka Producer
    Properties kafkaProps = new Properties(); // new instance of the Properties Class called kafkaProps

    void producing() { // this will produce the messages
        //List of brokers to connect to
        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        //Serializer class used to convert Keys to Byte Arrays
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        //Serializer class used to convert Messages to Byte Arrays
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        //Create a Kafka producer from configuration
        KafkaProducer simpleProducer = new KafkaProducer(kafkaProps); // new producer called simpleProducer
        try {
            while (true) {
                ProducerRecord<String, String> owensKafkaRecord =
                        new ProducerRecord<String, String>(
                                "kafka.learning.orders",    //Topic name
                                //   String.valueOf(i),          //Key for the message
                                "Owen Sending Message Hello-World! "        //Message Content
                        );
                System.out.println("Program is Running" + owensKafkaRecord.toString());// to the console
                sleep(3000); // wait 3 seconds between print statements

                //Publish to Kafka
                simpleProducer.send(owensKafkaRecord);

                Thread.sleep(2000);
            }
        } catch (Exception e) {
        }
    }
}


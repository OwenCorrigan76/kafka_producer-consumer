import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import static java.lang.Thread.sleep;

public class KafkaProducerClass {
    public String boot;
    public KafkaProducerClass(String boot){
        this.boot=boot;
    }

    //Setup Properties for Kafka Producer
    Properties kafkaProps = new Properties();

    void producing() { // this will produce the messages
        //List of brokers to connect to
      kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, boot);

    //Serializer class used to convert Keys to Byte Arrays
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
            "org.apache.kafka.common.serialization.StringSerializer");

    //Serializer class used to convert Messages to Byte Arrays
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
            "org.apache.kafka.common.serialization.StringSerializer");

    //Create a Kafka producer from configuration
    KafkaProducer simpleProducer = new KafkaProducer(kafkaProps);

        try {

            while (true) {
                ProducerRecord<String, String> owensKafkaRecord =
                        new ProducerRecord<String, String>(
                                "kafka.learning.orders",    //Topic name
                                //   String.valueOf(i),          //Key for the message
                                "Owen Sending Message Yo "        //Message Content
                        );
                System.out.println("Program is Running" + owensKafkaRecord.toString());
                sleep(3000);

                //Publish to Kafka
                simpleProducer.send(owensKafkaRecord);

                Thread.sleep(2000);
            }

        } catch (Exception e) {

        }
    }
    void callConsumer () {
    }
}


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;

import java.io.FileInputStream;
import java.util.Properties;  //import Properties util

import static java.lang.Thread.sleep;

public class KafkaProducerClass {  // class name
    public String bootstrapServers;
    public String topic;
    public String key;  // key serializer
    public String value; // value serializer

    public KafkaProducerClass(String bootstrapServers, String topic, String key, String value) {  // constructor
        this.bootstrapServers = bootstrapServers;
        this.topic = topic;
        this.key = key;
        this.value = value;
    }

    public static Properties getProperties() throws Exception { // new method using Properties Class
        FileInputStream input = new FileInputStream("../src/main/resources/config.properties");
        Properties props = new Properties();
        props.load(input);
        String userName = props.getProperty("username");
        String topic = props.getProperty("default.topic");
        String key = props.getProperty("key.serializer");
        String value = props.getProperty("value.serializer");
        return props;
    }

    //Setup Properties for Kafka Producer
    Properties props = new Properties(); // new instance of the Properties Class called props

    public void producing() { // this will produce the messages
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, key);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, value);

        //Create a Kafka producer from configuration
        KafkaProducer simpleProducer = new KafkaProducer(props); // new producer called simpleProducer
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


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class KafkaConsumerClass {
    public String bootstrapServers;
    public String topic;
    public String groupId;

    public KafkaConsumerClass(String bootstrapServers, String topic, String groupId) {
        this.bootstrapServers = bootstrapServers;
        this.topic = topic;
        this.groupId = groupId;
    }
// getters and setters  public


    void consuming() throws InterruptedException {

        //Setup Properties for consumer
        Properties kafkaProps = new Properties();

        //List of Kafka brokers to connect to
        kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        System.out.println(bootstrapServers);



        //Deserializer class to convert Keys from Byte Array to String
        kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        //Deserializer class to convert Messages from Byte Array to String
        kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        //Consumer Group ID for this consumer
        kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG,
                groupId); // this is the groupId

        //Set to consume from the earliest message, on start when no offset is
        //available in Kafka
        kafkaProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                "earliest");

        //Create a Consumer
        KafkaConsumer<String, String> simpleConsumer =
                new KafkaConsumer<String, String>(kafkaProps);

        //Subscribe to the kafka.learning.orders topic
        simpleConsumer.subscribe(Arrays.asList("kafka.learning.orders"));


        //Continuously poll for new messages
        while (true) {
            ConsumerRecords<String, String> messages =
                    simpleConsumer.poll(Duration.ofMillis(300)); // poll for message every .3 seconds
            System.out.println("This is working");
            //Print batch of records consumed
            for (ConsumerRecord<String, String> message : messages) //loop
                System.out.println("Message received : " + message);
            sleep(3000); // wait for 3 seconds
        }
    }
}

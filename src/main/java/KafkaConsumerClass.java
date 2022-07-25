import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.protocol.types.Field;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;


import static java.lang.Thread.sleep;

public class KafkaConsumerClass {
    public String bootstrapServers;
    public String topic;
    public String groupId;
    public String key;  // key deserializer
    public String value; // value deserializer

    public KafkaConsumerClass(String bootstrapServers, String topic, String groupId, String key, String value) {
        this.bootstrapServers = bootstrapServers;
        this.topic = topic;
        this.groupId = groupId;
        this.key = key;
        this.value = value;
    }

    public static Properties getProperties() throws Exception { // new method using Properties Class
        FileInputStream input = new FileInputStream("../src/main/resources/config.properties");
        Properties props = new Properties();
        props.load(input);
        String userName = props.getProperty("username");
        String topic = props.getProperty("default.topic");
        String key = props.getProperty("key.deserializer");
        String value = props.getProperty("value.deserializer");
        return props;
    }


    //Setup Properties for consumer
    Properties props = new Properties();

    void consuming() throws InterruptedException {

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        System.out.println(bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, key);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, value);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        //Create a Consumer
        KafkaConsumer<String, String> simpleConsumer =
                new KafkaConsumer<String, String>(props);

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

    /* getters and setters (not used yet, everything still public)
   public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

*/
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
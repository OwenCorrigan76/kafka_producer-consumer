import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;


import static java.lang.Thread.sleep;

public class KafkaConsumerClass {
    Properties props = new Properties();

    public KafkaConsumerClass(Properties props) {
        this.props = props;
    }
    void consuming() throws InterruptedException {

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
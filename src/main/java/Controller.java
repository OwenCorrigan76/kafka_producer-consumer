// this is to call consumer or producer

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class Controller {

    public static void main(String[] args) throws InterruptedException {
        
       KafkaProducerClass myProducer= new KafkaProducerClass(){};
       KafkaConsumerClass myConsumer = new KafkaConsumerClass();

      
       myProducer.producing();
       myConsumer.consuming();
    }
}


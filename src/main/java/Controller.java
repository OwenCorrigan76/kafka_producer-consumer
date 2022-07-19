// this is to call consumer or producer

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;


import java.util.Properties;

import static java.lang.Thread.sleep;

public class Controller {

    public static void main(String[] args) throws InterruptedException {
        String bootstrapServers = System.getenv("BOOTSTRAP_SERVERS"); // if not set it will be null
       if  (bootstrapServers == null)
       {
           if (args.length >1) {
               bootstrapServers = args[1];
           } else {
               throw new IllegalArgumentException("Not enough Arguments. Need to supply Class");
           }
       }

        if (args[0].equals("producer")) { // object literal    calling .equals and passing it producer
            System.out.println("Starting producer");
            KafkaProducerClass myProducer = new KafkaProducerClass(bootstrapServers);
            myProducer.producing();
        } else if (args[0].equals("consumer")) {
            System.out.println("Starting consumer");
            KafkaConsumerClass myConsumer = new KafkaConsumerClass(bootstrapServers);
            myConsumer.consuming();

        } else {
            throw new IllegalArgumentException("Unknown Argument: " + args[0] + " inputted.");
        }
    }
}



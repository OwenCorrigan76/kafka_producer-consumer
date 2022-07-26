import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.swing.plaf.synth.SynthToolTipUI;
import java.io.FileInputStream;
import java.util.Properties;  //import Properties util

import static java.lang.Thread.sleep;

public class KafkaProducerClass {  // class name
    //Setup Properties for Kafka Producer
    Properties props = new Properties(); // new instance of the Properties Class called props


    public KafkaProducerClass(Properties props) {  // constructor
       this.props = props;
    }

    public void producing() throws Exception { // this will produce the messages

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
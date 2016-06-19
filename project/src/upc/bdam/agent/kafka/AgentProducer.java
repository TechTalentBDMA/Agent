package upc.bdam.agent.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

public class AgentProducer {


  public void produce(byte[] bean) {

    Map<String, Object> conf = new HashMap<String, Object>();
    conf.put("bootstrap.servers", "debian-kafka-vm2:9092");
    conf.put("group.id", "news of the day");
    conf.put("key.serializer", StringSerializer.class.getName());
    conf.put("value.serializer", ByteArraySerializer.class.getName());
    conf.put("value.serializer.jackson.smile", "true");

    KafkaProducer<String, String> producer = new KafkaProducer<String, String>(conf);
    KafkaProducer<String, byte[]> agentProducer = new KafkaProducer<String, byte[]>(conf);

    agentProducer.send(new ProducerRecord<String, byte[]>("home", bean));

    agentProducer.close();
    producer.close();
  }
}

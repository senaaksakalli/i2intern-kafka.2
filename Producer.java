package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {

    public static void main(String[] args) {
    
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());

        
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        try {
            String topic = "test-topic";

        
            Student s1 = new Student(1, "Sena");
            Student s2 = new Student(2, "Miray");
            Student s3 = new Student(3, "Efe");

            
            producer.send(new ProducerRecord<>(topic, "1", s1.toJson()));
            producer.send(new ProducerRecord<>(topic, "2", s2.toJson()));
            producer.send(new ProducerRecord<>(topic, "3", s3.toJson()));

            System.out.println("Öğrenciler Kafka’ya gönderildi.");

        } catch (JsonProcessingException e) {
            System.err.println("JSON dönüştürme hatası: " + e.getMessage());
        } finally {
            producer.close();
        }
    }
}

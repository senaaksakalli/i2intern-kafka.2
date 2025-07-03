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

            for (int i = 1; i <= 10000; i++) {
                Student student = new Student(i, "Student " + i);
                String value = student.toJson();
                String key = String.valueOf(i);

                ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
                producer.send(record);

                if (i % 1000 == 0) {
                    System.out.println("Sent " + i + " students...");
                }
            }

            producer.flush();
            System.out.println("Finished sending 10,000 students.");

        } catch (JsonProcessingException e) {
            System.err.println("JSON serialization error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}

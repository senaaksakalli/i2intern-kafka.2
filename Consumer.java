package org.example;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) {
        // Set the properties for Kafka Consumer
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "test-group");
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());

        // Create the Kafka Consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        try {
            // Subscribe to the topic
            consumer.subscribe(Collections.singletonList("test-topic"));

            // Poll for new messages
            while (true) {
                var records = consumer.poll(Duration.ofSeconds(2));  // Poll for 2 seconds

                records.forEach(record -> {
                    System.out.println("Received message: Key = " + record.key() + ", Value = " + record.value());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the consumer
            consumer.close();
        }
    }
}

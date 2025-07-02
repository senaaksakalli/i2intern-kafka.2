package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {

    public static void main(String[] args) {
        // Set the properties for Kafka Producer
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());

        // Create the Kafka Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        try {
            String topic = "test-topic";

            // Send 10,000 messages
            for (int i = 1; i <= 10000; i++) {
                String key = String.valueOf(i);  // Key: the message ID (1 to 10000)
                String value = "Student " + i;  // Value: the student name

                // Create a ProducerRecord and send it to Kafka
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
                producer.send(record);

                // Optional: Print to track the progress
                if (i % 1000 == 0) {  // Print every 1000 messages to avoid clutter
                    System.out.println("Sent " + i + " messages...");
                }
            }

            // Flush the producer to make sure all messages are sent
            producer.flush();
            System.out.println("Finished sending 10,000 messages.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the producer
            producer.close();
        }
    }
}

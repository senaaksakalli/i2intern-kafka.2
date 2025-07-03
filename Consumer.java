package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) {

        // Kafka ayarları
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "test-group");
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        try {
            consumer.subscribe(Collections.singletonList("test-topic"));

            while (true) {
                var records = consumer.poll(Duration.ofSeconds(2));  // Mesajları al
                for (var record : records) {
                    try {

                        ObjectMapper objectMapper = new ObjectMapper();
                        Student student = objectMapper.readValue(record.value(), Student.class);


                        System.out.println("Alındı: " + student);

                    } catch (Exception e) {
                        System.err.println("JSON çözümleme hatası: " + record.value());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close(); // Kafka consumer'ını kapat
        }
    }
}

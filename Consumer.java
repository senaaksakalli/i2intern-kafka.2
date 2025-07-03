
package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {

    public static <JsonProcessingException> void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "test-group");
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        try {
            consumer.subscribe(Collections.singletonList("test-topic"));

            while (true) {
                var records = consumer.poll(Duration.ofSeconds(2));
                for (var record : records) {
                    try {
                        Student student = Student.fromJson(record.value());
                        System.out.println("Alındı: " + student);
                    } catch (JsonProcessingException e) {
                        System.out.println("JSON çözümleme hatası: " + record.value());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}

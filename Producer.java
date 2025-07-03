package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {

    public static void main(String[] args) {

        // Kafka ayarları
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092"); //  broker
        properties.put("key.serializer", StringSerializer.class.getName()); // Anahtar
        properties.put("value.serializer", StringSerializer.class.getName()); // Değer


        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        try {
            String topic = "test-topic";


            Student s1 = new Student(1, "Sena");
            Student s2 = new Student(2, "Miray");
            Student s3 = new Student(3, "Efe");


            ObjectMapper objectMapper = new ObjectMapper();
            String s1Json = objectMapper.writeValueAsString(s1);
            String s2Json = objectMapper.writeValueAsString(s2);
            String s3Json = objectMapper.writeValueAsString(s3);


            producer.send(new ProducerRecord<>(topic, "1", s1Json), (metadata, exception) -> {
                if (exception != null) {
                    System.err.println("Hata: " + exception.getMessage());
                } else {
                    System.out.println("Mesaj başarıyla gönderildi: " + metadata);
                }
            });

            producer.send(new ProducerRecord<>(topic, "2", s2Json), (metadata, exception) -> {
                if (exception != null) {
                    System.err.println("Hata: " + exception.getMessage());
                } else {
                    System.out.println("Mesaj başarıyla gönderildi: " + metadata);
                }
            });

            producer.send(new ProducerRecord<>(topic, "3", s3Json), (metadata, exception) -> {
                if (exception != null) {
                    System.err.println("Hata: " + exception.getMessage());
                } else {
                    System.out.println("Mesaj başarıyla gönderildi: " + metadata);
                }
            });

            System.out.println("Öğrenciler Kafka’ya gönderildi.");

        } catch (JsonProcessingException e) {
            System.err.println("JSON dönüştürme hatası: " + e.getMessage());
        } finally {
            producer.close();
    }
}

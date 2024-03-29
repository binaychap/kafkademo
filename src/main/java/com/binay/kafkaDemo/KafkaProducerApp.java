package com.binay.kafkaDemo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.UUID;

public class KafkaProducerApp {
    public static void produce() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9091, localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> myProducer = new KafkaProducer<>(props);

        try {
            for (int i = 0; i < 150000000; i++) {
                myProducer.send(new ProducerRecord<>("deploy", UUID.randomUUID().toString(), MessageUtils.jsonMsg + Integer.toString(i)));
                System.out.println("message sent "+ i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myProducer.close();
        }
    }
}

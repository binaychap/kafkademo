package com.binay.kafkaDemo;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Properties;

public class KafkaConsumerApp {

    public static void  consume() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9091, localhost:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // type contract extends to consumer
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // type contract extends to consumer
        props.put("group.id", "test-consumer-group");
        KafkaConsumer<String, String> myConsumer = new KafkaConsumer<>(props);

        ArrayList<String> topics = new ArrayList<String>();
        topics.add("deploy");
        topics.add("assignment");

        myConsumer.subscribe(topics);
        try {
            while (true) {
                ConsumerRecords<String, String> records = myConsumer.poll(30L);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(
                            String.format("Topic: %s, " +
                                            "Partition: %d, " +
                                            "Offset: %d, " +
                                            "Key: %s, " +
                                            "Value: %s",
                                    record.topic(),
                                    record.partition(),
                                    record.offset(),
                                    record.key(),
                                    record.value())
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            myConsumer.close();
        }
    }

}

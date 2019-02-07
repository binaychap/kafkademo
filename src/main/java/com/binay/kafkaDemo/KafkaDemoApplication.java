package com.binay.kafkaDemo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaDemoApplication implements ApplicationRunner {
	private static final String PRODUCER = "producer";
	private static final String CONSUMER = "consumer";

	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for(String appName : args.getNonOptionArgs()) {
			if (PRODUCER.equalsIgnoreCase(appName)) {
				KafkaProducerApp.produce();
			} else if (CONSUMER.equalsIgnoreCase(appName)) {
				KafkaConsumerApp.consume();
			}

		}

	}
}


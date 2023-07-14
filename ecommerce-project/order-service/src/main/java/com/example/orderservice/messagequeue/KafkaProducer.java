package com.example.orderservice.messagequeue;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.orderservice.dto.OrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@EnableKafka
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {
	private final KafkaTemplate<String, String> kafkaTemplate;

	public OrderDto send(String topic, OrderDto orderDto){
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(orderDto);
		} catch (JsonProcessingException exception) {
			exception.printStackTrace();
		}

		kafkaTemplate.send(topic, jsonInString);
		log.info("Kafka Producer sent data from the microService => {}",orderDto);

		return orderDto;
	}
}

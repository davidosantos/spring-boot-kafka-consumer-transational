package com.davidosantos.kafka.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class KafkaConsumer {

    @KafkaListener(topics = "users_survey_results", groupId = "consumer-for-testing" , id = "consumer-for-testing-1" )
    public void consume(
            final Acknowledgment acknowledgment,
            // Used when not working with avro data import org.springframework.messaging.handler.annotation.Payload; @Payload
            ConsumerRecord<String, Survey> survey,
            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) final String key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) final String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) final String partitionId,
            @Header(KafkaHeaders.OFFSET) final String offset) {

        log.info("Topic = {}, partition = {}, offset = {}, key = {}, msg = {}",
                topic, partitionId, offset, key, survey);
        acknowledgment.acknowledge();
    }

}

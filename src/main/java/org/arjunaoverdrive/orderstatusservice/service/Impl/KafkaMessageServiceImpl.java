package org.arjunaoverdrive.orderstatusservice.service.Impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.kafka.OrderStatusMessage;
import org.arjunaoverdrive.orderstatusservice.service.KafkaMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMessageServiceImpl implements KafkaMessageService {

    private final KafkaTemplate kafkaTemplate;

    @Value("${app.kafka.kafkaSendToTopicName}")
    private String sendToTopic;

    @Override
    public void sendChangeOrderStatusMessage(OrderStatusMessage message) {
        CompletableFuture<SendResult<String, OrderStatusMessage>> future =
                kafkaTemplate.send(sendToTopic, message);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message: {} with offset: {}", message, result.getRecordMetadata().offset());
            } else {
                log.warn("Unable to send message: {} due to {}", message, ex.getMessage());
            }
        });
    }
}

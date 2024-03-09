package org.arjunaoverdrive.orderstatusservice.service;

import org.arjunaoverdrive.kafka.OrderStatusMessage;

public interface KafkaMessageService {
    void sendChangeOrderStatusMessage(OrderStatusMessage message);
}

package org.arjunaoverdrive.kafka;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class OrderStatusMessage {
    private Status status;
    private Instant date;
}

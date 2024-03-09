package org.arjunaoverdrive.kafka;

import lombok.Data;

@Data
public class OrderMessage {
    private String product;
    private Integer quantity;
}

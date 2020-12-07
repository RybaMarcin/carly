package org.carly.api.rest.request.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.carly.core.ordermanagement.model.cart.PartType;
import org.carly.core.vehiclemanagement.model.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String orderId;
    private String consumerId;
    private String consumerName;
    private String supplierId;
    private String supplierName;
    private Status status;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Map<PartType, List<PartRequest>> partsOrder;
}

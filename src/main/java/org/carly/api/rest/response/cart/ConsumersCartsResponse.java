package org.carly.api.rest.response.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumersCartsResponse {
    private List<ConsumerCartResponse> consumersCarts;
}

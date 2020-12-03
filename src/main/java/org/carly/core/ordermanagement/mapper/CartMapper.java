package org.carly.core.ordermanagement.mapper;

import org.carly.api.rest.response.cart.ConsumerCartResponse;
import org.carly.api.rest.response.cart.ConsumersCartsResponse;
import org.carly.api.rest.response.cart.PartResponse;
import org.carly.api.rest.response.cart.SupplierPartsResponse;
import org.carly.core.ordermanagement.model.cart.CartOrder;
import org.carly.core.ordermanagement.model.cart.PartType;
import org.carly.core.ordermanagement.model.cart.SpecificFactory;
import org.carly.core.ordermanagement.model.cart.SpecificPart;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CartMapper {

    public ConsumersCartsResponse mapFromConsumerCartToList(List<CartOrder> cartOrders) {
        List<ConsumerCartResponse> ccr = new ArrayList<>();
        for (CartOrder cartOrder : cartOrders) {
            ccr.add(mapFromDomainToSingleResponse(cartOrder));
        }
        return new ConsumersCartsResponse(ccr);
    }

    public ConsumerCartResponse mapFromDomainToSingleResponse(CartOrder cartOrder) {
        ConsumerCartResponse response = new ConsumerCartResponse();
        response.setConsumerId(cartOrder.getConsumerId());
        response.setConsumerName(cartOrder.getConsumerName());
        response.setFactoriesParts(createSupplierPartResponse(cartOrder.getFactoryParts()));
        return response;
    }

    private List<SupplierPartsResponse> createSupplierPartResponse(List<SpecificFactory> factories) {
        List<SupplierPartsResponse> supplierParts = new ArrayList<>();
        for (SpecificFactory factory : factories) {
            SupplierPartsResponse partsResponse = new SupplierPartsResponse();
            partsResponse.setSupplierId(factory.getFactoryId());
            partsResponse.setSupplierName(factory.getFactoryName());
            partsResponse.setParts(createPartResponse(factory.getParts()));
            supplierParts.add(partsResponse);
        }
        return supplierParts;
    }

    private Map<PartType, List<PartResponse>> createPartResponse(Map<PartType, List<SpecificPart>> specificPart) {
        EnumMap<PartType, List<PartResponse>> map = new EnumMap<>(PartType.class);
        for (Map.Entry<PartType, List<SpecificPart>> entry : specificPart.entrySet()) {
            List<PartResponse> partResponses = new ArrayList<>();
            for (SpecificPart part : entry.getValue()) {
                PartResponse partResponse = new PartResponse();
                partResponse.setPartId(part.getPartId());
                partResponse.setPartName(part.getPartName());
                partResponse.setQuantity(part.getQuantity());
                partResponse.setAmountPerItem(part.getAmountPerItem());
                partResponses.add(partResponse);
                map.putIfAbsent(entry.getKey(), partResponses);
            }
        }
        return map;
    }
}

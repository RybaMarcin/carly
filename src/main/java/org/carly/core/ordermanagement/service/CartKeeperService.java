package org.carly.core.ordermanagement.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.PartToCartRequest;
import org.carly.api.rest.response.cart.ConsumerCartResponse;
import org.carly.core.ordermanagement.mapper.CartMapper;
import org.carly.core.ordermanagement.model.cart.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.annotation.ApplicationScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Getter
@ApplicationScope
public class CartKeeperService {

    private Map<CartOwner, CartOrder> cartOrders = new ConcurrentHashMap<>();

    public ResponseEntity<?> addOrUpdatePartToCart(PartToCartRequest request) {
        CartOwner cartOwner = new CartOwner(new ObjectId(request.getConsumerId()));
        if (request.getQuantity() < 1) {
            return null;
        }
        BigDecimal result = countAmountOfQuantity(request.getAmount(), request.getQuantity());
        List<SpecificPart> newSpecificParts = new ArrayList<>();
        List<SpecificFactory> factoriesParts = new ArrayList<>();
        EnumMap<PartType, List<SpecificPart>> specificFactoriesPartMap = new EnumMap<>(PartType.class);
        if (!cartOrders.containsKey(cartOwner)) {
            newSpecificParts.add(new SpecificPart(request.getPartId(),
                    request.getPartName(), request.getQuantity(), result)
            );
            specificFactoriesPartMap.put(request.getPartType(), newSpecificParts);
            factoriesParts.add((new SpecificFactory(request.getSupplierId(), request.getSupplierName(), specificFactoriesPartMap)));
            CartOrder cartOrder = new CartOrder(request.getConsumerId(), request.getConsumerName(), factoriesParts);
            cartOrders.put(cartOwner, cartOrder);
            return ResponseEntity.ok(cartOrders);
        } else if (cartOrders.containsKey(cartOwner) && cartOrders.get(cartOwner).getFactoryParts().stream().map(SpecificFactory::getFactoryId).noneMatch(sp -> sp.equals(request.getSupplierId()))) {
            cartOrders.get(cartOwner).getFactoryParts().add((new SpecificFactory(request.getSupplierId(), request.getSupplierName(), specificFactoriesPartMap)));
        }
        CartOrder cartOrder = cartOrders.get(cartOwner);
        for (SpecificFactory factory : cartOrder.getFactoryParts()) {
            if (factory.getFactoryId().equals(request.getSupplierId()) && !factory.getParts().containsKey(request.getPartType())) {
                newSpecificParts.add(new SpecificPart(request.getPartId(), request.getPartName(), request.getQuantity(), result));
                Map<PartType, List<SpecificPart>> parts = factory.getParts();
                parts.put(request.getPartType(), newSpecificParts);
            } else if (factory.getFactoryId().equals(request.getSupplierId())) {
                newSpecificParts = factory.getParts().get(request.getPartType());
                SpecificPart specificPart = newSpecificParts.stream()
                        .filter(sp -> sp.getPartId().equals(request.getPartId())).findFirst().orElse(null);
                if (specificPart == null) {
                    SpecificPart newPart = new SpecificPart(request.getPartId(), request.getPartName(), request.getQuantity(), result);
                    newSpecificParts.add(newPart);
                } else {
                    specificPart.setQuantity(request.getQuantity());
                    BigDecimal newAmount = countAmountOfQuantity(request.getAmount(), request.getQuantity());
                    specificPart.setAmountPerItem(newAmount);
                }
            }
        }
        return ResponseEntity.ok(cartOrders);
    }

    private BigDecimal countAmountOfQuantity(BigDecimal amountPerItem, int quantity) {
        return amountPerItem.multiply(BigDecimal.valueOf(quantity));
    }

    public ResponseEntity<?> getCartFactoriesInfoByConsumerId(String id) {
        CartOrder cartOrder = cartOrders.get(new CartOwner(new ObjectId(id)));
        if (cartOrder != null) {
            final ConsumerCartResponse response = new CartMapper().mapFromDomainToSingleResponse(cartOrder);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(id);
    }
}

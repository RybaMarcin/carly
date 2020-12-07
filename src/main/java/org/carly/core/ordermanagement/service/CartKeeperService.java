package org.carly.core.ordermanagement.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.carly.api.rest.request.cart.CartPartUpdate;
import org.carly.api.rest.request.cart.PartToCartRequest;
import org.carly.api.rest.response.ErrorResponse;
import org.carly.api.rest.response.SuccessResponse;
import org.carly.api.rest.response.cart.ConsumerCartResponse;
import org.carly.api.rest.response.cart.ConsumersCartsResponse;
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
import java.util.stream.Collectors;

@Slf4j
@Getter
@ApplicationScope
public class CartKeeperService {

    private Map<CartOwner, CartOrder> cartOrders = new ConcurrentHashMap<>();
    private Map<SupplierPartModel, BigDecimal> factoriesTotalAmountHolder = new ConcurrentHashMap<>();
    private Map<SupplierPartModel, Integer> factoriesTotalQuantityHolder = new ConcurrentHashMap<>();
    private Map<String, Integer> factoriesQuantities = new ConcurrentHashMap<>();
    private Map<String, BigDecimal> factoriesAmount = new ConcurrentHashMap<>();

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
            cartOrder.setTotalAmount(countAmount(cartOrder));
            cartOrder.setTotalQuantity(countQuantity(cartOrder));

            countFactoryTotalAmount(cartOrder, request);
            countFactoryTotalQuantity(cartOrder, request);

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
        countFactoryTotalQuantity(cartOrder, request);
        cartOrder.setTotalQuantity(countQuantity(cartOrder));

        countFactoryTotalAmount(cartOrder, request);
        cartOrder.setTotalAmount(countAmount(cartOrder));
        return ResponseEntity.ok(new CartMapper().mapFromConsumerCartToList(cartOrders.values()));
    }

    private void countFactoryTotalQuantity(CartOrder cartOrder, PartToCartRequest request) {
        for (SpecificFactory factoryPart : cartOrder.getFactoryParts())
            if (factoryPart.getFactoryId().equals(request.getSupplierId())) {
                final int sum = factoryPart.getParts().values()
                        .stream()
                        .map(sp -> sp.stream()
                                .map(SpecificPart::getQuantity)
                                .mapToInt(Integer::intValue).sum())
                        .mapToInt(Integer::intValue)
                        .sum();
                factoriesQuantities.put(request.getSupplierId(), sum);
                factoryPart.setTotalQuantityOfFactory(factoriesQuantities.get(request.getSupplierId()));
            }
    }

    private void countFactoryTotalAmount(CartOrder cartOrder, PartToCartRequest request) {
        for (SpecificFactory factoryPart : cartOrder.getFactoryParts()) {
            if (factoryPart.getFactoryId().equals(request.getSupplierId())) {

                final BigDecimal amount = factoryPart.getParts().values()
                        .stream()
                        .map(sp -> sp.stream()
                                .map(SpecificPart::getAmountPerItem)
                                .reduce(BigDecimal.ZERO, BigDecimal::add))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                factoriesAmount.put(request.getSupplierId(), amount);
                factoryPart.setTotalAmountOfFactory(factoriesAmount.get(request.getSupplierId()));
            }
        }
    }

    private int countQuantity(CartOrder cartOrder) {
        int totalQuantity = 0;
        if (cartOrder != null) {
            for (SpecificFactory factoryPart : cartOrder.getFactoryParts()) {
                final Map<PartType, List<SpecificPart>> parts = factoryPart.getParts();
                for (Map.Entry<PartType, List<SpecificPart>> sp : parts.entrySet()) {
                    totalQuantity = sp.getValue().stream().mapToInt(SpecificPart::getQuantity).sum();
                    SupplierPartModel supplierPartModel = new SupplierPartModel(sp.getKey(), factoryPart.getFactoryId(), cartOrder.getConsumerId());
                    factoriesTotalQuantityHolder.put(supplierPartModel, totalQuantity);
                }
            }
            return factoriesTotalQuantityHolder.entrySet().stream().filter(e -> e.getKey().getConsumerId().equals(cartOrder.getConsumerId())).mapToInt(Map.Entry::getValue).sum();
        }
        return totalQuantity;
    }

    private BigDecimal countAmount(CartOrder cartOrder) {
        for (SpecificFactory factoryPart : cartOrder.getFactoryParts()) {
            final Map<PartType, List<SpecificPart>> parts = factoryPart.getParts();
            for (Map.Entry<PartType, List<SpecificPart>> sp : parts.entrySet()) {
                BigDecimal sum = sp.getValue().stream().map(SpecificPart::getAmountPerItem).reduce(BigDecimal.ZERO, BigDecimal::add);
                SupplierPartModel supplierPartModel = new SupplierPartModel(sp.getKey(), factoryPart.getFactoryId(), cartOrder.getConsumerId());
                factoriesTotalAmountHolder.put(supplierPartModel, sum);
            }
        }
        return factoriesTotalAmountHolder.entrySet().stream().filter(e -> e.getKey().getConsumerId().equals(cartOrder.getConsumerId())).map(Map.Entry::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
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

    public ResponseEntity<?> getAllAvailableCarts() {
        final List<CartOrder> orders = new ArrayList<>(cartOrders.values());
        final ConsumersCartsResponse response = new CartMapper().mapFromConsumerCartToList(orders);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> clearConsumerCart(String consumerId) {
        final CartOwner cartOwner = new CartOwner(new ObjectId(consumerId));
        CartOrder cartOrder = cartOrders.get(cartOwner);
        if (cartOrder != null) {
            cartOrders.remove(cartOwner);
            return ResponseEntity.ok(new SuccessResponse("Successfully remove cart for consumer: " + cartOrder.getConsumerName()));
        }
        return ResponseEntity.badRequest().body(new ErrorResponse("Cannot find consumer cart, nothing happened"));
    }

    public ResponseEntity<?> removeFromCartItem(CartPartUpdate update) {
        final CartOwner cartOwner = new CartOwner(new ObjectId(update.getConsumerId()));
        CartOrder cartOrder = cartOrders.get(cartOwner);
        if (cartOrder != null) {
            SpecificPart partToRemove = cartOrder.getFactoryParts()
                    .stream()
                    .filter(sf -> sf.getFactoryId().equals(update.getSupplierId()))
                    .flatMap(sf -> sf.getParts().entrySet()
                            .stream()
                            .flatMap(e -> e.getValue().stream()
                                    .filter(sp -> sp.getPartId().equals(update.getPartId())))).findFirst().orElse(null);

            if (partToRemove != null) {
                final BigDecimal amountPerItem = partToRemove.getAmountPerItem();
                final int quantity = partToRemove.getQuantity();

                List<SpecificPart> updatedParts = cartOrder.getFactoryParts()
                        .stream()
                        .filter(sf -> sf.getFactoryId().equals(update.getSupplierId()))
                        .flatMap(sf -> sf.getParts().entrySet()
                                .stream()
                                .flatMap(e -> e.getValue().stream()
                                        .filter(sp -> !sp.getPartId().equals(update.getPartId())))).collect(Collectors.toList());

                cartOrder.getFactoryParts().stream().filter(sf -> sf.getFactoryId().equals(update.getSupplierId())).findFirst().ifPresent(sp -> sp.getParts().put(update.getPartType(), updatedParts));
                cartOrder.getFactoryParts().stream().filter(sf -> sf.getFactoryId().equals(update.getSupplierId())).findFirst().ifPresent(sf -> {
                    sf.setTotalAmountOfFactory(sf.getTotalAmountOfFactory().subtract(amountPerItem));
                    sf.setTotalQuantityOfFactory(sf.getTotalQuantityOfFactory() - quantity);
                });
                cartOrder.setTotalAmount(cartOrder.getTotalAmount().subtract(amountPerItem));
                cartOrder.setTotalQuantity(cartOrder.getTotalQuantity() - quantity);

                return ResponseEntity.ok().body(new SuccessResponse("Deleted part for consumer: " + update.getPartId()));

            }
        }
        return ResponseEntity.badRequest().body(new ErrorResponse("Cannot find and delete part: " + update.getConsumerId()));
    }
}

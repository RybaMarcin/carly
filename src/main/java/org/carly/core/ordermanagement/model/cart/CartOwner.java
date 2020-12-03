package org.carly.core.ordermanagement.model.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bson.types.ObjectId;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class CartOwner {
    private final ObjectId cartOwner;

    @Override
    public String toString() {
        return cartOwner.toHexString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartOwner)) return false;
        CartOwner cartOwner1 = (CartOwner) o;
        return Objects.equals(cartOwner, cartOwner1.cartOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartOwner);
    }
}

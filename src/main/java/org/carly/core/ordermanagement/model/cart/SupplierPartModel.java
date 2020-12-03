package org.carly.core.ordermanagement.model.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class SupplierPartModel {

    private PartType partType;
    private String factoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupplierPartModel)) return false;
        SupplierPartModel that = (SupplierPartModel) o;
        return partType == that.partType &&
                Objects.equals(factoryId, that.factoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partType, factoryId);
    }
}

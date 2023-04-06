package pl.dchruscinski.entity;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class CustomerProductKey {
    private Integer customerId;
    private Integer productId;

    public CustomerProductKey() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerProductKey that = (CustomerProductKey) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, productId);
    }

    @Override
    public String toString() {
        return "CustomerProductKey{" +
                "customerId=" + customerId +
                ", productId=" + productId +
                '}';
    }
}
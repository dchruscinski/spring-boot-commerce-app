package pl.dchruscinski.event;

import pl.dchruscinski.entity.Product;

import java.time.Clock;
import java.time.Instant;

public abstract class ProductAvailabilityEvent {

    public static ProductAvailabilityEvent changed (Product source) {
        return source.isAvailable() ? new ProductAvailable(source) : new ProductUnavailable(source);
    }

    public ProductAvailabilityEvent(int productId, Clock clock) {
        this.productId = productId;
        this.name = getClass().getSimpleName();
        this.updateMoment = Instant.now(clock);
    }

    private int productId;
    private String name;
    private Instant updateMoment;

    public int getProductId() {
        return productId;
    }

    public String getName() { return name; }

    public Instant getUpdateMoment() {
        return updateMoment;
    }

    @Override
    public String toString() {
        return "ProductAvailabilityEvent{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", updateMoment=" + updateMoment +
                '}';
    }
}
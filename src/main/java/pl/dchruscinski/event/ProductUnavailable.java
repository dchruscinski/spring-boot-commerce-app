package pl.dchruscinski.event;

import pl.dchruscinski.entity.Product;

import java.time.Clock;

public class ProductUnavailable extends ProductAvailabilityEvent {
    public ProductUnavailable(Product source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
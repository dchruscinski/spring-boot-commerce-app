package pl.dchruscinski.event;

import pl.dchruscinski.entity.Product;

import java.time.Clock;

public class ProductAvailable extends ProductAvailabilityEvent {
    public ProductAvailable(Product source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
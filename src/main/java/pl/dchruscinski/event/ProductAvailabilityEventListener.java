package pl.dchruscinski.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ProductAvailabilityEventListener {
    private static final Logger logger = LoggerFactory.getLogger(ProductAvailabilityEventListener.class);

    private final PersistedProductEventRepository productEventRepository;

    public ProductAvailabilityEventListener(PersistedProductEventRepository productEventRepository) {
        this.productEventRepository = productEventRepository;
    }

    @Async
    @EventListener
    void on(ProductAvailable event) {
        onChanged(event);
    }

    @Async
    @EventListener
    void on(ProductUnavailable event) {
        onChanged(event);
    }

    private void onChanged(ProductAvailabilityEvent event) {
        logger.info("onChanged(): event properties: {}", event);
        productEventRepository.save(new PersistedProductEvent(event));
    }
}
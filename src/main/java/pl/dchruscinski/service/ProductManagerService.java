package pl.dchruscinski.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pl.dchruscinski.entity.ProductManager;
import pl.dchruscinski.repository.ProductManagerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductManagerService {
    private final ProductManagerRepository productManagerRepository;

    public ProductManagerService(ProductManagerRepository productManagerRepository) {
        this.productManagerRepository = productManagerRepository;
    }

    public List<ProductManager> getProductManagers() {
        return productManagerRepository.findAll();
    }

    public boolean existsById(Integer id) {
        return productManagerRepository.existsById(id);
    }

    public ProductManager getProductManager(Integer id) {
        Optional<ProductManager> result = productManagerRepository.findById(id);

        ProductManager productManager = null;
        if (result.isPresent()) {
            productManager = result.get();
        }

        return productManager;
    }

    public ProductManager addProductManager(@RequestBody ProductManager productManager) {
        // In case of someone using API will pass ID in JSON - set it to "0" to save new item instead of updating existing.
        productManager.setId(0);

        return productManagerRepository.save(productManager);
    }

    public ProductManager updateProductManager(@RequestBody ProductManager productManager,
                                               @PathVariable Integer productManagerId) {

        productManager.setId(productManagerId);
        return productManagerRepository.save(productManager);
    }

    public void deleteProductManagerById(Integer id) {
        productManagerRepository.deleteById(id);
    }

    public Integer countProductManagers() {
        return productManagerRepository.countProductManagers();
    }
}
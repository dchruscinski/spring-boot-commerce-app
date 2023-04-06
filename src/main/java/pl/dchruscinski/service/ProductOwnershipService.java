package pl.dchruscinski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.dchruscinski.entity.ProductOwnership;
import pl.dchruscinski.repository.ProductOwnershipRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOwnershipService {
    private final ProductOwnershipRepository productOwnershipRepository;

    @Autowired
    public ProductOwnershipService(ProductOwnershipRepository productOwnershipRepository) {
        this.productOwnershipRepository = productOwnershipRepository;
    }

    public List<ProductOwnership> getProductOwnerships() {
        return productOwnershipRepository.findAll();
    }

    public boolean existsById(Integer customerId, Integer productId) {
        return productOwnershipRepository.existsById(customerId, productId);
    }

    public ProductOwnership getProductByCustomerIdAndProductId(Integer customerId, Integer productId) {
        Optional<ProductOwnership> result = productOwnershipRepository.findById(customerId, productId);

        ProductOwnership productOwnership = null;
        if (result.isPresent()) {
            productOwnership = result.get();
        }

        return productOwnership;
    }

    public ProductOwnership addProductOwnership(@RequestBody ProductOwnership productOwnership) {
        return productOwnershipRepository.save(productOwnership);
    }



    public void deleteProductOwnership(Integer customerId, Integer productId) {
        productOwnershipRepository.deleteById(customerId, productId);
    }

    public Integer countProductOwnerships() {
        return productOwnershipRepository.countProductOwnerships();
    }
}
package pl.dchruscinski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.dchruscinski.entity.Customer;
import pl.dchruscinski.entity.ProductPurchase;
import pl.dchruscinski.repository.ProductPurchaseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductPurchaseService {
    private final ProductPurchaseRepository productPurchaseRepository;

    @Autowired
    public ProductPurchaseService(ProductPurchaseRepository productPurchaseRepository) {
        this.productPurchaseRepository = productPurchaseRepository;
    }

    public List<ProductPurchase> getProductPurchases() {
        return productPurchaseRepository.findAll();
    }

    public List<ProductPurchase> getProductPurchasesByCustomer(Integer customerId) {
        return productPurchaseRepository.findAllByCustomer(customerId);
    }

    public List<ProductPurchase> getProductPurchasesByProduct(Integer productId) {
        return productPurchaseRepository.findAllByProduct(productId);
    }

    public ProductPurchase getProductPurchase(Integer purchaseId) {
        Optional<ProductPurchase> result = productPurchaseRepository.findById(purchaseId);

        ProductPurchase productPurchase = null;
        if (result.isPresent()) {
            productPurchase = result.get();
        }

        return productPurchase;
    }

    public boolean existsById(Integer purchaseId) {
        return productPurchaseRepository.existsById(purchaseId);
    }

    public ProductPurchase addProductPurchase(@RequestBody ProductPurchase productPurchase) {
        return productPurchaseRepository.save(productPurchase);
    }

    public ProductPurchase updateProductPurchase(@RequestBody ProductPurchase productPurchase, Integer purchaseId) {
        productPurchase.setId(purchaseId);
        return productPurchaseRepository.save(productPurchase);
    }

    public void deleteProductPurchase(Integer purchaseId) {
        productPurchaseRepository.deleteById(purchaseId);
    }

    public Integer countProductPurchases() {
        return productPurchaseRepository.countProductPurchases();
    }

    public Integer countProductPurchasesByCustomer(Integer customerId) {
        return productPurchaseRepository.countProductPurchasesByCustomer(customerId);
    }

    public Integer countProductPurchasesByProduct(Integer productId) {
        return productPurchaseRepository.countProductPurchasesByProduct(productId);
    }
}
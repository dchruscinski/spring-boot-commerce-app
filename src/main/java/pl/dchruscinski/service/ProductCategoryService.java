package pl.dchruscinski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pl.dchruscinski.entity.ProductCategory;
import pl.dchruscinski.repository.ProductCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategory> getProductCategories() {
        return productCategoryRepository.findAll();
    }

    public boolean existsById(Integer id) {
        return productCategoryRepository.existsById(id);
    }

    public ProductCategory getProductCategory(Integer id) {
        Optional<ProductCategory> result = productCategoryRepository.findById(id);

        ProductCategory productCategory = null;
        if (result.isPresent()) {
            productCategory = result.get();
        }

        return productCategory;
    }

    public ProductCategory addProductCategory(@RequestBody ProductCategory productCategory) {
        // In case of someone using API will pass ID in JSON - set it to "0" to save new item instead of updating existing.
        productCategory.setId(0);

        return productCategoryRepository.save(productCategory);
    }

    public ProductCategory updateProductCategory(@RequestBody ProductCategory productCategory,
                                                 @PathVariable Integer productCategoryId) {

        productCategory.setId(productCategoryId);
        return productCategoryRepository.save(productCategory);
    }

    public void deleteProductCategoryById(Integer id) {
        productCategoryRepository.deleteById(id);
    }

    public Integer countProductCategories() {
        return productCategoryRepository.countProductCategories();
    }
}
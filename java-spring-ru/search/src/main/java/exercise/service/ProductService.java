package exercise.service;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getFilteredProducts(ProductParamsDTO params, Pageable pageable) {
        return productRepository.findAll(ProductSpecification.filterByParams(params), pageable);
    }
}
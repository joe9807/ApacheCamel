package camel.service;

import camel.entity.Product;
import camel.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository products;

    @Autowired
    public ProductService(ProductRepository products) {
        this.products = products;
    }

    public Product findById(Integer id) {
        return products.findById(id).orElse(null);
    }

    public void save(Product product) {
        products.save(product);
    }

    public Iterable<Product> findAll(){
        return products.findAll();
    }
}
package camel.service;

import camel.entity.Discount;
import camel.entity.Product;
import camel.model.ProductEnum;
import camel.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class DiscountService {

    private final DiscountRepository discounts;
    private final ProductService productService;

    private final Random random = new Random();

    @Autowired
    public DiscountService(DiscountRepository discounts,
                           ProductService productService) {
        this.discounts = discounts;
        this.productService = productService;
    }

    public Discount makeDiscount() {
        // create a discount
        Discount discount = new Discount();
        int discountRate = random.nextInt(100);
        discount.setAmount(discountRate);

        // select random product
        int productId = random.nextInt(4) + 1;
        Product product = productService.findById(productId);
        if (product == null) {
            product = new Product();
            product.setId(productId);
            product.setName(ProductEnum.getById(productId).getName());
            product.setPrice(100);
            product.setDiscounted(0);
        }

        // set the discount to product and save
        product.setDiscounted(product.getPrice() - (discountRate * product.getPrice() / 100));
        productService.save(product);

        discount.setProduct(product);
        discounts.save(discount);
        return discount;
    }

    public Discount findDiscount(Integer id) {
        Optional<Discount> discount = discounts.findById(id);
        if (!discount.isPresent()) {
            throw new IllegalStateException("Discount could not found for given id:" + id);
        }
        return discount.get();
    }

    public Iterable<Discount> findAll() {
        return discounts.findAll();
    }
}
package camel.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "products")
@NamedQuery(name = "listDiscountedProducts", query = "select product from Product product where product.discounted != 0")
public class Product {

    @Id
    private int id;

    private String name;

    private Integer price;

    private Integer discounted;

    // Getters and setters
}
package camel.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue
    private int id;

    private Integer amount;

    @OneToOne
    private Product product;

    // Getters and setters
}
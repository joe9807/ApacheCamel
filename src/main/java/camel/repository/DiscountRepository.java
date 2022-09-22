package camel.repository;

import camel.entity.Discount;
import org.springframework.data.repository.CrudRepository;

public interface DiscountRepository extends CrudRepository<Discount, Integer> {}

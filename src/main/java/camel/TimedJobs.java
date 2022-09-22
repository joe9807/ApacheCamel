package camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
class TimedJobs extends RouteBuilder {

    @Override
    public void configure() {
        from("timer:?delay=1000&period=2000")
                .bean("discountService", "makeDiscount")
                .log("Created ${body.amount} discount for ${body.product.name}");

        from("jpa:?namedQuery=listDiscountedProducts&delay=3000&consumeDelete=false")
                .log("========Discounted product ${body.name}. Price dropped from ${body.price} to ${body.discounted}");
    }
}
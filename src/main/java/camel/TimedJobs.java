package camel;

import camel.service.DiscountService;
import camel.service.ProductService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
class TimedJobs extends RouteBuilder {

    @Override
    public void configure() {
        from("timer:timer?delay=1000&period=2000")
                .bean("discountService", "makeDiscount")
                .log("Created ${body.amount} discount for ${body.product.name}").routeId("create");

        from("jpa:jpa?namedQuery=listDiscountedProducts&delay=3000&consumeDelete=false")
                .log("========Discounted product ${body.name}. Price dropped from ${body.price} to ${body.discounted}").routeId("print");

        restConfiguration()
                .contextPath("/market")
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "JAVA DEV JOURNAL REST API")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiContextRouteId("doc-api")
                .port(8080)
                .bindingMode(RestBindingMode.json);

        rest("/products").description("Details of products")
                .get("/").description("List of all products")
                .route().routeId("products-api")
                .bean(ProductService.class, "findAll")
                .endRest()
                .get("discounts/{id}").description("Discount of a product")
                .route().routeId("discount-api")
                .bean(DiscountService.class, "findDiscount(${header.id})");

        rest("/discounts").description("Details of discounts")
                .get("/").description("List of all discounts")
                .route().routeId("discounts-api")
                .bean(DiscountService.class, "findAll");
    }
}
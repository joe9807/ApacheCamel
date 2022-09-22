package camel.model;

import java.util.Arrays;

public enum ProductEnum {
    FIRST(1, "Shoes"), SECOND(2, "Books"), THIRD(3, "Cloths"), FOURTH(4, "Gloves");

    private final int id;
    private final String name;

    ProductEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ProductEnum getById(int id){
        return Arrays.stream(values()).filter(p -> p.id == id).findAny().orElse(null);
    }

    public String getName() {
        return name;
    }
}
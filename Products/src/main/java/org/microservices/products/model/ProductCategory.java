package org.microservices.products.model;

import lombok.Getter;

@Getter
public enum ProductCategory {
    HOME(1, "For home"),
    OFFICE(2, "For office"),
    GARDEN(3, "For garden"),
    GARAGE(4, "For garage");

    private final int id;
    private final String name;

    ProductCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }


}

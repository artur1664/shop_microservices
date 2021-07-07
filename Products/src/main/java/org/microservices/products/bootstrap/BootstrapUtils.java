package org.microservices.products.bootstrap;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.microservices.products.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BootstrapUtils {
    public static List<Product> prepareSomeProducts() {
        List<Product> products = new ArrayList<>();

        Manufacturer man1 = Manufacturer.builder()
                .nip("123456789")
                .name("IKEA")
                .uuid(UUID.randomUUID())
                .address(Address.builder()
                        .number("23A")
                        .street("Past is last")
                        .build())
                .build();
        Manufacturer man2 = Manufacturer.builder()
                .nip("987654321")
                .name("BLACK RED WHITE")
                .uuid(UUID.randomUUID())
                .address(Address.builder()
                        .number("58B")
                        .street("Simple street")
                        .build())
                .build();
        Manufacturer man3 = Manufacturer.builder()
                .nip("258963147")
                .name("VOX")
                .uuid(UUID.randomUUID())
                .address(Address.builder()
                        .number("6C")
                        .street("Cassandra")
                        .build())
                .build();

        for (int i = 0; i < 100; i++) {
            products.add(
                    Product.builder()
                            .name("Name " + RandomStringUtils.randomAlphabetic(10))
                            .productUuid(UUID.randomUUID())
                            .index(RandomUtils.nextInt(1000))
                            .description("Some description " + RandomStringUtils.randomAlphabetic(10) + " with random text " + RandomStringUtils.randomAlphabetic(10) + " somewhere in line")
                            .price(RandomUtils.nextFloat() * 100)
                            .productCategory(i < 25 ? ProductCategory.HOME : i > 25 && i < 50 ? ProductCategory.OFFICE : i > 50 && i < 75 ? ProductCategory.GARDEN : ProductCategory.GARAGE)
                            .productDetails(ProductDetails.builder()
                                    .manufacturer(i < 25 ? man1 : i > 25 && i < 50 ? man2 : i > 50 && i < 75 ? man3 : man2)
                                    .uuid(UUID.randomUUID())
                                    .moreInfo(i < 25 ? "For personal use only" : i > 25 && i < 50 ? "Only for professionals" : i > 50 && i < 75 ? "For all people" : "No details")
                                    .build())
                            .build()
            );
        }
        return products;
    }
}

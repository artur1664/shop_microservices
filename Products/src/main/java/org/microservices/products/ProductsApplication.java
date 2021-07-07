package org.microservices.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProductsApplication {

    //todo test remove me aaaa

    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class, args);
    }

}

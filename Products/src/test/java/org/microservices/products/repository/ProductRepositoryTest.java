package org.microservices.products.repository;

import org.junit.jupiter.api.Test;
import org.microservices.products.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("integration")
@DataMongoTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveOperation(){
        //given
        Product product = new Product();
        product.setDescription("some simple product");
        product.setName("some simple name");
        product.setPrice(new BigDecimal("4.99"));

        //when
        Product result = productRepository.save(product).block();

        //then
        assertNotNull(result);
        assertNotNull(result.get_id());

    }

}

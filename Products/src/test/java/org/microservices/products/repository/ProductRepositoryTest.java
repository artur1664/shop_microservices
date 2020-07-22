package org.microservices.products.repository;

import org.junit.jupiter.api.Test;
import org.microservices.products.repository.mongo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@DataMongoTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveOperation(){
        //given
//        Product product = new Product();
//        product.setDescription("some simple product");
//        product.setName("some simple name");
//        product.setPrice(new BigDecimal("4.99"));
//
//        //when
//        Product result = productRepository.save(product).block();
//
//        //then
//        assertNotNull(result);

    }

}

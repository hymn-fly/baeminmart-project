package com.example.springproject.repository;

import com.example.springproject.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    private final Product product = new Product(UUID.randomUUID(), "test", 1000);
    private final Product product2 = new Product(UUID.randomUUID(), "test2", 2000);

    @BeforeEach
    void insert(){
        productRepository.save(product);
        productRepository.save(product2);
    }

    @Test
    @DisplayName("상품 저장 테스트")
    void save() {
        Product product = new Product(UUID.randomUUID(), "save-test", 1000);
        productRepository.save(product);

        Optional<Product> maybeProduct = productRepository.findById(product.getId());

        assertThat(maybeProduct.isPresent()).isTrue();
        assertThat(maybeProduct.get()).isEqualTo(product);
    }

    @Test
    @DisplayName("모두 조회 테스트")
    void findAll() {
        List<Product> products = productRepository.findAll();

        assertThat(products).containsExactlyInAnyOrder(product, product2);
    }

    @Test
    @DisplayName("id로 삭제 테스트")
    void deleteById() {
        productRepository.deleteById(product.getId());

        List<Product> products = productRepository.findAll();

        assertThat(products).containsExactly(product2);
    }
}
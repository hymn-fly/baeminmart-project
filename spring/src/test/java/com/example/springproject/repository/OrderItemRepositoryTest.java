package com.example.springproject.repository;

import com.example.springproject.domain.Order;
import com.example.springproject.domain.*;
import lombok.val;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderItemRepositoryTest {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    private final Order order = new Order(UUID.randomUUID(), OrderStatus.ACCEPTED, LocalDateTime.now(), "test-order", new Email("test@gmail.com"), "서울시 관악구");
    private final Product product =  new Product(UUID.randomUUID(), "test-product", 1000);
    private final Product product2 = new Product(UUID.randomUUID(), "test-product2", 1000);
    private final Product product3 = new Product(UUID.randomUUID(), "test-product3", 1000);

    private final OrderItem item1 = new OrderItem(UUID.randomUUID(), order.getId(), product.getId(), 2);
    private final OrderItem item2 = new OrderItem(UUID.randomUUID(), order.getId(), product2.getId(), 1);

    @BeforeAll
    void setup(){
        orderRepository.save(order);
        productRepository.save(product);
        productRepository.save(product2);
    }

    @AfterAll
    void deleteAll(){
        orderRepository.deleteById(order.getId());
        productRepository.deleteById(product.getId());
        productRepository.deleteById(product2.getId());
    }

    @BeforeEach
    void insert(){
        orderItemRepository.save(item1);
        orderItemRepository.save(item2);
    }

    @Test
    void save() {
        val item3 = new OrderItem(UUID.randomUUID(), order.getId(), product3.getId(), 1);
        productRepository.save(product3);
        orderItemRepository.save(item3);

        val items = orderItemRepository.findAll();

        assertThat(items).containsExactlyInAnyOrder(item1, item2, item3);
    }

    @Test
    void findAll() {
        val items = orderItemRepository.findAll();

        assertThat(items).containsExactlyInAnyOrder(item1, item2);
    }

    @Test
    void findById() {
        val item = orderItemRepository.findById(item1.getId());

        assertThat(item.isPresent()).isTrue();
        assertThat(item.get()).isEqualTo(item1);
    }

    @Test
    void findByOrderId() {
        val items = orderItemRepository.findByOrderId(order.getId());

        assertThat(items).containsExactlyInAnyOrder(item1, item2);
    }

    @Test
    void findByProductId() {
        val items = orderItemRepository.findByProductId(product.getId());

        assertThat(items).containsExactly(item1);
    }

    @Test
    void deleteById() {
        orderItemRepository.deleteById(item1.getId());

        val items = orderItemRepository.findAll();

        assertThat(items).containsExactly(item2);
    }
}
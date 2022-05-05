package com.example.springproject.repository;

import com.example.springproject.domain.Email;
import com.example.springproject.domain.Order;
import com.example.springproject.domain.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    private final Order order = new Order(UUID.randomUUID(), OrderStatus.ACCEPTED, LocalDateTime.now(), "test-order", new Email("test@gmail.com"), "서울시 행운동");
    private final Order order2 = new Order(UUID.randomUUID(), OrderStatus.ACCEPTED, LocalDateTime.now(), "test-order2", new Email("test2@gmail.com"), "서울시 청룡동");

    @BeforeEach
    void insert(){
        orderRepository.save(order);
        orderRepository.save(order2);
    }

    @Test
    @DisplayName("상품 저장 테스트")
    void save() {
        Order order3 = new Order(UUID.randomUUID(), OrderStatus.ACCEPTED, LocalDateTime.now(), "test-order3", new Email("test3@gmail.com"), "서울시 소공동");
        orderRepository.save(order3);

        Optional<Order> maybeOrder = orderRepository.findById(order3.getId());

        assertThat(maybeOrder.isPresent()).isTrue();
        assertThat(maybeOrder.get()).isEqualTo(order3);
    }

    @Test
    @DisplayName("모두 조회 테스트")
    void findAll() {
        List<Order> orders = orderRepository.findAll();

        assertThat(orders).containsExactlyInAnyOrder(order, order2);
    }

    @Test
    @DisplayName("id로 삭제 테스트")
    void deleteById() {
        orderRepository.deleteById(order.getId());

        List<Order> orders = orderRepository.findAll();

        assertThat(orders).containsExactly(order2);
    }
}
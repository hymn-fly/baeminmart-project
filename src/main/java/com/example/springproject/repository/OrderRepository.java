package com.example.springproject.repository;

import com.example.springproject.domain.Email;
import com.example.springproject.domain.Order;
import com.example.springproject.domain.OrderStatus;
import com.example.springproject.domain.Product;
import com.example.springproject.utils.Utils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.val;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

@Repository
public class OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Order save(Order order){
        int update = jdbcTemplate.update("INSERT INTO orders (id, order_status, created_at, customer_name, email, address) " +
                "values (?, ?, ?, ?, ?, ?)",
                Utils.UuidToBytes(order.getId()),
                order.getOrderStatus().name(),
                order.getCreatedAt(),
                order.getCustomerName(),
                order.getEmail().getEmail(),
                order.getAddress());

        checkArgument(update == 1, "저장되지 않았습니다");

        return order;
    }

    public List<Order> findAll(){
        return jdbcTemplate.query("SELECT * FROM orders", this::rowMapper);
    }

    public Optional<Order> findById(UUID id){
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM orders WHERE id = ?", this::rowMapper, Utils.UuidToBytes(id)));
        }
        catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public void deleteById(UUID id){
        jdbcTemplate.update("DELETE FROM orders WHERE id = ?", Utils.UuidToBytes(id));
    }

    public Order rowMapper(ResultSet rs, int rowNum) throws SQLException {
        val id = Utils.BytesToUuid(rs.getBytes("id"));
        val orderStatus = OrderStatus.valueOf(rs.getString("order_status"));
        val createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        val customerName = rs.getString("customer_name");
        val email = rs.getString("email");
        val address = rs.getString("address");

        return new Order(id, orderStatus, createdAt, customerName, new Email(email), address);
    }

}

package com.example.springproject.repository;

import com.example.springproject.domain.Email;
import com.example.springproject.domain.Order;
import com.example.springproject.domain.OrderItem;
import com.example.springproject.domain.OrderStatus;
import com.example.springproject.utils.Utils;
import lombok.val;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

@Repository
public class OrderItemRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public OrderItem save(OrderItem orderItem){
        val update = jdbcTemplate.update("INSERT INTO order_item (id, order_id, product_id, product_count) " +
                        "values (?, ?, ?, ?)",
                Utils.UuidToBytes(orderItem.getId()),
                Utils.UuidToBytes(orderItem.getOrderId()),
                Utils.UuidToBytes(orderItem.getProductId()),
                orderItem.getProductCount()
                );

        checkArgument(update == 1, "저장되지 않았습니다");

        return orderItem;
    }

    public List<OrderItem> findAll(){
        return jdbcTemplate.query("SELECT * FROM order_item", this::rowMapper);
    }

    public Optional<OrderItem> findById(UUID id){
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM order_item WHERE id = ?", this::rowMapper, Utils.UuidToBytes(id)));
        }
        catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public List<OrderItem> findByOrderId(UUID orderId){
        return jdbcTemplate.query("SELECT * FROM order_item WHERE order_id = ?", this::rowMapper, Utils.UuidToBytes(orderId));
    }

    public List<OrderItem> findByProductId(UUID productId){
        return jdbcTemplate.query("SELECT * FROM order_item WHERE product_id = ?", this::rowMapper, Utils.UuidToBytes(productId));
    }

    public void deleteById(UUID id){
        jdbcTemplate.update("DELETE FROM order_item WHERE id = ?", Utils.UuidToBytes(id));
    }

    public OrderItem rowMapper(ResultSet rs, int rowNum) throws SQLException {
        val id = Utils.BytesToUuid(rs.getBytes("id"));
        val orderId = Utils.BytesToUuid(rs.getBytes("order_id"));
        val productId = Utils.BytesToUuid(rs.getBytes("product_id"));
        val productCount = rs.getLong("product_count");

        return new OrderItem(id, orderId, productId, productCount);
    }

}

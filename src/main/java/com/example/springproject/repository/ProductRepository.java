package com.example.springproject.repository;

import com.example.springproject.domain.Product;
import com.example.springproject.utils.Utils;
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
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product save(Product product){
        int update = jdbcTemplate.update("INSERT INTO product (id, name, price) values (?, ?, ?)", Utils.UuidToBytes(product.getId()), product.getName(), product.getPrice());
        checkArgument(update == 1, "저장되지 않았습니다");
        return product;
    }

    public List<Product> findAll(){
        return jdbcTemplate.query("SELECT * FROM product", this::rowMapper);
    }

    public Optional<Product> findById(UUID id){
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM product WHERE id = ?", this::rowMapper, Utils.UuidToBytes(id)));
        }
        catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public void deleteById(UUID id){
        jdbcTemplate.update("DELETE FROM product WHERE id = ?", Utils.UuidToBytes(id));
    }

    public Product rowMapper(ResultSet rs, int rowNum) throws SQLException {
        UUID id = Utils.BytesToUuid(rs.getBytes("id"));
        String name = rs.getString("name");
        long price = rs.getLong("price");

        return new Product(id, name, price);
    }
}

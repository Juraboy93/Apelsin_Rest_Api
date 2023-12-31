package com.example.apelsin_rest_api.repositary;

import com.example.apelsin_rest_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositary  extends JpaRepository<Product, Integer> {
    List<Product> findAllByActiveTrue();


    @Query(value = "select * from product join detail d on product.id=d.product_id where quantity>=10;", nativeQuery = true)
    List<Product>getHigh_demand_products();

    @Query(value = "select *\n" + "from product\n" + "join detail d on product.id = d.product_id_id\n" +
            "where d.quantity = (select avg(d1.quantity) from detail as d1) and d.quantity>8;", nativeQuery = true)
    List<Product> getBulk_products();
}

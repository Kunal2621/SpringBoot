package com.kp.sprinboot.productrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kp.sprinboot.ProductEntity.ProductEntity;

public interface ProductRepository extends JpaRepository <ProductEntity,Long> {

}

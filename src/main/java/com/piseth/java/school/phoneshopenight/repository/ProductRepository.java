package com.piseth.java.school.phoneshopenight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phoneshopenight.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,JpaSpecificationExecutor<Product> {

}

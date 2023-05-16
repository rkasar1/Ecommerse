package com.infosys.ecomerse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infosys.ecomerse.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findProductByProductName(String name);

	List<Product> findProductByProductNameAndPrice(String name, Long price);

	@Query("SELECT p FROM Product p JOIN Brand pc ON p.id = pc.id WHERE pc.name = :name")
	List<Product> findProductByBrand(String name);

	@Query("SELECT p FROM Product p JOIN ProductCategory pc ON p.id = pc.id WHERE pc.categoryName = :categoryName")
	List<Product> findProductByCategory(String categoryName);

}

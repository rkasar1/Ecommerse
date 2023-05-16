package com.infosys.ecomerse.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.infosys.ecomerse.entity.Brand;
import com.infosys.ecomerse.entity.ImageModel;
import com.infosys.ecomerse.entity.Product;
import com.infosys.ecomerse.entity.ProductCategory;

public interface ProductService {

	
	public Product addProduct(Product product,MultipartFile[] file);
	
	
	public Set<ImageModel> uploadImage(MultipartFile[] file) throws IOException;
	
	public List<Product> getProducts();
	
	public void deleteProduct(Long id);
	
	public Product updateProduct(Product product);
	
	public List<Product> searchProductByName(String name) throws Exception;
	
	public List<Product> searchProductByNameAndPrice(String name,long price);
	
	public List<Product> searchProductByBrand(String name);
	public List<Product> searchProductByCategoryName(String categoryName);


	public List<Product> searchProductByCategory(String category);
}

package com.infosys.ecomerse.service.serviceimpl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.infosys.ecomerse.entity.Brand;
import com.infosys.ecomerse.entity.ImageModel;
import com.infosys.ecomerse.entity.Product;
import com.infosys.ecomerse.entity.ProductCategory;
import com.infosys.ecomerse.repository.ProductRepository;
import com.infosys.ecomerse.service.ProductService;

import io.swagger.models.Path;
import io.swagger.v3.oas.models.Paths;

@Service
public class ProductiServicempl implements ProductService {

	@Autowired
	private ProductRepository productRepository;


	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public void deleteProduct(Long id) {

		productRepository.deleteById(id);
	}

	@Override

	public Product updateProduct(Product product) {
		Product updatedProduct = productRepository.findById(product.getId()).get();
		updatedProduct.setProductName(product.getProductName());
		updatedProduct.setProductDescription(product.getProductDescription());
		return productRepository.save(updatedProduct);

	}

	@Override
	public List<Product> searchProductByName(String name) throws Exception {

		List<Product> products = productRepository.findProductByProductName(name);
		return products;
	}

	@Override
	public List<Product> searchProductByNameAndPrice(String name, long price) {

		return productRepository.findProductByProductNameAndPrice(name, price);
	}

	@Override
	public List<Product> searchProductByBrand(String name) {
//		// TODO Auto-generated method stub return
		return productRepository.findProductByBrand(name);
	}

	@Override
	public List<Product> searchProductByCategory(String category) {
		// TODO Auto-generated method stub
		return productRepository.findProductByCategory(category);
	}

	@Override
	public List<Product> searchProductByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ImageModel> uploadImage(MultipartFile[] file) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product addProduct(Product product, MultipartFile[] file) {
		Product productDetails = new Product();
		productDetails.setProductName(product.getProductName());
		productDetails.setProductDescription(product.getProductDescription());
		productDetails.setPrice(product.getPrice());

		ProductCategory categories = new ProductCategory();
		categories.setCategoryName(product.getCategory().getCategoryName());

		Brand brands = new Brand();
		brands.setName(product.getBrand().getName());

		Set<ImageModel> imageModels = new HashSet<>();

		for (MultipartFile files : file) {
			try {
				ImageModel imageModel = new ImageModel(

						files.getOriginalFilename(), files.getContentType(), files.getBytes()

				);

				productDetails.setProductImages(imageModels);

			} catch (Exception e) {
				e.getMessage();
			}

		}
		return productRepository.save(productDetails);
	}

}
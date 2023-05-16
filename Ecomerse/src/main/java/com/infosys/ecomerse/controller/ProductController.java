package com.infosys.ecomerse.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.infosys.ecomerse.entity.Product;
import com.infosys.ecomerse.repository.ProductRepository;
import com.infosys.ecomerse.service.ProductService;

/**
 * 
 * @author Rashmi kasar
 *
 */
@RequestMapping("/products")
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	/**
	 * 
	 * to add new Product
	 * 
	 * @param product
	 * @param file
	 * @return Product
	 */

	@PostMapping(value = { "/addNewproduct" }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public Product addNewProduct(@RequestBody Product product,

			@RequestPart MultipartFile[] file) {
		return productService.addProduct(product, file);
	}

	/**
	 * to get All product details
	 * 
	 * @return List<Product>
	 */

	@GetMapping({ "/getAllProducts" })
	public List<Product> getAllProducts() {
		return productService.getProducts();
	}

	/**
	 * to delete product
	 * 
	 * @param id
	 */

	@DeleteMapping({ "/deleteProductDetails/{productId}" })
	public ResponseEntity<?> deleteProductDetail(@PathVariable("productId") Long id) {

		productService.deleteProduct(id);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Product product = productRepository.findById(id).get();
			map.put("status", 200);
			map.put("data", product);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception ex) {
			map.clear();
			map.put("status", 404);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);

		}
	}

	/**
	 * 
	 * to update product details
	 * 
	 * @param Id
	 * @param product
	 * @return
	 */

	@PutMapping({ "/updateProductDetails" })
	public ResponseEntity<Product> updateProductDetails(@RequestParam Long Id, @RequestBody Product product) {

		product.setId(Id);
		Product updatedProduct = productService.updateProduct(product);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	/**
	 * 
	 * search product by product name
	 * 
	 * @param name
	 * @return List<Product>
	 * @throws Exception
	 */

	@GetMapping({ "/searchProductByName" })
	public ResponseEntity<?> searchProductByName(@RequestParam String name) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Product> findProductByName = productRepository.findProductByProductName(name);
		if (!findProductByName.isEmpty()) {
			map.put("status", 200);
			map.put("data", findProductByName);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data is not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * search product by name and price
	 * 
	 * @param name
	 * @param price
	 * @return List<Product>
	 */

	@GetMapping({ "/searchProductByNameAndPrice" })
	public List<Product> searchProductByNameAndPrice(@RequestParam("name") String name, @RequestParam long price) {
		return productService.searchProductByNameAndPrice(name, price);

	}

	/**
	 * 
	 * search product by name and brand
	 * 
	 * @param name
	 * @param brand
	 * @return List<Product>
	 */

	@GetMapping({ "/searchProductByNameAndBrand" })
	public List<Product> searchProductByBrand(@RequestParam("name") String name) {

		return productService.searchProductByBrand(name);
	}

	@GetMapping({ "/searchProductByCategoryName" })
	public List<Product> searchProductByCategoryName(@RequestParam String categoryName) {
		return productService.searchProductByCategory(categoryName);
	}
}

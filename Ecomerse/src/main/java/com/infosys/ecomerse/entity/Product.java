package com.infosys.ecomerse.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String productName;

	private String productDescription;

	private Long price;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private ProductCategory category;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

	@JoinTable(name = "product_images", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = {

			@JoinColumn(name = "image_id") }

	)
	private Set<ImageModel> productImages;

	
	 
	@JsonManagedReference
	  @ManyToOne()
	  @JoinColumn(name = "brand_id", nullable = false)
	  private Brand brand;
	 
}

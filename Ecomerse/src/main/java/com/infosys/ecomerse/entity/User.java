package com.infosys.ecomerse.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
	@Table(name = "user")
	public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "user_id")
		private Long userId;
		@Column(name = "first_name")
		private String firstName;
		@Column(name = "last_name")
		private String lastName;
		@Column(name = "email", unique = true)
		private String email;
		@Column(name = "mobile_number")
		private String mobileNumber;
		@Column(name = "password")
		private String password;
		
		  @OneToMany( cascade = CascadeType.ALL)
		  @Column(name = "roles")
		   private Set<Role> roles;
		 
		@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
		@Column(name = "address")
		private Set<Address> address;
}

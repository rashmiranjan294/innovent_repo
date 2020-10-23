package com.app.innoventes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name="address")
@Data
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(min=5)
	private String addrLineOne;
	
	private String addrLineTwo;
	
	@NotBlank
	private String city;

	public Address(long id, @NotBlank @Size(min = 5) String addrLineOne, String addrLineTwo, @NotBlank String city) {
		super();
		this.id = id;
		this.addrLineOne = addrLineOne;
		this.addrLineTwo = addrLineTwo;
		this.city = city;
	}

	public Address() {
		super();
	}
	
	

}

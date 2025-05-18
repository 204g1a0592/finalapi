package com.edureka.training.DTO;

import org.springframework.stereotype.Component;

import com.edureka.training.entity.Product;
import com.edureka.training.entity.Vendor;

@Component
public class VendorProductDTO {

	private Product product;
	private Vendor vendor;

	public VendorProductDTO() {
	}

	public VendorProductDTO(Product product, Vendor vendor) {
		super();
		this.product = product;
		this.vendor = vendor;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	
}
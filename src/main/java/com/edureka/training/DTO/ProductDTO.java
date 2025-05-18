package com.edureka.training.DTO;

import java.time.LocalDate;

public class ProductDTO {
	    private Long vendorId;
	    public ProductDTO(Long vendorId, String vendorname, String productName, String description, double price,
				int quantity) {
			super();
			this.vendorId = vendorId;
			this.vendorname = vendorname;
			this.productName = productName;
			this.description = description;
			this.price = price;
			this.quantity = quantity;
		}
		public ProductDTO() {
			// TODO Auto-generated constructor stub
		}
		private String vendorname;
	    private String productName;
	    private String description;
	   
		 private  double price;
		
	    private int quantity;
	    public Long getVendorId() {
			return vendorId;
		}
		public void setVendorId(Long vendorId) {
			this.vendorId = vendorId;
		}
		public String getVendorname() {
			return vendorname;
		}
		public void setVendorname(String vendorname) {
			this.vendorname = vendorname;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	
		
}

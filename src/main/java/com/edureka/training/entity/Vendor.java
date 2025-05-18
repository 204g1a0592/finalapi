package com.edureka.training.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorid;

    @Column(unique=true)
    private String vendorname;
    
   
    @Column(unique=true)
    private String email;

	private String phone;

	private String address;
	 
   
	

    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "productid") // Foreign key column in Vendor table
  
    @JsonIgnoreProperties("vendor")
    private Product product;

	public Long getVendorid() {
		return vendorid;
	}

	public void setVendorid(Long vendorid) {
		this.vendorid = vendorid;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}
	  public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	   

	   
	@OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
	 @JsonIgnore
    private List<PurchaseInvoice> invoices;
    // getters and setters

	public List<PurchaseInvoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<PurchaseInvoice> invoices) {
		this.invoices = invoices;
	}
}

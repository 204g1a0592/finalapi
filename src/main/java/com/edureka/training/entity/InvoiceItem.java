package com.edureka.training.entity;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private double priceAtPurchase;
    private int quantity;

    // Belongs to one invoice
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @JsonBackReference
    private UserInvoice invoice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPriceAtPurchase() {
		return priceAtPurchase;
	}

	public void setPriceAtPurchase(double priceAtPurchase) {
		this.priceAtPurchase = priceAtPurchase;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public UserInvoice getInvoice() {
		return invoice;
	}

	public void setInvoice(UserInvoice invoice) {
		this.invoice = invoice;
	}

    // Getters/Setters...
}


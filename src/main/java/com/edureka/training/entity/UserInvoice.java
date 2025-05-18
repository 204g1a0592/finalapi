package com.edureka.training.entity;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

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

public class UserInvoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invoiceid;


	private LocalDateTime purchaseDate;
	private double totalAmount;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserCredential user;

	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
	private List<InvoiceItem> items = new ArrayList<>();

	public Long getId() {
		return invoiceid;
	}

	public void setId(Long id) {
		this.invoiceid = id;
	}



	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public UserCredential getUser() {
		return user;
	}

	public void setUser(UserCredential user) {
		this.user = user;
	}

	public List<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}

}

package com.edureka.training.entity;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "user_credentials")
public class UserCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;


    private String address;
    private String password;
    
    //added for cart
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
   
    private Cart cart;

    public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<UserInvoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<UserInvoice> invoices) {
		this.invoices = invoices;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// One user can have many invoices
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)

    private List<UserInvoice> invoices = new ArrayList<>();

    // Constructors
    public UserCredential() {}

    public UserCredential(String name, String email, String phone, String password, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

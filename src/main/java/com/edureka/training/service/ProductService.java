package com.edureka.training.service;

import com.edureka.training.DTO.ProductDTO;
import com.edureka.training.entity.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edureka.training.entity.Vendor;
import com.edureka.training.repository.ProductRepository;
import com.edureka.training.repository.VendorRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productrepo;
	@Autowired
	VendorRepository vendorrepo;
	public Product add_product(Product product) {
		
		return productrepo.save(product);
		
	}

	    public List<Vendor> getAllVendors() {
	        return vendorrepo.findAll();  // Assuming JPA repository
	    }
	

	
	
	public void addProductWithVendor(Product product, Long vendorId) {
	    Vendor vendor = vendorrepo.findById(vendorId)
	        .orElseThrow(() -> new RuntimeException("Vendor not found"));

	    // Save product first to generate ID
	    Product savedProduct = productrepo.save(product);

	    // Link both sides
	    vendor.setProduct(savedProduct);
	    savedProduct.setVendor(vendor);

	    // Save vendor to update foreign key
	    vendorrepo.save(vendor);
	}
	public List<ProductDTO> getAllPurchases() {
	    List<Vendor> vendors = vendorrepo.findAll();
	    List<Product> products = productrepo.findAll();
	    List<ProductDTO> dtos = new ArrayList<>();

	   for(Vendor v:vendors) {
		   ProductDTO dto=new ProductDTO();
		   dto.setVendorId(v.getVendorid());
		   dto.setVendorname(v.getVendorname());
		  
		   Product product = productrepo.findById(v.getVendorid()).orElse(null);
		   System.out.println(product.getProductname());
		   dto.setProductName(product.getProductname());
		   System.out.println(product.getDescription());
		   dto.setDescription(product.getDescription());
		   System.out.println(product.getPrice());
		   dto.setPrice(product.getPrice());
		   dto.setQuantity(product.getQuantity());
		  dtos.add(dto);
				
	   
	   }

	    return dtos;
	}

	public Object fetechAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Product save(Product product) {
		// TODO Auto-generated method stub
		
		
		return productrepo.save(product);
		
	}

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productrepo.findAll();
	}
	public Product getProductById(long id) {
		// TODO Auto-generated method stub
		return productrepo.getById(id);
	}
}

        
	
	

    




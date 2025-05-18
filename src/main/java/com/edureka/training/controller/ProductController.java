package com.edureka.training.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edureka.training.DTO.ProductDTO;
import com.edureka.training.entity.Product;
import com.edureka.training.entity.PurchaseInvoice;
import com.edureka.training.entity.Vendor;
import com.edureka.training.repository.ProductRepository;
import com.edureka.training.repository.PurchaseInvoiceRepository;
import com.edureka.training.repository.VendorRepository;
import com.edureka.training.service.ProductService;
import com.edureka.training.service.VendorService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@RequestMapping("/apiproduct")
public class ProductController {
	@Autowired
	ProductService service;
	@Autowired
	ProductRepository productrepo;
	@Autowired
	PurchaseInvoiceRepository purchaserepo;
	@Autowired
	VendorRepository vendorrepo;
	 @PostMapping("/addproduct")
     public ResponseEntity<String> addingproduct(@RequestBody Product product, @RequestParam Long vendorId) {
      System.out.println(product.getProductname());
      service.addProductWithVendor(product, vendorId);
	     return ResponseEntity.ok("added successfully");
     }
	 @GetMapping("/allproducts")
	    public List<Product> getAllProducts() {
	        return service.getAllProducts();
	    }
	 @GetMapping("/displayvendor")
	 public ResponseEntity<Object> addingvendor() {
		 return ResponseEntity.ok(service.fetechAll());
	 
	 }
	 @PostMapping("/updatepriceandqty")
	 public ResponseEntity<String> updatePriceAndQuantity(@RequestBody Product product) {
	     Optional<Product> optional = productrepo.findById(product.getProductid());
	     if (optional.isPresent()) {
	         Product existing = optional.get();
	         existing.setPrice((product.getPrice()+existing.getPrice())/2);
	         existing.setQuantity(product.getQuantity()+existing.getQuantity());
	         productrepo.save(existing);
	         return ResponseEntity.ok("success");
	     }
	     return ResponseEntity.ok("fail");
	 }
//	 @PostMapping("/updatepriceandqty")
//	 public ResponseEntity<String> updatePriceAndQuantity(@RequestBody Product product) {
//	     Optional<Product> optional = productrepo.findById(product.getProductid());
//
//	     if (optional.isPresent()) {
//	         Product existing = optional.get();
//
//	         double newAvgPrice = (product.getPrice() + existing.getPrice()) / 2;
//	         int newQuantity = product.getQuantity() + existing.getQuantity();
//
//	         existing.setPrice(newAvgPrice);
//	         existing.setQuantity(newQuantity);
//	         productrepo.save(existing);
//
//	         // ✅ Manually fetch vendor
//	         Vendor vendor = vendorrepo.findByProduct(existing);
//	         if (vendor == null) {
//	             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                 .body("Vendor not found for product ID: " + product.getProductid());
//	         }
//
//	         // ✅ Create invoice with managed vendor entity
//	         PurchaseInvoice invoice = new PurchaseInvoice();
//	         invoice.setVendor(vendor); // must be managed entity
//	         invoice.setQuantity(product.getQuantity());
//	         invoice.setUnitPrice(product.getPrice());
//	         invoice.setTotalCost(product.getQuantity() * product.getPrice());
//	         invoice.setDate(LocalDate.now());
//
//	         purchaserepo.save(invoice);
//
//	         return ResponseEntity.ok("Product updated and invoice generated.");
//	     }
//
//	     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
//	 }


	 
//	 @PostMapping("/addproduct")
//	 public ResponseEntity<String> addingproduct(@RequestBody Product product, @RequestParam Long vendorId) {
//	     service.addProductWithVendor(product, vendorId);
//	     return ResponseEntity.ok("added successfully");
//	 }
	 
	 @GetMapping("/vendors")
	 public List<Vendor> allvendor(@RequestBody Vendor vendor){
		return service.getAllVendors();
		 
	 }
	 @GetMapping("/all")
	    public List<ProductDTO> getAllPurchases() {
	        return  service.getAllPurchases();  // Fetches all purchase DTOs
	    }
	
	 
	

}

package com.edureka.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edureka.training.DTO.VendorProductDTO;
import com.edureka.training.entity.Login;
import com.edureka.training.entity.Product;
import com.edureka.training.entity.Vendor;
import com.edureka.training.repository.ProductRepository;
import com.edureka.training.repository.VendorRepository;
import com.edureka.training.service.ProductService;
import com.edureka.training.service.VendorService;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@RequestMapping("apivendor")
public class VendorController {
	@Autowired
	VendorService vservice;
	@Autowired
	ProductService rservice;
	@Autowired 
	ProductRepository repo;
	@Autowired
	VendorRepository vrepo;
	@PostMapping("/addvendor")
	public ResponseEntity<String> addingvendor(@RequestBody VendorProductDTO dto) {
	    try {
	        Vendor vendor = dto.getVendor();
	        Product product = dto.getProduct();

	        // Step 1: Save vendor without product
	        vendor.setProduct(null); // break circular link
	        Vendor savedVendor = vservice.add_vendor(vendor);

	        // Step 2: Now save product and assign the saved vendor
	        product.setProductid(null); // to make sure it's treated as new
	        product.setVendor(savedVendor);
	        Product savedProduct = rservice.add_product(product);

	        // Step 3: Optional: update vendor to point back to saved product
	        savedVendor.setProduct(savedProduct);
	        vservice.add_vendor(savedVendor); // update vendor with product

	        return ResponseEntity.ok("success");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	}


//	 @GetMapping("/displayvendor")
//	 public ResponseEntity<List<Vendor>> addingvendor() {
//		 List<Vendor> l=vservice.fetechAll();
//		 
//		 System.out.println("form cline lisy"+"name  "+l.get(0).getProduct().getDescription());
//		 vservice.fetechAll();
//		 return ResponseEntity.ok(l);
//	 
//	 }
	 //now added by commenting above
	 @GetMapping("/displayvendor")
	 public ResponseEntity<List<Vendor>> getAllVendors() {
	     List<Vendor> vendors = vrepo.findAll();
	     return ResponseEntity.ok(vendors);
	 }

	 @GetMapping("edit/{id}")
	 public ResponseEntity<Vendor> getVendorById(@PathVariable long id) {
	     Vendor vendor = vservice.getVendorById(id);
	     return ResponseEntity.ok(vendor);
	 }
	 @GetMapping("delete/{id}")
	 public ResponseEntity<Vendor> getVendorByIdTodlete(@PathVariable long id) {
	     Vendor vendor = vservice.getVendorById(id);
	     Product product=rservice.getProductById(id);
	    // vrepo.delete(vendor);
	     repo.delete(product);
	     return ResponseEntity.ok(vendor);
	 }
	  @GetMapping("/getvendorbyproductid/{productId}")
      public ResponseEntity<Vendor> getVendorByProductId(@PathVariable Long productId) {
          return vrepo.findByProductProductid(productId)
                  .map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
      }

	
	

}

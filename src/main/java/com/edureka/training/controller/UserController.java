package com.edureka.training.controller;

import java.net.http.HttpHeaders;
import java.util.Map;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edureka.training.entity.Login;
import com.edureka.training.entity.Product;
import com.edureka.training.entity.UserCredential;
import com.edureka.training.entity.Vendor;
import com.edureka.training.repository.ProductRepository;
import com.edureka.training.repository.UserRepository;
import com.edureka.training.repository.VendorRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserRepository userrepo;
	@Autowired
	ProductRepository productrepo;
	@Autowired
	VendorRepository vendorrepo;
	@GetMapping()
	public String home() {
		return "home";
	}
	
	@PostMapping("/postman/register")
	public UserCredential register(@RequestBody UserCredential user) {
		System.out.println("Email: " + user.getEmail());

		return userrepo.save(user);
	}
	@PostMapping("/postman/product")
	public Product registerproduct(@RequestBody Product user) {
		//System.out.println("Email: " + user.getEmail());

		return productrepo.save(user);
	}
	@PostMapping("/postman/vendor")
	public Vendor registervendor(@RequestBody Vendor user) {
		//System.out.println("Email: " + user.getEmail());

		return vendorrepo.save(user);
	}
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<UserCredential> registerUser(@RequestBody UserCredential user) {
    	//System.out.println(user.getName());
    	if(userRepository.findByName(user.getName()).size()>0 ) {
    	
    		user.setName(null);
    		return ResponseEntity.ok(user);
    	}
    	else if( userRepository.findByEmail(user.getEmail()).size()>0) {
    		user.setEmail(null);
    		return ResponseEntity.ok(user);
    	}
    	else {
    	UserCredential savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    	}
		//return null;
    	
    }
   
  
        @PostMapping("/loginvalidation")
        public ResponseEntity<Login> validateLogin(@RequestBody Login user) {
            String name = user.getName();
            String password = user.getPassword();

            System.out.println("Received login: " + name + " / " + password);

            // Validate here
            if (userRepository.findByName(user.getName()).size()==1 && userRepository.findByPassword(user.getPassword()).size()>0) {
            	
                return ResponseEntity.ok(user);
            } else {
            	user.setName(null);
                return  ResponseEntity.ok(user);//ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        }
        @PostMapping("/admin")
        public ResponseEntity<Login> validateAdmin(@RequestBody Login user){
        	System.out.println("fomr api ..........."+user.getName());
        	
                 return  ResponseEntity.ok(user);//ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
             }
        }
    




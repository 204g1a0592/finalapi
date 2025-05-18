package com.edureka.training.controller;

import org.springframework.http.HttpHeaders;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edureka.training.entity.CartItem;
import com.edureka.training.entity.Login;
import com.edureka.training.entity.Product;
import com.edureka.training.entity.UserCredential;
import com.edureka.training.entity.Vendor;
import com.edureka.training.repository.CartItemRepository;
import com.edureka.training.repository.ProductRepository;
import com.edureka.training.repository.UserRepository;
import com.edureka.training.repository.VendorRepository;
import com.edureka.training.service.CartService;
import com.edureka.training.service.ProductService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/api")
@SessionAttributes("loggedInUser")
public class UserController {
	
	
	@Autowired
	UserRepository userrepo;
	@Autowired
	ProductRepository productrepo;
	@Autowired
	VendorRepository vendorrepo;
	 @Autowired
	ProductService productService;
	 @Autowired 
	 CartService cartservice;
	 @Autowired
	 CartItemRepository cirepo;
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
	public Product registerproducts(@RequestBody Product product) {
	    System.out.println("Received product: " + product);
	    return productrepo.save(product);
	}
	@GetMapping("/allProducts1")
	public ResponseEntity<List<Product>> getAllProducts1() {
	    List<Product> products = productrepo.findAll();
	    return ResponseEntity.ok(products);
	}


	@PostMapping("/postman/vendor")
	public Vendor registervendor(@RequestBody Vendor user) {
		//System.out.println("Email: " + user.getEmail());

		return vendorrepo.save(user);
	}
    @Autowired
    private UserRepository userRepository;

  //  @PostMapping("/register")
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
   // @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
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
        @GetMapping("/allProducts")
        public ResponseEntity<List<Product>> getAllProducts() {
            List<Product> products = productrepo.findAll();
            return ResponseEntity.ok(products);
        }

      // for cart
       
//              @PostMapping("/add")
//        public ResponseEntity<?> addToCart(@RequestBody Map<String, Object> payload) {
//        	System.out.println("enterd api");
//            try {
//                Long userId = Long.valueOf(payload.get("userid").toString());
//                Long productId = Long.valueOf(payload.get("productid").toString());
//                Integer quantity = Integer.valueOf(payload.get("quantity").toString());
//                
//                cartservice.addToCart(userId, productId, quantity);
//
//                return ResponseEntity.ok().build();
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                                     .body("Error adding to cart: " + e.getMessage());
//            }
//        }
        
      
        @PostMapping("/cart/add/{productId}")
        public ResponseEntity<String> addToCart(@PathVariable Long productId,
                                                @RequestParam int quantity,
                                                @RequestParam Long userId) {
            // Validate userId, productId, quantity here
            
            // Your service logic to add product to cart
            cartservice.addToCart(userId, productId, quantity);

            return ResponseEntity.ok("Product added to cart");
        }


        @GetMapping("/user/{name}")
        public ResponseEntity<UserCredential> getUserByName(@PathVariable String name) {
            List<UserCredential> users = userRepository.findByName(name);
            if (users.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(users.get(0));
        }
        @PostMapping("/user/removeCartItem")
        public String removeCartItem(@RequestParam Long itemId, HttpSession session) {
            Long userId = (Long) session.getAttribute("userid");
            if (userId == null) return "redirect:/login";

            cartservice.removeFromCart(userId, itemId);
            return "redirect:/user/cart";
        }

        @GetMapping("/cart/{userId}")
        public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long userId) {
            List<CartItem> cartItems = cartservice.getCartItems(userId);  // <-- here you call it
            return ResponseEntity.ok(cartItems);
        }

//        @PostMapping(value = "/testjson", consumes = MediaType.APPLICATION_JSON_VALUE)
//        public ResponseEntity<String> testJson(@RequestBody Map<String, Object> payload) {
//            System.out.println("Received JSON payload: " + payload);
//            return ResponseEntity.ok("JSON received successfully!");
//        }

}

        
        
        
    




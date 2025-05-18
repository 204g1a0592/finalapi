package com.edureka.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edureka.training.entity.Product;
import com.edureka.training.entity.UserCredential;
import com.edureka.training.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long>{



	List<Vendor> findByVendorname(String vendorname);

	Vendor findByProduct(Product existing);

	
	
}

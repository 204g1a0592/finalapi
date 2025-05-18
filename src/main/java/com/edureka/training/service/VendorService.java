package com.edureka.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edureka.training.entity.Vendor;
import com.edureka.training.repository.VendorRepository;

@Service
public class VendorService {
	@Autowired
	VendorRepository vendorrepo;
	
	public Vendor add_vendor(Vendor vendor) {
//		if(vendorrepo.findByVendorname(vendor.getVendorname()).size()==1) {
//			return false;
//		}
//		else {
		
		return vendorrepo.save(vendor);
			
			
		}
			
	
	public List<Vendor> fetechAll(){
		return vendorrepo.findAll();
	}


	public Vendor getVendorById(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	
	

}

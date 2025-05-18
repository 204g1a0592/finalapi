package com.edureka.training.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.edureka.training.entity.PurchaseInvoice;

public interface PurchaseInvoiceRepository extends JpaRepository<PurchaseInvoice, Long> {

    List<PurchaseInvoice> findTop5ByOrderByIdDesc();

    List<PurchaseInvoice> findByVendor_VendornameIgnoreCaseContaining(String vendorName);
}

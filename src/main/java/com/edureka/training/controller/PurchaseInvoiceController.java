package com.edureka.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.edureka.training.entity.PurchaseInvoice;
import com.edureka.training.repository.PurchaseInvoiceRepository;

@RestController
@RequestMapping("/apiipurchasenvoice")
public class PurchaseInvoiceController {

    @Autowired
    private PurchaseInvoiceRepository purchaseInvoiceRepository;

    // Get last 5 invoices by ID descending (latest first)
//    @GetMapping("/last5")
//    public List<PurchaseInvoice> getLast5Invoices() {
//        return purchaseInvoiceRepository.findTop5ByOrderByIdDesc();
//    }
//
//    // Search invoices by vendor name (case insensitive) or invoice ID (exact match)
//    @GetMapping("/search")
//    public List<PurchaseInvoice> searchInvoices(@RequestParam("query") String query) {
//        // First try to parse query as Long (invoice ID)
//        try {
//            Long id = Long.parseLong(query);
//            return purchaseInvoiceRepository.findById(id)
//                    .map(List::of)
//                    .orElse(List.of());
//        } catch (NumberFormatException e) {
//            // Not an ID, search by vendor name
//            return purchaseInvoiceRepository.findByVendor_VendornameIgnoreCaseContaining(query);
//        }
//    }
    @PostMapping("/addinvoice")
    public String addInvoice(@RequestBody PurchaseInvoice invoice) {
        invoice.setId(null); // force to save as new
        purchaseInvoiceRepository.save(invoice);
        return "success";
    }
    @GetMapping("/all")
    public List<PurchaseInvoice> getAllInvoices() {
        return purchaseInvoiceRepository.findAll();
    }
   
}

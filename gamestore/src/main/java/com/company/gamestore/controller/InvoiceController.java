package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel addInvoice(@RequestBody @Valid Invoice invoice) {
        return serviceLayer.saveInvoice(invoice);
        // return serviceLayer.saveInvoice(serviceLayer.buildInvoiceViewModel(invoice))
    }

    @PutMapping("/invoices")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(@RequestBody Invoice invoice){
        if (!(serviceLayer.findInvoiceById(invoice.getInvoiceId()) == null)) {
            serviceLayer.updateInvoice(invoice);
        }
    }

    @DeleteMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Integer id){
        serviceLayer.deleteInvoiceById(id);
    }

    @GetMapping("/invoices")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return serviceLayer.findAllInvoices();
    }

    @GetMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable int id) {
        return serviceLayer.findInvoiceById(id);
    }

    @GetMapping("/invoices/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoiceByCustomerName(@PathVariable String name){return serviceLayer.findInvoicesByCustomerName(name);}



// METHOD ONLY FOR CLEARING DATABASE WHEN TESTING W/ INSOMNIA
//    @DeleteMapping("/invoices")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteAll(){serviceLayer.deleteAllInvoices();}

}

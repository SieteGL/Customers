package siete.springframework.customersapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siete.springframework.customersapp.model.Customers;
import siete.springframework.customersapp.services.CustomersService;

import java.util.List;

@RestController
public class CustomersController {

    @Autowired
    CustomersService customersService;

    @GetMapping("/customers")
    private List<Customers> getAllCustomers(){

        return customersService.getAllCustomers();
    }

    @GetMapping("/customers/{customid}")
    private Customers getCustomers(@PathVariable("customid") Long customid){
        return customersService.getCustomersById(customid);
    }

    @DeleteMapping("/customers/{customid}")
    private void deleteCustomer(@PathVariable("customid") Long customid){
        customersService.delete(customid);
    }

    @PostMapping("/customers")
    private Long saveCustomers(@RequestBody Customers customers){
        customersService.saveOrUpdate(customers);
        return customers.getId();
    }

    @PutMapping("/customers")
    private Customers update(@RequestBody Customers customers){
        customersService.saveOrUpdate(customers);
        return customers;
    }

}

package siete.springframework.customersapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import siete.springframework.customersapp.exception.CustomerIdExistException;
import siete.springframework.customersapp.exception.CustomerNotFoundException;
import siete.springframework.customersapp.services.CustomersService;

import siete.springframework.customersapp.model.Customers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomersController {

    @Autowired
    CustomersService customersService;

    private static Map<String, Customers> customersRepo = new HashMap<>();

    /**static {
        Customers c1 = new Customers();
        c1.setId("1");
        c1.setAddress("intelli");
        c1.setCity("Santiago");
        c1.setFirstName("c1");
        c1.setLastName("c1.1");
        c1.setPhone(123456789);
        customersRepo.put(c1.getId(), c1);
    }**/

    /**@GetMapping("/customers")
    private List<Customers> getAllCustomers(){
    return customersService.getAllCustomers();
    }**/

    @RequestMapping(value = "/customers")
    public ResponseEntity<Object> getAllCustomers(){
        if(customersService.getAllCustomers().size() == 0) throw new CustomerNotFoundException();
        return new ResponseEntity<>(customersRepo.values(), HttpStatus.OK);
    }

    /**@GetMapping("/customers/{customid}")
    private Customers getCustomers(@PathVariable("customid") String customid){
        return customersService.getCustomersById(customid);
    }**/

    /**@DeleteMapping("/customers/{customid}")
    private void deleteCustomer(@PathVariable("customid") String customid){
        customersService.delete(customid);
    }**/

    /**@PostMapping("/customers")
    private String saveCustomers(@RequestBody Customers customers){
        customersService.saveOrUpdate(customers);
        //throw new CustomerNotFoundException();
        return customers.getId();
    }**/

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<Object> createCustomer(@RequestBody Customers customers){
        System.out.println("****");
        System.out.println(customersRepo.containsKey(customers.getId()));
        System.out.println("****");

        if(customersRepo.containsKey(customers.getId()))throw new CustomerIdExistException();
        customersRepo.put(customers.getId(), customers);
        customersService.saveOrUpdate(customers);
        return new ResponseEntity<>("Customer is created Successfully", HttpStatus.CREATED);
    }


    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") String id, @RequestBody Customers customers){

        System.out.println(id);
        System.out.println(customersRepo.containsKey(id));

        if(!customersRepo.containsKey(id))throw new CustomerNotFoundException();
        customersRepo.remove(id);
        customers.setId(id);
        customersRepo.put(id, customers);
        customersService.saveOrUpdate(customers);
        return new ResponseEntity<>("Customer Is Updated Successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
        System.out.println(id);
        System.out.println(customersRepo.containsKey(id));

        if(!customersRepo.containsKey(id))throw new CustomerNotFoundException();
        customersRepo.remove(id);
        customersService.delete(id);
        return new ResponseEntity<>("Customer is deleted Successfully", HttpStatus.OK);
    }


    /**@PutMapping("/customers")
    private Customers update(@RequestBody Customers customers){
        customersService.saveOrUpdate(customers);
        return customers;
    }**/

}

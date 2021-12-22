package siete.springframework.customersapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import siete.springframework.customersapp.exception.CustomException;
import siete.springframework.customersapp.exception.CustomerIdExistException;
import siete.springframework.customersapp.exception.CustomerNotFoundException;
import siete.springframework.customersapp.services.CustomersService;

import siete.springframework.customersapp.model.Customers;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class CustomersController {

    @Autowired
    CustomersService customersService;

    private static Map<String, Customers> customersRepo = new HashMap<>();
    //private static final Logger LOG = LogManager.getLogger(CustomersController.class);

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomersController.class);


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
        if(customersService.getAllCustomers().size() == 0) {
            throw new CustomerNotFoundException();
        }
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

    /**@RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<Object> createCustomer(@RequestBody Customers customers){
        System.out.println("****");
        System.out.println(customersRepo.containsKey(customers.getId()));
        System.out.println("****");

        if(customersRepo.containsKey(customers.getId()))throw new CustomerIdExistException();
        customersRepo.put(customers.getId(), customers);
        customersService.saveOrUpdate(customers);
        return new ResponseEntity<>("Customer is created Successfully", HttpStatus.CREATED);
    }**/

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<Object> createCustomer(@RequestBody Customers customers) {
        if (customersRepo.containsKey(customers.getId())) {
            //LOG.info("Error");
            throw new CustomException(
                    "Wrong ID",
                    "You've entered an existing ID",
                    "Check Again the Information",
                    "Please change the ID",
                    "Contact your Support");
        }
        customersRepo.put(customers.getId(), customers);
        customersService.saveOrUpdate(customers);
        return new ResponseEntity<>("Customer is created Successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") String id,
                                                 @RequestBody Customers customers){

        LOGGER.trace("doStuff needed more information - {}", customers);
        LOGGER.debug("doStuff needed to debug - {}", id);
        LOGGER.info("Error in this point - {}", customers.getAddress());
        LOGGER.warn("doStuff needed to warn - {}", id);
        LOGGER.error("doStuff encountered an error with value - {}", id);

        /**System.out.println(id);
        System.out.println(customersRepo.containsKey(id));**/

        if(!customersRepo.containsKey(id)) {
            throw new CustomerNotFoundException();
        }
        customersRepo.remove(id);
        customers.setId(id);
        customersRepo.put(id, customers);
        customersService.saveOrUpdate(customers);
        return new ResponseEntity<>("Customer Is Updated Successfully", HttpStatus.OK);
    }

    /**@RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id){

        if(!customersRepo.containsKey(id)) {
            throw new CustomerNotFoundException();
        }
        customersRepo.remove(id);
        customersService.delete(id);
        return new ResponseEntity<>("Customer is deleted Successfully", HttpStatus.OK);
    }**/



    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
        try {
            customersRepo.remove(id);
            customersService.delete(id);
        } catch (Exception e) {
            LOGGER.info("Please verify the id that you want to delete", e);
            throw new CustomException(
                    "Please verify the ID",
                    "Please be sure if the ID you want to delete exist",
                    "Generate a consult to GET all the users",
                    "Verify the data it's the same",
                    "Contact your support if the issue persist");
        }
        return new ResponseEntity<>("Customer is deleted successfully", HttpStatus.OK);
    }


    /**@PutMapping("/customers")
    private Customers update(@RequestBody Customers customers){
        customersService.saveOrUpdate(customers);
        return customers;
    }**/

}

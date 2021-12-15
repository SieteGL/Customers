package siete.springframework.customersapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siete.springframework.customersapp.model.Customers;
import siete.springframework.customersapp.repositories.CustomersRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomersService {

    @Autowired
    CustomersRepository customersRepository;

    public List<Customers> getAllCustomers(){
        List<Customers> customers = new ArrayList<Customers>();
        customersRepository.findAll().forEach(customers1 -> customers.add(customers1));
        return customers;
    }

    public Customers getCustomersById(Long id){
        return customersRepository.findById(id).get();
    }

    public void saveOrUpdate(Customers customers){
        customersRepository.save(customers);
    }

    public void delete(Long id){
        customersRepository.deleteById(id);
    }

    public void update(Customers customers,Long customid){
        customersRepository.save(customers);
    }


}

package siete.springframework.customersapp.repositories;

import org.springframework.data.repository.CrudRepository;
import siete.springframework.customersapp.model.Customers;

public interface CustomersRepository extends CrudRepository<Customers, String> {
}

package guru.springfamework.repositories;

import guru.springfamework.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository <Customer, Long> {

    Optional<Customer> findByfirstname(String firstname);
    Optional<Customer> findBylastname(String lastname);

}

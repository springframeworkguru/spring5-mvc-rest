package guru.springfamework.repositories;

import guru.springfamework.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Darcy Xian  22/11/20  5:16 pm      spring5-mvc-rest
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {



}

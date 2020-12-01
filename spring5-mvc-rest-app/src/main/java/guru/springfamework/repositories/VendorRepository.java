package guru.springfamework.repositories;

import guru.springfamework.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Darcy Xian  24/11/20  3:57 pm      spring5-mvc-rest
 */
@Repository
public interface VendorRepository extends JpaRepository<Vendor,Long> {

}

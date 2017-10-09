package guru.springfamework.repositories;

import guru.springfamework.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jt on 10/6/17.
 */
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}

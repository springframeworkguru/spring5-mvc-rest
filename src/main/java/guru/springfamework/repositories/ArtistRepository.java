package guru.springfamework.repositories;

import guru.springfamework.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jt on 9/24/17.
 */
public interface ArtistRepository extends JpaRepository<Artist, Long> {
}

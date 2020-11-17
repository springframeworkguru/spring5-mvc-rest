package bootcamp.repositories;

import bootcamp.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jt on 9/24/17.
 */
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Artist findByName(String name);
}


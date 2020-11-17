package bootcamp.bootstrap;

import bootcamp.domain.Artist;
import bootcamp.repositories.ArtistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    public Bootstrap(ArtistRepository categoryRepository) {
        this.artistRepository = categoryRepository;
    }

    private ArtistRepository artistRepository;

    @Override
    public void run(String... args) throws Exception {
        Artist bono = new Artist();
        bono.setName("Bono");

        Artist lil_wayne = new Artist();
        lil_wayne.setName("Lil Wayne");

        artistRepository.save(lil_wayne);
        artistRepository.save(bono);

        System.out.println("count " + artistRepository.count());
    }
}

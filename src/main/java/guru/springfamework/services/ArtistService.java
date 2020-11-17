package guru.springfamework.services;

import guru.springfamework.api.v1.model.ArtistDTO;
import guru.springfamework.domain.Artist;
import jdk.jfr.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArtistService {

public List<ArtistDTO> getAllArtists();

ArtistDTO getArtistByName(String name);


}

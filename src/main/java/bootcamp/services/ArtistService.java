package bootcamp.services;

import bootcamp.api.v1.model.ArtistDTO;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArtistService {

public List<ArtistDTO> getAllArtists();

ArtistDTO getArtistByName(String name);


}

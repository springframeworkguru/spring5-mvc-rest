package bootcamp.services;

import bootcamp.api.v1.model.ArtistDTO;
import bootcamp.mapper.ArtistMapper;
import bootcamp.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {
    public ArtistServiceImpl(ArtistMapper artistMapper, ArtistRepository artistRepository) {
        this.artistMapper = artistMapper;
        this.artistRepository = artistRepository;
    }

    private final ArtistMapper artistMapper;
    private final ArtistRepository artistRepository;

    @Override
    public List<ArtistDTO> getAllArtists() {
       return artistRepository.findAll().stream().map(artistMapper::artistToArtistDTO).collect(Collectors.toList());

    }

    @Override
    public ArtistDTO getArtistByName(String name) {
        return null;
    }
}

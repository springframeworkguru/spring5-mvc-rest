package guru.springfamework.services;

import guru.springfamework.api.v1.model.ArtistDTO;
import guru.springfamework.domain.Artist;
import guru.springfamework.mapper.ArtistMapper;
import guru.springfamework.repositories.ArtistRepository;

import java.util.List;
import java.util.stream.Collectors;

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

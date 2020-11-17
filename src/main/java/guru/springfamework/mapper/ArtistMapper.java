package guru.springfamework.mapper;

import guru.springfamework.api.v1.model.ArtistDTO;
import guru.springfamework.domain.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArtistMapper {

    ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);

    @Mapping(source = "id", target = "id")
    ArtistDTO artistToArtistDTO(Artist artist);
}

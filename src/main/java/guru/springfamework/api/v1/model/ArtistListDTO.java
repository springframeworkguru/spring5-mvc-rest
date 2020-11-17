package guru.springfamework.api.v1.model;

import guru.springfamework.domain.Artist;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ArtistListDTO {
    List<Artist> artists;
}

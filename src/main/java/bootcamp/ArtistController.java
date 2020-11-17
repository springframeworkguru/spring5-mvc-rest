package bootcamp;

import bootcamp.api.v1.model.ArtistDTO;
import bootcamp.api.v1.model.ArtistListDTO;
import bootcamp.services.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("api/artists")
public class ArtistController {

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    private final ArtistService artistService;

    @GetMapping("/getall")
    public ResponseEntity<ArtistListDTO> getAllArtists(){
       return new ResponseEntity<ArtistListDTO>(
        new ArtistListDTO(artistService.getAllArtists()), HttpStatus.OK);

    }

    @GetMapping("name")
    public ResponseEntity<ArtistDTO> getArtistByName(String name){
        return new ResponseEntity<ArtistDTO>(
                artistService.getArtistByName(name), HttpStatus.OK);

    }



}

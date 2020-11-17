package guru.springfamework;

import guru.springfamework.services.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("", "/", "/getall")


}

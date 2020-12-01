package guru.springfamework.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Darcy Xian  22/11/20  9:07 pm      spring5-mvc-rest
 */
public abstract class AbstractControllerTest {

    public static String asJsonString(final Object ob){
        try{
            return new ObjectMapper().writeValueAsString(ob);
        } catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
package application.user;

import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

//RestController tells Spring to check this class for request mappings.
//Unlike rails, there seem to not be a central location where routes are defined.
@RestController
@EnableAutoConfiguration
public class UserController
{
    //Request mappings associates a function with a path, http params, headers etc.
    @RequestMapping(value = "/users", method = GET)
    public String get_users()
    {
        return "return stuff here";
    }
}

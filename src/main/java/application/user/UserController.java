package application.user;

import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@EnableAutoConfiguration
public class UserController
{
    @RequestMapping(value = "/users", method = GET)
    public String get_users()
    {
        return "return stuff here";
    }
}

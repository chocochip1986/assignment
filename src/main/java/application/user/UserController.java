package application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

//RestController tells Spring to check this class for request mappings.
//Unlike rails, there seem to not be a central location where routes are defined.
@RestController
public class UserController
{
    @Autowired
    UserService userService;

    //Request mappings associates a function with a path, http params, headers etc.
    @RequestMapping(value = "/users", method = GET)
    public List<User> get_users()
    {
        return userService.find_all_users();
    }
}

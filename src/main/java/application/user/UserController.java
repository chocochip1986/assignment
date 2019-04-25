package application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    //Get method to retrieve one user record
    @RequestMapping(value = "/user/{id}", method = GET)
    public Optional<User> get_user(@RequestParam(required = false ) @PathVariable("id") Integer id)
    {
        return userService.find_by(id);
    }

    //Post method to create a user record i.e. /user?name=afafa
    @RequestMapping(value = "/user", method = POST)
    public void create_user(@RequestParam(required = true, name = "name") String name, @RequestParam(required = true, name = "salary") double salary)
    {
        System.out.println("Creating entry in User table => "+name+" "+Double.toString(salary));
    }


}

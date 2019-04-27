package application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

//RestController tells Spring to check this class for request mappings.
//Unlike rails, there seem to not be a central location where routes are defined.
@RestController
public class UserController
{
    private final double min_salary = 0;
    private final double max_salary = 4000;

    @Autowired
    UserService userService;

    //Request mappings associates a function with a path, http params, headers etc.
    @RequestMapping(value = "/users", method = GET)
    public Map<String, List<User>> get_users()
    {
        Map<String, List<User>> response = new HashMap<String, List<User>>();
        response.put("results", userService.find_all_users(min_salary, max_salary));
        return response;
    }

    //Get method to retrieve one user record
    @RequestMapping(value = "/user/{id}", method = GET)
    public Map<String, List<User>> get_user(@RequestParam(required = false ) @PathVariable("id") Integer id)
    {
        Map<String,List<User>> response = new HashMap<String, List<User>>();
        List<User> users = new ArrayList<User>();
        users.add(userService.find_by(id));
        response.put("results", users);
        return response;
    }

    //Post method to create a user record i.e. /user?name=afafa
    @RequestMapping(value = "/user", method = POST)
    public ResponseEntity<String> create_user(@RequestParam(required = true, name = "name") String name, @RequestParam(required = true, name = "salary") double salary)
    {
        System.out.println("Creating entry in User table => "+name+" "+Double.toString(salary));
        try
        {
            userService.create(name, salary);
        }
        catch (Exception e)
        {
            System.out.println("Error in creating user record in db!");
            System.out.println("Error: "+e.getClass()+" "+e.getMessage());
            return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("GOOD!", HttpStatus.OK);
    }
}

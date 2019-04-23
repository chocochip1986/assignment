package application;

import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class User
{
    @RequestMapping("/")
    public String get_users()
    {
        return "return stuff here";
    }
}

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class User
{
    @RequestMapping("/")
    String get_users()
    {
        return "return stuff here";
    }
}

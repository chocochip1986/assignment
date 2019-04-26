package application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    UserDao userDao;

    public List<User> find_all_users()
    {
        return this.userDao.findAll();
    }

    public Optional<User> find_by(Integer employee_id)
    {
        return this.userDao.findById(employee_id);
    }

    public User create(String name, double salary)
    {
        User user = new User(name, salary);
        return userDao.save(user);
    }
}

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

    public List<User> find_all_users(double min_salary, double max_salary)
    {
        return this.userDao.findBySalaryBetween(min_salary, max_salary);
    }

    public List<User> find_all_users()
    {
        return this.userDao.findAll();
    }

    public User find_by(Integer employee_id)
    {
        Optional<User> optionalUserEntity = this.userDao.findById(employee_id);
        return optionalUserEntity.get();
    }

    public User create(String name, double salary)
    {
        User user = new User(name, salary);
        return userDao.save(user);
    }
}

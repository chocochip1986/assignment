package application.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer>
{
    User findByName(String name);

    List<User> findBySalaryBetween(double min_salary, double max_salary);
}

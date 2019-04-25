package application.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer>
{
    //Implicitly order by primary key in descending order and take the first
    //User find_by_employee_id(long employee_id);
}

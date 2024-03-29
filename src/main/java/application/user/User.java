package application.user;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "tbl_test")
public class User{
    private static final String MAX_SALARY = "999999.99";

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "tbl_test_seq")
    @SequenceGenerator(name = "tbl_test_seq", sequenceName = "tbl_test_seq", initialValue = 10000, allocationSize = 1)
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 50, message = "Name of user cannot be more than 50 characters or lesser than 1 character!")
    @Column(name = "name", nullable = false, length = 50)
    private String name;


    @DecimalMax(value = MAX_SALARY, message = "salary cannot be more than "+MAX_SALARY)
    @Column(name = "salary", nullable = false, precision = 8, scale = 2)
    private double salary;


    public User() {

    }

    public User(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    public String getName()
    {
        return name;
    }

    public double getSalary()
    {
        return salary;
    }
}

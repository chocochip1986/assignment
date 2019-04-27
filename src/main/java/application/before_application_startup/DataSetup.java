package application.before_application_startup;

import application.file_readers.CsvFileReader;
import application.user.User;
import application.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSetup
{
    @Autowired
    CsvFileReader<User> fileReader;

    @Autowired
    UserDao userDao;

    public void setupData()
    {
        List<User> list_of_users = fileReader.read_csv_file( "data.csv" );
        System.out.println("Number of users to save: "+list_of_users.size());
        userDao.saveAll(list_of_users);
    }

}

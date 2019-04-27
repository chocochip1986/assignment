package application.before_application_startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationBeforeStartupBean implements CommandLineRunner
{
    @Autowired
    DataSetup dataSetup;

    public void run(String... args) throws Exception {
        System.out.println("Loading data into db");
        dataSetup.setupData();
        //TODO insert into database
    }
}

package application.file_readers;


import application.user.User;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;


import java.io.*;
import java.util.List;

@Component
public class CsvFileReader<T>
{
    public <T> List<T> read_csv_file(String file_name)
    {
        File data_file;
        try
        {
            data_file = ResourceUtils.getFile("classpath:data.csv");
        }
         catch (Exception e)
         {
             System.out.println("Something wrong trying to read the file!");
             return null;
         }

        if ( !data_file.exists() )
        {
            System.out.println("File "+file_name+" does not exists!");
            return null;
        }
        BufferedReader file_reader;
        try
        {
            file_reader = new BufferedReader(new FileReader(data_file.getPath()));
        }
        catch (Exception e)
        {
            System.out.println("Unable to read file! "+e.getMessage());
            return null;
        }
        CsvMapper csv_mapper = new CsvMapper();
        CsvSchema csv_schema;

        try
        {
            System.out.println("Building schema for User");
            csv_schema = csv_mapper.schemaFor(User.class).withColumnSeparator(';');
        }
        catch (Exception e)
        {
            System.out.println("Unable to find class "+User.class);
            return null;
        }

        ObjectReader reader = csv_mapper.reader(User.class).with(csv_schema);
        try
        {
            file_reader.readLine(); //Skip headers
            MappingIterator<T> mappingIterator = reader.readValues(file_reader);
            return mappingIterator.readAll();
        }
        catch (Exception e)
        {
            System.out.println("Unable to parse values csv file into iterator: "+e.getMessage());
            return null;
        }
    }
}

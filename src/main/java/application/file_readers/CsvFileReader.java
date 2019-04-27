package application.file_readers;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;


import java.io.*;
import java.util.List;

@Component
public class CsvFileReader
{
    public <T> List<T> read_csv_file(Class<T> klass, String file_name)
    {
        File data_file = get_data_file_from_resource_dir(file_name);
        BufferedReader file_reader = read_file(data_file);

        CsvMapper csv_mapper = new CsvMapper();
        CsvSchema csv_schema = generate_csv_schema_for(klass, csv_mapper);

        ObjectReader reader = csv_mapper.reader(klass).with(csv_schema);
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

    private CsvSchema generate_csv_schema_for(Class klass, CsvMapper csv_mapper)
    {
        try
        {
            return csv_mapper.schemaFor(klass).withColumnSeparator(';');
        }
        catch (Exception e)
        {
            System.out.println("Unable to build schema for"+klass.toString());
        }
        return null;
    }

    private BufferedReader read_file(File data_file)
    {
        try
        {
            return new BufferedReader(new FileReader(data_file.getPath()));
        }
        catch ( FileNotFoundException e )
        {
            System.out.println("File "+data_file.getPath()+" not found!!");
            System.out.println(e.getMessage());
        }
        catch ( Exception e )
        {
            System.out.println("Someting went wrong!");
            System.out.println(e.getMessage());
        }
        return null;
    }

    private File get_data_file_from_resource_dir(String file_path)
    {
        try
        {
            return ResourceUtils.getFile("classpath:"+file_path);
        }
        catch ( FileNotFoundException e )
        {
            System.out.println("The File "+file_path+" not found!!");
            System.out.println(e.getMessage());
        }
        catch ( Exception e )
        {
            System.out.println("There is someting went wrong!");
            System.out.println(e.getMessage());
        }
        return null;
    }
}

package launcher;

import domain.DirectoryValidator;
import domain.dto.DictionaryDto;
import domain.excel.ExcelReader;
import domain.json.DictionaryJsonWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConsoleApp
{
    private static final String appDir = new File("").getAbsolutePath();
    private static final Path excelDir = Paths.get( appDir, "excel" );
    private static final Path jsonDir = Paths.get( appDir,"json" );

    private static DirectoryValidator validator = new DirectoryValidator();

    public static void main( String[] args )
    {
        try {
            // Validate excel directory
            if( !validator.validateAndCreateDirectoryIfNotExist( excelDir )) {
                System.out.println( "Excel directory not found! Created new excel directory. Place there excel files and rerun the application" );
                return;
            }

            // Validate json directory
            if( !validator.validateAndCreateDirectoryIfNotExist( jsonDir )) {
                System.out.println( "Json directory not found. Created new json directory." );
            }

            ExcelReader excelReader = new ExcelReader();
            DictionaryJsonWriter jsonWriter = new DictionaryJsonWriter();

            Files.list( excelDir )
                    .forEach( path -> {
                        // Get dictionary from excel file as DTO
                        DictionaryDto dto = excelReader.read( path );

                        // Get excel file name without extension
                        String fileName = path.toFile().getName();
                        fileName = fileName.substring( 0, fileName.lastIndexOf( '.' ));

                        // Write dictionary in json file
                        jsonWriter.write( dto, jsonDir, fileName );
                    });
        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }
}

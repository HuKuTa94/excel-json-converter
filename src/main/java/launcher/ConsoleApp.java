package launcher;

import domain.DirectoryValidator;
import domain.dto.DTO;
import domain.excel.DictionaryExcelReader;
import domain.excel.ExcelReader;
import domain.excel.LocaleExcelReader;
import domain.json.JsonWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConsoleApp
{
    private static final String APP_DIR = new File( "" ).getAbsolutePath();

    private static final Path EXCEL_DICTIONARY_DIR = Paths.get(APP_DIR, "excel", "dictionary" );
    private static final Path JSON_DICTIONARY_DIR = Paths.get(APP_DIR,"json", "dictionary" );

    private static final Path EXCEL_LOCALE_DIR = Paths.get(APP_DIR, "excel", "locale" );
    private static final Path JSON_LOCALE_DIR = Paths.get(APP_DIR, "json", "locale" );

    private static DirectoryValidator validator = new DirectoryValidator();


    public static void main( String[] args )
    {
        try {
            convertDictionaries();
            convertLocales();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }


    private static void convertDictionaries() throws IOException {
        if( validateDirectories( EXCEL_DICTIONARY_DIR, JSON_DICTIONARY_DIR )) {
            convertToJson( new DictionaryExcelReader(), EXCEL_DICTIONARY_DIR, JSON_DICTIONARY_DIR );
        }
    }

    private static void convertLocales() throws IOException {
        if( validateDirectories( EXCEL_LOCALE_DIR, JSON_LOCALE_DIR )) {
            convertToJson( new LocaleExcelReader(), EXCEL_LOCALE_DIR, JSON_LOCALE_DIR );
        }
    }

    private static boolean validateDirectories( Path excelDir, Path jsonDir )
    {
        // Validate excel directory
        if( !validator.validateAndCreateDirectoryIfNotExist( excelDir )) {
            System.out.println( "Excel directory not found! Created new excel directory. Place there excel files and rerun the application" );
            return false;
        }

        // Validate json directory
        if( !validator.validateAndCreateDirectoryIfNotExist( jsonDir )) {
            System.out.println( "Json directory not found. Created new json directory." );
        }
        return true;
    }

    private static void convertToJson( ExcelReader reader, Path excelDir, Path jsonDir ) throws IOException
    {
        Files.list( excelDir )
                .forEach( path -> {
                    // Read excel into dto
                    DTO dto = reader.read( path );

                    // Get excel file name without extension
                    String fileName = path.toFile().getName();
                    fileName = fileName.substring( 0, fileName.lastIndexOf( '.' ));

                    // Write json from dto
                    JsonWriter.write( dto, jsonDir, fileName );
                });
    }
}

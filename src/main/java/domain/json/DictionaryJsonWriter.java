package domain.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.dto.DictionaryDto;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class DictionaryJsonWriter
{
    private static final String DATA_FILE = "test.json";

    public void write( DictionaryDto dictionaryDto, Path outDirectory, String fileName )
    {
        try {
            // Write to JSON file
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson( dictionaryDto );

            try ( FileWriter file = new FileWriter( Path.of( outDirectory.toString(), fileName.concat( ".json" )).toString() )) {
                file.write( json );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}

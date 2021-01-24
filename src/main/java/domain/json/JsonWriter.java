package domain.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.dto.DTO;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public abstract class JsonWriter
{
    public static void write( DTO dto, Path jsonDir, String fileName )
    {
        try {
            // Write to JSON file
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson( dto );

            try ( FileWriter file = new FileWriter( Path.of( jsonDir.toString(), fileName.concat( ".json" )).toString() )) {
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

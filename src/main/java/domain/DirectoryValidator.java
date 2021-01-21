package domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryValidator
{
    public boolean validateAndCreateDirectoryIfNotExist( Path dir )
    {
        boolean dirExist = Files.exists( dir );
        if( !dirExist )
        {
            try {
                Files.createDirectory( dir );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dirExist;
    }
}

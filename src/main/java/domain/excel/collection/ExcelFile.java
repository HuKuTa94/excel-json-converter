package domain.excel.collection;

import domain.excel.iterator.ExcelIterator;
import domain.excel.iterator.LineByLineIterator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Class reads the excel file in special directory of the project. The excel table can be multi-language.
 * The excel file must have the following structure:
 *  |  A  |  B  |  C  |  D  |  ...
 * 1| lang| name| lang| name|  ...
 * 2| word| desc| word| desc|  ...
 * ...
 * A1 - language of table AB;
 * B1 - Dictionary name in language A1. Example: for rus - Новый год.
 * C1 - next language.
 * D1 - Dictionary name in next language B1. Example: for eng - New year.
 * A2 - Word in language A1. Example: for rus - Подарок.
 * B2 - Description of the word A2 in language A1. Example: for rus - Предмет, который дарят.
 * C2 - Same but for next language...
 * ... */
public class ExcelFile implements IterableExcelFile
{
    private XSSFSheet sheet;

    public ExcelFile( Path pathToFile )
    {
        File file = new File( pathToFile.toString() );

        try ( FileInputStream fis = new FileInputStream( file )) {
            sheet = new XSSFWorkbook( fis ).getSheetAt( 0 );
        }
        catch ( FileNotFoundException e) {
            System.err.println( "File not found!" );
            e.printStackTrace();
        }
        catch ( IOException e ) {
            System.err.println( "Impossible close the excel file! Maybe file used by other process!" );
        }
    }

    @Override
    public ExcelIterator getLineByLineIterator() {
        return new LineByLineIterator( sheet );
    }
}

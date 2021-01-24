package domain.excel.collection;

import domain.excel.iterator.DictionaryIterator;
import domain.excel.iterator.LocaleIterator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

/** Class wraps apache poi XSSDSheet to provide iterators for special excel tables. */
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
    public DictionaryIterator getDictionaryIterator() {
        return new DictionaryIterator( sheet );
    }

    @Override
    public LocaleIterator getLocaleIterator() {
        return new LocaleIterator( sheet );
    }
}

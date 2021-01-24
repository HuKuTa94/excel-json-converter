package domain.excel;

import domain.dto.DTO;
import domain.dto.locale.LocaleDto;
import domain.excel.collection.ExcelFile;
import domain.excel.iterator.LocaleIterator;

import java.nio.file.Path;

/** Class reads the excel file in special directory of the project. The excel table can be multi-language.
 * The excel file must have the following structure:
 *  |  A  |  B  |  C  | ...
 * 1| key | lang| lang| ...
 * 2|label|value|value| ...
 * ...
 * A1 - key column for labels;
 * B1 - localization for this language. Example: rus.
 * C1 - next language.
 * A2 - GUI element (label). Example: MainMenuTitle.
 * B2 - Value of GUI element A2 for lang B1. Example: for label MainMenuTitle and rus column - Главное меню.
 * ... */
public class LocaleExcelReader implements ExcelReader
{
    public DTO read( Path path )
    {
        ExcelFile excelFile = new ExcelFile( path );
        LocaleIterator it = excelFile.getLocaleIterator();

        LocaleDto localeDto = new LocaleDto();

        while( it.hasNextLanguage() )
        {
            String language = it.nextLanguage();
            System.out.println( "lang: " + language );
            while( it.hasNextKey() )
            {
                String key = it.nextKey();
                String value = it.nextValue();

                localeDto.createKey( key );
                localeDto.addValue( key, language, value );

                System.out.println( "\tkey: " + key + "\n\tvalue: " + value );
            }
            it.resetKeyCursor();
            it.resetValueCursor();
        }

        return localeDto;
    }
}

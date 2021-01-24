package domain.excel;


import domain.dto.DTO;
import domain.dto.dictionary.DictionaryDto;
import domain.dto.dictionary.WordDto;
import domain.excel.collection.ExcelFile;
import domain.excel.iterator.DictionaryIterator;

import java.nio.file.Path;

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
public class DictionaryExcelReader implements ExcelReader
{
    public DTO read( Path path )
    {
        ExcelFile excelFile = new ExcelFile( path );
        DictionaryIterator it = excelFile.getDictionaryIterator();

        DictionaryDto dictionaryDto = new DictionaryDto();

        // Dictionary names
        while( it.hasNextLanguage() ) {
            dictionaryDto.addName( it.nextLanguage(), it.nextDictionaryName() );
        }
        it.resetLanguageCursor();

        // Words
        while( it.hasNextWord() && it.hasNextDescription() )
        {
            WordDto word = new WordDto();
            // Add all languages (translations) into word
            while( it.hasNextLanguage() ) {
                String lang = it.nextLanguage();
                word.addWord( lang, it.nextWord() );
                word.addDescription( lang, it.nextDescription() );
            }
            dictionaryDto.addWord( word );
        }

        return dictionaryDto;
    }

    private void debug( DictionaryIterator it )
    {
        // Dictionary names
        System.out.println( "Dictionary name" );
        while( it.hasNextLanguage() )
        {
            System.out.println( it.nextLanguage() + ": " + it.nextDictionaryName() );
        }
        it.resetLanguageCursor();

        // Words
        System.out.println( "Words and descriptions" );
        while( it.hasNextWord() && it.hasNextDescription() )
        {
            System.out.println( "word = new WordDto(); " );
            while( it.hasNextLanguage() )
            {
                System.out.print( "\tlang: " + it.nextLanguage() + " " );
                System.out.println( it.nextWord() + " - " + it.nextDescription() );
            }
            System.out.println( "directory.addWord( word ); " );
        }
    }
}

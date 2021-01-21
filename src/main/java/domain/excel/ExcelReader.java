package domain.excel;


import domain.dto.DictionaryDto;
import domain.dto.WordDto;
import domain.excel.collection.ExcelFile;
import domain.excel.iterator.ExcelIterator;

import java.nio.file.Path;

public class ExcelReader
{
    public DictionaryDto read( Path path)
    {
        ExcelFile excelFile = new ExcelFile( path );
        ExcelIterator it = excelFile.getLineByLineIterator();

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

    private void debug( ExcelIterator it )
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

package domain.excel.iterator;

import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Iterator walks line by line in the excel table. Supports multi-language table but all cells must be filled.
 */
public class LineByLineIterator extends AbstractBaseIterator
{
    public LineByLineIterator( XSSFSheet sheet ) {
        super( sheet );
    }

    @Override
    public String nextLanguage()
    {
        String language = sheet.getRow( LANGUAGE_ROW ).getCell( languageCell ).getStringCellValue();
        languageCell += LANGUAGE_CELL_STEP;
        return language;
    }

    @Override
    public boolean hasNextLanguage() {
        return !isCellEmpty( LANGUAGE_ROW, languageCell );
    }

    @Override
    public void resetLanguageCursor() {
        languageCell = 0;
    }

    @Override
    public String nextDictionaryName() {
        String dictionary = sheet.getRow( DICTIONARY_ROW ).getCell( dictionaryCell ).getStringCellValue();
        dictionaryCell += DICTIONARY_NAME_CELL_STEP;
        return dictionary;
    }

    @Override
    public boolean hasNextDictionaryName() {
        return sheet.getRow( DICTIONARY_ROW ).getCell( dictionaryCell ) != null;
    }

    @Override
    public String nextWord() {
        String word = sheet.getRow( wordRow ).getCell( wordCell ).getStringCellValue();
        wordCell += WORD_CELL_STEP;
        return word;
    }

    @Override
    public boolean hasNextWord() {
        if( isCellEmpty( wordRow, wordCell )) {
            wordRow++;
            wordCell = 0;
            return !isCellEmpty( wordRow, wordCell );
        }
        return true;
    }

    @Override
    public String nextDescription() {
        String description = sheet.getRow( descriptionRow ).getCell( descriptionCell ).getStringCellValue();
        descriptionCell += DESCRIPTION_CELL_STEP;
        return description;
    }

    @Override
    public boolean hasNextDescription() {
        if( isCellEmpty( descriptionRow, descriptionCell )) {
            descriptionRow++;
            descriptionCell = 1;
            resetLanguageCursor();
            return !isCellEmpty( descriptionRow, descriptionCell );
        }
        return true;
    }
}

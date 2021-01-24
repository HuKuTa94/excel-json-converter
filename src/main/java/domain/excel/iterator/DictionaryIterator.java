package domain.excel.iterator;

import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Iterator walks line by line in the excel table. Supports multi-language table but all cells must be filled.
 */
public class DictionaryIterator extends ExcelIterator
{
    private byte dictionaryCell = 1;
    private static final byte DICTIONARY_NAME_CELL_STEP = 2;
    private static final byte DICTIONARY_ROW = 0;

    private short wordCell = 0;
    private short wordRow = 1;
    private static final byte WORD_CELL_STEP = 2;

    private short descriptionCell = 1;
    private short descriptionRow = 1;
    private static final byte DESCRIPTION_CELL_STEP = 2;

    public DictionaryIterator( XSSFSheet sheet ) {
        super( sheet );
        languageCell = 0;
        languageCellStep = 2;
    }

    @Override
    public void resetLanguageCursor() {
        languageCell = 0;
    }

    public String nextDictionaryName() {
        String dictionary = sheet.getRow( DICTIONARY_ROW ).getCell( dictionaryCell ).getStringCellValue();
        dictionaryCell += DICTIONARY_NAME_CELL_STEP;
        return dictionary;
    }

    public boolean hasNextDictionaryName() {
        return sheet.getRow( DICTIONARY_ROW ).getCell( dictionaryCell ) != null;
    }

    public String nextWord() {
        String word = sheet.getRow( wordRow ).getCell( wordCell ).getStringCellValue();
        wordCell += WORD_CELL_STEP;
        return word;
    }

    public boolean hasNextWord() {
        if( isCellEmpty( wordRow, wordCell )) {
            wordRow++;
            wordCell = 0;
            return !isCellEmpty( wordRow, wordCell );
        }
        return true;
    }

    public String nextDescription() {
        String description = sheet.getRow( descriptionRow ).getCell( descriptionCell ).getStringCellValue();
        descriptionCell += DESCRIPTION_CELL_STEP;
        return description;
    }

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

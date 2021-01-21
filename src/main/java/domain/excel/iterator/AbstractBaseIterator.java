package domain.excel.iterator;

import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Base class of excel iterator.
 * */
abstract class AbstractBaseIterator implements ExcelIterator
{
    protected XSSFSheet sheet;

    protected byte languageCell = 0;
    protected static final byte LANGUAGE_CELL_STEP = 2;
    protected static final byte LANGUAGE_ROW = 0;

    protected byte dictionaryCell = 1;
    protected static final byte DICTIONARY_NAME_CELL_STEP = 2;
    protected static final byte DICTIONARY_ROW = 0;

    protected short wordCell = 0;
    protected short wordRow = 1;
    protected static final byte WORD_CELL_STEP = 2;

    protected short descriptionCell = 1;
    protected short descriptionRow = 1;
    protected static final byte DESCRIPTION_CELL_STEP = 2;

    protected AbstractBaseIterator( XSSFSheet sheet ) {
        this.sheet = sheet;
    }

    protected boolean isCellEmpty( short row, short cell ) {
        try {
            return sheet.getRow( row ).getCell( cell ) == null;
        } catch ( NullPointerException ex ) {
            return true;
        }
    }
}

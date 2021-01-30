package domain.excel.iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Base class of excel iterator.
 * */
abstract class ExcelIterator
{
    XSSFSheet sheet;

    protected byte languageCell;
    protected byte languageCellStep;
    protected static final byte LANGUAGE_ROW = 0;

    ExcelIterator( XSSFSheet sheet) {
        this.sheet = sheet;
    }

    boolean isCellEmpty( short rowIndex, short cellIndex ) {
        try {
            Cell cell = sheet.getRow( rowIndex ).getCell( cellIndex );
            return ( cell == null || cell.getCellType() == CellType.BLANK );
        } catch ( NullPointerException ex ) {
            return true;
        }
    }

    public String nextLanguage() {
        String value = sheet.getRow( LANGUAGE_ROW ).getCell( languageCell ).getStringCellValue();
        languageCell++;
        return value;
    }

    public boolean hasNextLanguage(){
        return !isCellEmpty( LANGUAGE_ROW, languageCell );
    }

    public abstract void resetLanguageCursor();
}

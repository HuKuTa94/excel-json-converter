package domain.excel.iterator;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public class LocaleIterator extends ExcelIterator
{
    private final byte KEY_CELL = 0;
    private short keyRow = 1;

    private short valueRow = 1;
    private short valueCell = 1;

    public LocaleIterator( XSSFSheet sheet ) {
        super( sheet );
        languageCell = 1;
    }

    @Override
    public void resetLanguageCursor() {
        languageCell = 1;
    }

    public String nextKey() {
        return sheet.getRow( keyRow++ )
                    .getCell( KEY_CELL )
                    .getStringCellValue();
    }

    public boolean hasNextKey() {
        return !isCellEmpty( keyRow, KEY_CELL );
    }

    public void resetKeyCursor() {
        keyRow = 1;
    }

    public String nextValue() {
        return sheet.getRow( valueRow++ )
                    .getCell( valueCell )
                    .getStringCellValue();
    }

    public boolean hasNextValue() {
        return !isCellEmpty( valueRow, valueCell );
    }

    public void resetValueCursor() {
        valueRow = 1;
        valueCell++;
    }
}

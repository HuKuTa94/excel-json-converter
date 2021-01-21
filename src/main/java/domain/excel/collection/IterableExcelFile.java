package domain.excel.collection;

import domain.excel.iterator.ExcelIterator;

public interface IterableExcelFile
{
    ExcelIterator getLineByLineIterator();
}

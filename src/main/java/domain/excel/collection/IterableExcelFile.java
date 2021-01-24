package domain.excel.collection;

import domain.excel.iterator.DictionaryIterator;
import domain.excel.iterator.LocaleIterator;

public interface IterableExcelFile
{
    DictionaryIterator getDictionaryIterator();
    LocaleIterator getLocaleIterator();
}

package domain.excel.iterator;

public interface ExcelIterator
{
    String nextLanguage();
    boolean hasNextLanguage();
    void resetLanguageCursor();

    String nextDictionaryName();
    boolean hasNextDictionaryName();

    String nextWord();
    boolean hasNextWord();

    String nextDescription();
    boolean hasNextDescription();
}

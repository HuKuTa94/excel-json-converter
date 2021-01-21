package domain.dto;

import java.util.HashMap;

/** Class describes the structure of the json file. This class is included in {@link DictionaryDto} */
public class WordDto
{
    private HashMap<String, String> word;
    private HashMap<String, String> description;

    public WordDto() {
        this.word = new HashMap<>( 2 );
        this.description = new HashMap<>( 2 );
    }

    public WordDto(String wordLanguage, String word, String descriptionLanguage, String description ) {
        this();
        addWord( wordLanguage, word );
        addDescription( descriptionLanguage, description );
    }

    public HashMap<String, String> getWord() {
        return word;
    }

    public HashMap<String, String> getDescription() {
        return description;
    }

    public void addWord(String language, String word ) {
        this.word.put( language, word );
    }

    public void addDescription(String language, String description ) {
        this.description.put( language, description );
    }
}

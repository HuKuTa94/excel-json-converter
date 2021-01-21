package domain.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** The class describes the structure of the JSON file with multi-language localization.
 * Includes {@link WordDto}. See example:
* {
*   "name": {   <- name of dictionary
*       "ru": "Новый год",
*       "en": "New year"
*   },
* 	"words": [  <- array of {@link WordDto}
*        {
* 			"word": {
* 				"ru": "Ёлка",
* 				"en": "Christmas tree"
*            },
* 			"description": {
* 				"ru": "Хвойное дерево с зелёными иголками",
* 				"en": "Coniferous tree with green needles"
*            }
*        },
*        ...
* 	]
* }*/
public class DictionaryDto
{
    private HashMap<String, String> name;
    private ArrayList<WordDto> words;

    public DictionaryDto() {
        this.name = new HashMap<>(2);
        this.words = new ArrayList<>( 100 );
    }

    public HashMap<String, String> getName() {
        return name;
    }

    public List<WordDto> getWords() {
        return words;
    }

    public void addName(String language, String name ) {
        this.name.put( language, name );
    }

    public void addWord( WordDto word ) {
        words.add( word );
    }
}

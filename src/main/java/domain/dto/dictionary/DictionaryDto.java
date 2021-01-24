package domain.dto.dictionary;

import domain.dto.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** The class describes the structure of the JSON file with multi-language localization.
 * Includes {@link WordDto}. See example:
* {
*   "name": {   <- name of dictionary
*       "rus": "Новый год",
*       "eng": "New year"
*   },
* 	"words": [  <- array of {@link WordDto}
*        {
* 			"word": {
* 				"rus": "Ёлка",
* 				"eng": "Christmas tree"
*            },
* 			"description": {
* 				"rus": "Хвойное дерево с зелёными иголками",
* 				"eng": "Coniferous tree with green needles"
*            }
*        },
*        ...
* 	]
* }*/
public class DictionaryDto implements DTO
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

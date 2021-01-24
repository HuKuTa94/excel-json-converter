package domain.dto.locale;

import domain.dto.DTO;

import java.util.HashMap;

/** The class describes the structure of the locale JSON file.
 * {
 *      "languages": {
 *          "MenuTitle": {
 *              "rus": "Крокодил",
 *              "eng": "Crocodile"
 *           },
 *           "MenuNewGame": {
 *              "rus": "Новая игра",
 *              "eng": "New game"
 *           },
 *           ...
 *       }
 * }
 */
public class LocaleDto implements DTO
{
    private HashMap<String, HashMap<String, String>> languages = new HashMap<>();

    public void createKey( String key ) {
        languages.putIfAbsent( key, new HashMap<>()) ;
    }

    public void addValue( String key, String language, String value ) {
        languages.get( key ).put( language, value );
    }

    public String getValue( String key, String language ) {
        return languages.get( key ).get( language );
    }
}

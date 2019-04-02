package javabrain.org.expenditure.api;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by Fernando García on 01/04/2019.
 */

public class JsonApi {

    private static JSONParser parser = new JSONParser();

    public static Object parser(String pars) {
        try {
            return parser.parse(pars);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}

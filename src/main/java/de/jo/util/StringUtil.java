package de.jo.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author CommandJoo 19.05.2023
 * @Project AuroraScript
 */
public class StringUtil {

    public static LinkedList<String> chop(String text) {
        return new LinkedList<>(Arrays.asList(text.split("")));
    }

    public static boolean isIdentifier(String s) {
        return Character.isAlphabetic(s.charAt(0)) || isNumeric(s) || s.charAt(0) == '_';
    }

    public static boolean isNumeric(String s) {
        return Character.isDigit(s.charAt(0));
    }

    public static String jsonify(Object obj) {
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        return g.toJson(obj);
    }

}

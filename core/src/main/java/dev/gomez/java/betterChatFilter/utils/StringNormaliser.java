package dev.gomez.java.betterChatFilter.utils;

import java.util.HashMap;
import java.util.Map;

public final class StringNormaliser {

    private static final HashMap<Character, Character> leetMap = new HashMap<Character, Character>();

    static {
        leetMap.put('4', 'a');
        leetMap.put('@', 'a');
        leetMap.put('8', 'b');
        leetMap.put('3', 'e');
        leetMap.put('6', 'g');
        leetMap.put('9', 'g');
        leetMap.put('1', 'i');
        leetMap.put('!', 'i');
        leetMap.put('|', 'l');
        leetMap.put('0', 'o');
        leetMap.put('5', 's');
        leetMap.put('$', 's');
        leetMap.put('7', 't');
        leetMap.put('+', 't');
        leetMap.put('2', 'z');
    }

    public static String normalise(String message){
        message = message.toLowerCase();

        for (Map.Entry<Character, Character> entry : leetMap.entrySet()){
            message = message.replace(entry.getKey(), entry.getValue());
        }

        message = message.replaceAll("[^\\w ]", "");

        return message;
    }
}

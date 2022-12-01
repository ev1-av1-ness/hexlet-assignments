package exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        if (sentence.length() == 0)
        {
            return new HashMap<>();
        }
        Map<String, Integer> dictionaryOfCountedWords = new HashMap<>();
        List<String> listOfSentenceWords = List.of(sentence.split(" "));

        for (String word : listOfSentenceWords) {
            if (!dictionaryOfCountedWords.containsKey(word)) {
                dictionaryOfCountedWords.put(word, 1);
            } else {
                dictionaryOfCountedWords.put(word, dictionaryOfCountedWords.get(word) + 1);
            }
        }
        return dictionaryOfCountedWords;
    }

    public static String toString(Map<String, Integer> dictionaryOfCountedWords) {
        if (dictionaryOfCountedWords.isEmpty()) {
            return "{}";
        }
        return dictionaryOfCountedWords.keySet().stream()
                .map(key -> key + ": " + dictionaryOfCountedWords.get(key))
                .collect(Collectors.joining("\n  ", "{\n  ", "\n}"));
    }
}
//END

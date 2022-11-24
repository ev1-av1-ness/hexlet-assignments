package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {
    public static boolean scrabble(String letters, String word) {
        List<Character> charsOfLetters = new ArrayList<>();
        for (int i = 0; i < letters.length(); i++) {
            charsOfLetters.add(letters.charAt(i));
        }
        for (Character requiredLetterOfWord : word.toLowerCase().toCharArray()) {
            if (charsOfLetters.contains(requiredLetterOfWord)) {
                charsOfLetters.remove(requiredLetterOfWord);
                //удалить requiredLetter, так как нам нужны ее дубликат
                continue;
            }
            return false; //не содержит requiredLetter
        }
        return true; //так и не попало в false
    }
}
//END

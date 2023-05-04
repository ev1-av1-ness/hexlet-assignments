package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
class App {
    public static <T> List<Map<T, T>> findWhere(List<Map<T, T>> books, Map<T, T> parameters) {
        List<Map<T, T>> result = new ArrayList<>();
        for (Map<T, T> book : books) {
            if (book.entrySet().containsAll(parameters.entrySet())) {
                result.add(book);
            }
        }
        return result;
    }
}
//END

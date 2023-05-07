package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;

// BEGIN
class Sorter {

    public static List<String> takeOldestMans(List<Map<String,String>> users) {
        return users.stream()
                .filter(user -> user.get("gender").equals("male"))
                .sorted(Comparator.comparing(u -> u.get("birthday")))
                .map(user -> user.get("name"))
                .toList();
    }
}
// END

package exercise;

import java.util.List;

// BEGIN
class App {
    public static List<String> buildAppartmentsList (List<Home> apartments, int n) {
        return apartments.stream()
                .sorted(Home::compareTo)
                .limit(n)
                .map(Object::toString)
                .toList();
    }
}
// END

package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {

    public static long getCountOfFreeEmails(List<String> emails) {
        List<String> domains = Arrays.asList("gmail.com", "yandex.ru", "hotmail.com");
        return emails.stream()
                .filter(email -> domains.stream().anyMatch(email::endsWith))
                .count();
    }
}
// END

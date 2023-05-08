package exercise;

import java.util.Arrays;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static String getForwardedVariables(String config) {
        return Arrays.stream(config.split("\n"))
                .filter(s -> s.startsWith("environment"))
                .map(s -> s.replaceAll("environment=|\"", ""))
                .flatMap(s -> Arrays.stream(s.split(",")))
                .filter(s -> s.startsWith("X_FORWARDED_"))
                .map(s -> s.replace("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}
//END

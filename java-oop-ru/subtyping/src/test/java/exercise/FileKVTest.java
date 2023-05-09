package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
// BEGIN

// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    void fileKVTest() {
        FileKV storage = new FileKV("src/test/resources/file", Map.of("key1", "value1"));

        assertThat(storage.get("key2", "default")).isEqualTo("default");
        assertThat(storage.get("key1", "default")).isEqualTo("value1");

        storage.set("key2", "value2");
        storage.set("key3", "value3");

        assertThat(storage.get("key2", "default")).isEqualTo("value2");
        assertThat(storage.get("key3", "default")).isEqualTo("value3");

        storage.unset("key3");
        assertThat(storage.get("key3", "default")).isEqualTo("default");
        assertThat(storage.toMap()).isEqualTo(Map.of("key1", "value1", "key2", "value2"));
    }
    // END
}

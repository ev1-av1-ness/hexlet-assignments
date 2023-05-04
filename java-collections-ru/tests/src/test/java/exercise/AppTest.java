package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {

    List<Integer> numbers;

    @BeforeEach
    void beforeEach() {
        numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        numbers.add(7);
    }

    @Test
    void testTake() {
        // BEGIN
        assertThat(App.take(numbers, 0)).isEmpty();
        assertThat(App.take(numbers, 5)).containsExactly(1, 2, 3, 4, 5);
        assertThat(App.take(numbers, numbers.size())).containsAll(numbers);
        assertThat(App.take(numbers, 9)).containsExactly(1, 2, 3, 4, 5, 6, 7);
        assertThat(App.take(numbers, 5)).hasSize(5);

        numbers = new ArrayList<>();
        assertThat(App.take(numbers, 0)).isEmpty();
        assertThat(App.take(numbers, 5)).isEmpty();
        assertThat(App.take(numbers, 5)).doesNotContainNull();

        numbers.add(-2);
        numbers.add(-1);
        numbers.add(0);

        assertThat(App.take(numbers, 0)).isEmpty();
        assertThat(App.take(numbers, 3)).containsExactly(-2, -1, 0);
        // END
    }
}

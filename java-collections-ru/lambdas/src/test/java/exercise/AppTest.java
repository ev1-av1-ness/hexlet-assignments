package exercise;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

// BEGIN
class AppTest {
    @Test
    public void testEnlargingImage() {
        String[][] image = {
                {"+", "*"},
                {"!", "?"}
        };
        String[][] expectedResult = {
                {"+", "+", "*", "*"},
                {"+", "+", "*", "*"},
                {"!", "!", "?", "?"},
                {"!", "!", "?", "?"}
        };
        String[][] result = App.enlargeArrayImage(image);
        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void testEnlargingReadMeExampleImage() {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };
        String[][] expectedResult = {
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"}
        };
        String[][] result = App.enlargeArrayImage(image);
        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void testEnlargingWithoutSymmetryImage() {
        String[][] image = {
                {"*", "*"},
                {" ", "*"},
                {" ", "*"},
                {"*", "*"},
        };
        String[][] expectedResult = {
                {"*", "*", "*", "*"},
                {"*", "*", "*", "*"},
                {" ", " ", "*", "*"},
                {" ", " ", "*", "*"},
                {" ", " ", "*", "*"},
                {" ", " ", "*", "*"},
                {"*", "*", "*", "*"},
                {"*", "*", "*", "*"},
        };;
        String[][] result = App.enlargeArrayImage(image);
        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void testEnlargingEmptyImage() {
        String[][] image = {};
        String[][] expectedResult = {};
        String[][] result = App.enlargeArrayImage(image);
        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void testEnlargingSinglePixelImage() {
        String[][] image = {{"*"}};
        String[][] expectedResult = {{"*", "*"}, {"*", "*"}};
        String[][] result = App.enlargeArrayImage(image);
        assertArrayEquals(expectedResult, result);
    }
}
// END

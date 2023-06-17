package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {

    private int[] numbers;
    private int min;

    public MinThread (int[] numbers) {
        this.numbers = numbers;
    }

    public int getMin() {
        return min;
    }

    @Override
    public void run() {
        Arrays.sort(numbers);
        this.min = numbers[0];
    }
}
// END

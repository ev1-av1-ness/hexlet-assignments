package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {

    private int[] numbers;
    private int max;

    public MaxThread (int[] numbers) {
        this.numbers = numbers;
    }

    public int getMax() {
        return max;
    }

    @Override
    public void run() {
        Arrays.sort(numbers);
        this.max = numbers[numbers.length - 1];
    }
}
// END

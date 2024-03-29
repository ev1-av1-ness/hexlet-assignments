package exercise;

// BEGIN
public class ReversedSequence implements CharSequence{
    String text;

    public ReversedSequence(String input) {
        StringBuilder s = new StringBuilder(input);
        this.text = s.reverse().toString();
    }

    @Override
    public int length() {
        char[] result = this.text.toCharArray();
        return result.length;
    }

    @Override
    public char charAt(int i) {
        char[] result = this.text.toCharArray();
        return result[i];
    }

    public String toString() {
        return this.text;
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        char[] toArray = this.text.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int j = i; j < i1; j++) {
            result.append(toArray[j]);
        }
        return result.toString();
    }
}
// END

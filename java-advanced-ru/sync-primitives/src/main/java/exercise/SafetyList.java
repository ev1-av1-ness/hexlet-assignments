package exercise;

class SafetyList {
    // BEGIN
    private int size;
    private Object[] values;

    private static final int DEFAULT_SIZE = 10;

    public SafetyList() {
        values = new Object[DEFAULT_SIZE];
    }

    public SafetyList(int customSize) {
        values = new Object[customSize];
    }


    public synchronized void add(Object o) {
        if (size == values.length) {
            Object[] oldValues = this.values;
            values = new Object[size * 2];
            System.arraycopy(oldValues, 0, values, 0, oldValues.length);
        }
        values[size++] = o;
    }


    public Object get(int i) {
        if (i < size && i >= 0) {
            return values[i];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }


    public int getSize() {
        return this.size;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Object value : values) {
            if (value != null) {
                result.append(value);
                result.append(" ");
            }
        }
        return result.toString().trim();
    }
    // END
}

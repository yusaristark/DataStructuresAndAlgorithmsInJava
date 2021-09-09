package Chapter06Recursion;

public class Task3 {
    public static void main(String[] args) {
        System.out.print(power(2, 5));
    }

    private static long power(long value, long x) {
        if (x == 0)
            return 1;
        return value * power(value, x - 1);
    }
}

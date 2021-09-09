package Chapter06Recursion;

public class Task1 {
    public static void main(String[] args) {
        System.out.print(mult(5, 8));
    }

    private static long mult(long x, long y) {
        if (y == 1)
            return x;
        else
            return x + mult(x, y - 1);
    }
}

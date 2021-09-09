package Chapter06Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TriangleApp {
    public static void main(String[] args) throws IOException {
        System.out.print("Enter a number: ");
        System.out.println("Triangle=" + triangle(getInt()));
    }

    public static int triangle(int n) {
        if (n == 1)
            return 1;
        else
            return n + triangle(n - 1);
    }

    public static String getString() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public static int getInt() throws IOException {
        return Integer.parseInt(getString());
    }
}

import java.io.*;
public class Tribonacci {
    public static void main(String[] args) {
        for(int i=0; i < 51; i++) {
            System.out.println(t(i));
        };
    }

    public static long t(long n) {
        return ((2 < n)? t(n-1) + t(n-2) + t(n-3) : ( (0 < n)? 1 : 0));
    }
}
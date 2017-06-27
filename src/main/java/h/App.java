package h;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by a on 27.06.17.
 */
public class App {
    private final static int QUANTITY = 4;
    /*
    todo заменить 10
     */
    public static void main(String[] args) {
        int sum0 = 0, sum1 = 0;
        int[] arr = new int[10];
        int[] res = new int[1022];
        for (int i = 0; i < 10; i++) {
            arr[i] = new Random().nextInt(10);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(arr[i] + " ");
        }
        for (int n = 1; n < 1023; n++) {

            for (int b = 0; b < 10; b++) {
//                System.out.println(getBit(i, b));
                if (getBit(n, b) == 0) {
                    sum0 += arr[b];
                } else if (getBit(n, b) == 1) {
                    sum1 += arr[b];
                }
            }
            res[n-1] = Math.abs(sum1 - sum0);
        }
        System.out.println(IntStream.of(res).min().getAsInt());
    }

    public static int getBit(int n, int b) {
        return (n >> b) & 1;
    }
}

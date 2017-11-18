package h;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Решаем перебором. Длину массива -- в двоичное представление: 0 -первая группа
 * 1 - вторая
 */
public class App {
    private final static int QUANTITY = 4;
    private final static int NUMBER_BIN = 15; // Integer.parseInt("1111",2)

    public static void main(String[] args) {
        int sum0 = 0, sum1 = 0;
        int[] arr = new int[QUANTITY];
        int[] res = new int[NUMBER_BIN];            //
        for (int i = 0; i < QUANTITY; i++) {
            arr[i] = new Random().nextInt(10);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        for (int n_b = 1; n_b <= NUMBER_BIN; n_b++) {

            for (int n_d = 0; n_d < arr.length; n_d++) {
                if (getBit(n_b, n_d) == 0) {
                    sum0 += arr[n_d];
                } else if (getBit(n_b, n_d) == 1) {
                    sum1 += arr[n_d];
                }
            }
            res[n_b-1] = Math.abs(sum1 - sum0);
            sum0 = sum1 = 0;
        }
        System.out.println("\n" + IntStream.of(res).min().getAsInt());
    }

    public static int getBit(int n_b, int n_d) {
        return (n_b >> n_d) & 1;
    }
}

package h;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Решаем перебором. Длину массива -- в двоичное представление: 0 -первая группа
 * 1 - вторая
 */
public class App {
    private final static int QUANTITY = 4;
    private final static int NUMBER = 15; // Integer.parseInt("1111",2)

    public static void main(String[] args) {
        int sum0 = 0, sum1 = 0;
        int[] arr = new int[QUANTITY];
        int[] res = new int[NUMBER];            //
        for (int i = 0; i < QUANTITY; i++) {
            arr[i] = new Random().nextInt(10);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        for (int n = 1; n <= NUMBER; n++) {

            for (int b = 0; b < arr.length; b++) {
                if (getBit(n, b) == 0) {
                    sum0 += arr[b];
                } else if (getBit(n, b) == 1) {
                    sum1 += arr[b];
                }
            }
            res[n-1] = Math.abs(sum1 - sum0);
            sum0 = sum1 = 0;
        }
        System.out.println("\n" + IntStream.of(res).min().getAsInt());
    }

    public static int getBit(int n, int b) {
        return (n >> b) & 1;
    }
}

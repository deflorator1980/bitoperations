import java.util.Random;
import java.util.stream.IntStream;

/**
 * Решаем перебором. Длинa массива -- количество бит -- количество комбинаций
 * -- в десятичное представление: 0 -первая группа, 1 - вторая
 */
public class App {
    private final static int QUANTITY = 4;
    private final static int NUMBER_DEC = 15; // Integer.parseInt("1111",2)

    public static void main(String[] args) {
        int sum0 = 0, sum1 = 0;
        int[] arr = new int[QUANTITY];
        int[] res = new int[NUMBER_DEC];            //
        for (int i = 0; i < QUANTITY; i++) {
            arr[i] = new Random().nextInt(10);
        }
        // int[] arr = {4,5,7,1};

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        for (int n_dec = 1; n_dec <= NUMBER_DEC; n_dec++) {

            for (int n_bin = 0; n_bin < arr.length; n_bin++) {
                if (getBit(n_dec, n_bin) == 0) {
                    sum0 += arr[n_bin];
                } else if (getBit(n_dec, n_bin) == 1) {
                    sum1 += arr[n_bin];
                }
            }
            res[n_dec-1] = Math.abs(sum1 - sum0);
            sum0 = sum1 = 0;
        }
        System.out.println("\n" + IntStream.of(res).min().getAsInt());

        // for (int i = 0; i < res.length; i++) {
        //     System.out.print(res[i] + " ");
        // }
    }

    public static int getBit(int n_dec, int n_bin) {
        return (n_dec >> n_bin) & 1;
    }
}
//todo IntStream.range(0,67108863).forEach(res::add)
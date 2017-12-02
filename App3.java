import java.util.Random;
import java.util.stream.IntStream;
import java.util.Date;

public class App3 {
    public static void main(String[] args) {
        int quantity = Integer.parseInt(args[0]);
        int numberDec = (int)Math.pow(2, quantity) - 1;
        System.out.println(numberDec);
        int sum0 = 0, sum1 = 0;
        int[] arr = new int[quantity];
        for (int i = 0; i < quantity; i++) {
            arr[i] = new Random().nextInt(10);
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        int diffenece = Integer.MAX_VALUE;  

        System.out.println("\n" + new Date());

        for (int n_dec = 1; n_dec <= numberDec; n_dec++) {

            for (int n_bin = 0; n_bin < arr.length; n_bin++) {
                if (getBit(n_dec, n_bin) == 0) {
                    sum0 += arr[n_bin];
                } else if (getBit(n_dec, n_bin) == 1) {
                    sum1 += arr[n_bin];
                }
            }

            int dif = Math.abs(sum1 - sum0);

            if (dif < diffenece) {
                diffenece = dif;
            }

            sum0 = sum1 = 0;
        }
        System.out.println(diffenece);
        System.out.println(new Date());
        
    }

    public static int getBit(int n_dec, int n_bin) {
        return (n_dec >> n_bin) & 1;
    }
}
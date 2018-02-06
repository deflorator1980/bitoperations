import java.util.Arrays;
import java.util.Random;

public class AppKnapSack {

    public static void main(String args[]) {

        int quantity = Integer.parseInt(args[0]);
        long[] arr = new long[quantity];
        for (int i = 0; i < quantity; i++) {
            arr[i] = new Random().nextInt(10);
        }

        Arrays.stream(arr).forEach(value -> System.out.print(value + " "));
        int sum = (int) Arrays.stream(arr).reduce(0, Long::sum);
        System.out.println("\nsum " + sum);
        int W = sum / 2;
        System.out.println("W " + W);
        int n = arr.length;
        int one = knapSack(W, arr, n);
        System.out.println("one " + one);
        System.out.println("result " + (sum - (one * 2)));
    }

    static int knapSack(int W, long arr[], int n) {
        int K[][] = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (arr[i - 1] <= w)
                    K[i][w] = (int) Math.max(arr[i - 1] + K[i - 1][(int) (w - arr[i - 1])], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }
        return K[n][W];
    }
}

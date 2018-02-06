import java.util.Arrays;
import java.util.Random;

public class AppKnapSack {

    public static void main(String args[]) {

        int quantity = Integer.parseInt(args[0]);
        long[] arr = new long[quantity];
        long sum0 = 0, sum1 = 0;
        for (int i = 0; i < quantity; i++) {
            arr[i] = new Random().nextInt(10);
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        long val[] = arr;
        long wt[] = arr;
        int sum = (int) Arrays.stream(arr).reduce(0, Long::sum);
        System.out.println("sum " + sum);
        int W = sum / 2;
        System.out.println("W " + W);
        int n = val.length;
        int one = knapSack(W, wt, val, n);
        System.out.println("one " + one);
        System.out.println("result " + (sum - (one * 2)));
    }

    static int knapSack(int W, long wt[], long val[], int n) {
        int i, w;
        int K[][] = new int[n + 1][W + 1];

        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w] = (int) Math.max(val[i - 1] + K[i - 1][(int) (w - wt[i - 1])], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        return K[n][W];
    }
}

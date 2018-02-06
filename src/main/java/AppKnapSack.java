import java.util.Arrays;
import java.util.Random;

public class AppKnapSack {

    public static void main(String args[]) {
//        int val[] = new int[]{60, 100, 120};
//        int wt[] = new int[]{10, 20, 30};
//        int W = 30;

//        int val[] = {10, 40, 30, 50};
//        int wt[] = {5, 4, 6, 3};
//        int W = 10;

        int quantity = Integer.parseInt(args[0]);
//        int quantity = 5;
        long[] arr = new long[quantity];
//        long numberDec = (long)Math.pow(2, quantity) - 1;
//        System.out.println(numberDec);
        long sum0 = 0, sum1 = 0;
        for (int i = 0; i < quantity; i++) {
            arr[i] = new Random().nextInt(10);
        }


        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

//        int val[] = {6, 9, 4, 1,4};
//        int wt[] = {6, 9, 4, 1,4};
        long val[] = arr;
        long wt[] = arr;
        int sum = (int) Arrays.stream(arr).reduce(0, Long::sum);
        System.out.println("sum " + sum);
        int W = sum / 2;
        System.out.println("W " + W);
        int n = val.length;
//        System.out.println(knapSack(W, wt, val, n));
        int one = knapSack(W, wt, val, n);
        System.out.println("one " + one);
        System.out.println("result " + (sum - (one * 2)));
    }

    //    static int knapSack(int W, int wt[], int val[], int n) {
    static int knapSack(int W, long wt[], long val[], int n) {
        int i, w;
        int K[][] = new int[n + 1][W + 1];

        // Build table K[][] in bottom up manner
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

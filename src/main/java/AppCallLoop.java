import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class AppCallLoop {
    public static void main(String[] args) throws Exception {
        long quantity = Long.parseLong(args[0]);
        long[] arr = new long[(int)quantity];
        long numberDec = (long)Math.pow(2, quantity) - 1;
        System.out.println(numberDec);
        long sum0 = 0, sum1 = 0;
        for (int i = 0; i < quantity; i++) {
            arr[i] = new Random().nextInt(10);
        }


        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        int cores = Runtime.getRuntime().availableProcessors();

        System.out.println("\n" + new Date());

        ExecutorService exec = Executors.newFixedThreadPool(cores);

        // long part = numberDec/4;
        long part = numberDec/cores;

        // Callable<Integer> callable = new CallCount(arr, 1, numberDec);
        // Callable<Integer> callable = new CallCount(arr, 1, numberDec/2);

        // Callable<Integer> callable = new CallCount(numberDec, arr);

        List<Callable<Integer>> calls = new ArrayList<>();
        calls.add(new CallCount(arr, 1, part));

        List<Future<Integer>> futures = new ArrayList<>();
        futures.add(exec.submit(new CallCount(arr, 1, part)));

        if (cores > 1) {
            for (int i = 1; i < cores; i++ ){
                futures.add(exec.submit(new CallCount(arr, part + i, part * (i + 1))));
            }

        }

        for (Future f : futures) {
            System.out.println(f.get());
        }



        // Callable<Integer> callable = new CallCount(arr, 1, part);
        // Callable<Integer> callable2 = new CallCount(arr, part + 1, part * 2);
        // Callable<Integer> callable3 = new CallCount(arr, (part * 2) + 1, part * 3);
        // Callable<Integer> callable4 = new CallCount(arr, (part * 3) + 1, part * 4);
        // Callable<Integer> callable5 = new CallCount(arr, (part * 4) + 1, part * 5);
        // Callable<Integer> callable6 = new CallCount(arr, (part * 5) + 1, part * 6);
        // Callable<Integer> callable7 = new CallCount(arr, (part * 6) + 1, part * 7);
        // Callable<Integer> callable8 = new CallCount(arr, (part * 7) + 1, numberDec);
        // Future<Integer> future = exec.submit(callable);
        // Future<Integer> future2 = exec.submit(callable2);
        // Future<Integer> future3 = exec.submit(callable3);
        // Future<Integer> future4 = exec.submit(callable4);
        // Future<Integer> future5 = exec.submit(callable5);
        // Future<Integer> future6 = exec.submit(callable6);
        // Future<Integer> future7 = exec.submit(callable7);
        // Future<Integer> future8 = exec.submit(callable8);
        // // diffenece = future.get();

        // System.out.println(future.get());
        // System.out.println(future2.get());
        // System.out.println(future3.get());
        // System.out.println(future4.get());
        // System.out.println(future5.get());
        // System.out.println(future6.get());
        // System.out.println(future7.get());
        // System.out.println(future8.get());

        System.out.println(new Date());
        exec.shutdown();

    }

}

class CallCount implements Callable {

    private long[] arr;
    private  long diffenece = Long.MAX_VALUE;
    // private  long diffenece = 0;

    private long sum0 = 0, sum1 = 0;
    private long start;
    private long stop;

    public CallCount(long[] arr, long start, long stop) {
        this.arr = arr;
        this.start = start;
        this.stop = stop;
    }

    public Integer call() {
        for (long nDec = start; nDec <= stop; nDec++) {

            for (int nBin = 0; nBin < arr.length; nBin++) {
                if (getBit(nDec, nBin) == 0) {
                    sum0 += arr[nBin];
                } else if (getBit(nDec, nBin) == 1) {
                    sum1 += arr[nBin];
                }
            }

            long dif = Math.abs(sum1 - sum0);

            // if(dif == 0) {
            //     System.out.println("zero " + Thread.currentThread().getName());
            //     System.out.println(new Date());
            //     System.exit(0);
            // }

            if (dif < diffenece) {
                // if (dif > diffenece) {

                // System.out.println(Thread.currentThread().getName() + " " + dif);
                diffenece = dif;
            }

            sum0 = sum1 = 0;
        }
        return (int)diffenece;
    }

    public long getBit(long nDec, long nBin) {
        return (nDec >> nBin) & 1;
    }

}

// Runtime.getRuntime().availableProcessors();
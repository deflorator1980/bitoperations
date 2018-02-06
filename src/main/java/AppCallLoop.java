import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class AppCallLoop {
    /**
     * 28 -- pretty quick
     * @param args
     * @throws Exception
     */
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

        long part = numberDec/cores;

        List<Callable<Integer>> calls = new ArrayList<>();
        calls.add(new CallCount(arr, 1, part));

        List<Future<Integer>> futures = new ArrayList<>();
        futures.add(exec.submit(new CallCount(arr, 1, part)));

        if (cores > 1) {
            for (int i = 1; i < cores; i++ ){
                futures.add(exec.submit(new CallCount(arr, (part * i) + 1 , part * (i + 1))));
            }

        }

        for (Future f : futures) {
            System.out.println(f.get());
        }

        System.out.println(new Date());
        exec.shutdown();

    }

}

class CallCount implements Callable {

    private long[] arr;
    private  long diffenece = Long.MAX_VALUE;

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

            if (dif < diffenece) {
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

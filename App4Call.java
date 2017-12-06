import java.util.Random;
import java.util.stream.IntStream;
import java.util.Date;
import java.util.concurrent.*;

public class App4Call {
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

        long diffenece = Long.MAX_VALUE;  

        System.out.println("\n" + new Date());

        ExecutorService exec = Executors.newFixedThreadPool(4);

        Callable<Integer> callable = new CallCount(numberDec, arr);
        Future<Integer> future = exec.submit(callable);
        diffenece = future.get();

        System.out.println(diffenece);
        System.out.println(new Date());
        exec.shutdown();
        
    }

}

class CallCount implements Callable {

    private long numberDec;
    private long[] arr;
    private  long diffenece = Long.MAX_VALUE;
    private long sum0 = 0, sum1 = 0;

    public CallCount(long numberDec, long[] arr) {
        this.numberDec = numberDec;
        this.arr = arr;
    }

    public Integer call() {
        for (long nDec = 1; nDec <= numberDec; nDec++) {
            
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
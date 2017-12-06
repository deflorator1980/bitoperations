// import java.util.Random;
// import java.util.stream.IntStream;
// import java.util.Date;
// import java.util.concurrent.*;

// public class AppCall {
//     public static void main(String[] args) throws Exception {
//         long quantity = Long.parseLong(args[0]);
//         long[] arr = new long[(int)quantity];
//         long numberDec = (long)Math.pow(2, quantity) - 1;
//         System.out.println(numberDec);
//         long sum0 = 0, sum1 = 0;
//         for (int i = 0; i < quantity; i++) {
//             arr[i] = new Random().nextInt(10);
//         }


//         for (int i = 0; i < arr.length; i++) {
//             System.out.print(arr[i] + " ");
//         }

//         long diffenece = Long.MAX_VALUE;  

//         System.out.println("\n" + new Date());

//         ExecutorService exec = Executors.newFixedThreadPool(4);

//         long part = numberDec/4;
//         // Callable<Integer> callable = new CallCount(arr, 1, numberDec);
//         // Callable<Integer> callable = new CallCount(arr, 1, numberDec/2);

//         Callable<Integer> callable = new CallCount(arr, 1, part);
//         Callable<Integer> callable2 = new CallCount(arr, part + 1, part * 2);
//         Callable<Integer> callable3 = new CallCount(arr, (part * 2) + 1, part * 3);
//         Callable<Integer> callable4 = new CallCount(arr, (part * 3) + 1, numberDec);
//         Future<Integer> future = exec.submit(callable);
//         Future<Integer> future2 = exec.submit(callable2);
//         Future<Integer> future3 = exec.submit(callable3);
//         Future<Integer> future4 = exec.submit(callable4);
//         // diffenece = future.get();
//         System.out.println(future.get());
//         System.out.println(future2.get());
//         System.out.println(future3.get());
//         System.out.println(future4.get());

//         System.out.println(new Date());
//         exec.shutdown();
        
//     }

// }

// class CallCount implements Callable {

//     private long numberDec;
//     private long[] arr;
//     private  long diffenece = Long.MAX_VALUE;
//     // private  long diffenece = 0;

//     private long sum0 = 0, sum1 = 0;
//     private long start;
//     private long stop;

//     public CallCount(long[] arr, long start, long stop) {
//         this.numberDec = numberDec;
//         this.arr = arr;
//         this.start = start;
//         this.stop = stop;
//     }

//     public Integer call() {
//         for (long nDec = start; nDec <= stop; nDec++) {
            
//             for (int nBin = 0; nBin < arr.length; nBin++) {
//                 if (getBit(nDec, nBin) == 0) {
//                     sum0 += arr[nBin];
//                 } else if (getBit(nDec, nBin) == 1) {
//                     sum1 += arr[nBin];
//                 }
//             }

//             long dif = Math.abs(sum1 - sum0);

//             if (dif < diffenece) {
//             // if (dif > diffenece) {

//                 // System.out.println(Thread.currentThread().getName() + " " + dif);
//                 diffenece = dif;
//             }

//             sum0 = sum1 = 0;
//         }
//         return (int)diffenece;
//     }

//     public long getBit(long nDec, long nBin) {
//         return (nDec >> nBin) & 1;
//     }

// }
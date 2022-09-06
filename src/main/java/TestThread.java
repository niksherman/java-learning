

// Semaphore
// 10 tests need to run in parallel and be thread safe


import java.util.concurrent.*;

public class TestThread extends Thread {

    int val = 0;
    int val1 = 1;
    Object o = new Object();
    Object p = new Object();
    int oVal = 0;
    public String test1() {

        synchronized (o) {
            oVal++;
            val++;
            System.out.println(val);
            System.out.println("Thread number" + currentThread().getId());
            System.out.println("OVal ++ "+oVal);
        }
        return "true";
    }
    public String test2() {
        val1++;
//        System.out.println("Running test2" + val1);
//        System.out.println("Thread number" + currentThread().getId());
        return "false";
    }

    public String test3() {
        return "true";
    }
    public String test4() {
        return "false";
    }
    public String test5() {
        return "false";
    }

    public static void main(String[] args) {
    ExecutorService exec = Executors.newFixedThreadPool(5);
//executor.submit(() -> {
//    new Task();
        TestThread test = new TestThread();
        for (int i = 0; i < 25; i++) {
            Future X = exec.submit(() -> {
                test.test1();
                test.test2();
            });
            try {
                X.get();
            } catch (Exception e) {
                System.out.println("Exception = " + e);
            }
        }
        exec.shutdown();
    }

}


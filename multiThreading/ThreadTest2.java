package multiThreading;

public class ThreadTest2 {

    public static void main(String[] args) {

        //main主线程与子线程同时进行
        for(int i = 0;i < 20;i++) {
            final int n = i;

            //new Thread 稍微有点耗时
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(n);
                }
            });
            t.start();
        }


        System.out.println("OK");
        //ok  1--19 都是随机打印，主线程子线程同时进行

    }
}

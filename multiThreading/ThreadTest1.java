package multiThreading;

public class ThreadTest1 {

    public static void main(String[] args) {

        for(int i = 0;i < 20;i++) {
            final int n = i;
            //子线程休眠3s之后，同时执行：无序的执行（系统调度）
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);//休眠3s
                        System.out.println(n);//匿名内部类要是用外部的变量，必须是final修饰
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }

        //main线程和子线程同时执行
        System.out.println("OK");
        //先打印OK，因为OK是主线程，所以先打印OK，等了三秒钟，
        // 然后运行并发的20个进程，随机的打印1---19，无序
    }
}

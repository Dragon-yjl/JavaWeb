package threadMethod;

/**
 * 自己设计代码来模拟实现中断一个线程
 */

public class StopThreadTest {


    public static volatile boolean STOP = false;

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 10000 && !STOP;i++) {
                    System.out.println(i);
                    try {
                        //模拟中断线程
                        Thread.sleep(1000);
                        //通过标志位自行实现，无法解决线程阻塞导致无法中断
                        //Thread.sleep(100000),当出现这样的代码，就无法中断
                        //因为休眠时间过长，根本进不去for循环判断！STOP这段代码，导致无法中断
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        System.out.println("t start");
        //模拟，t执行了5s，还没有结束,要中断，停止t线程
        Thread.sleep(5000);

        STOP = true;
        System.out.println("t stop");
    }
}

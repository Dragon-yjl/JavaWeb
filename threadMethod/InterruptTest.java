package threadMethod;

public class InterruptTest {

    /**
     *Thread.interrupted();返回当前线程中断标志位，然后重置中断标志位
     */

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //第一种：中断以后，停止程序
//                try {
//                for(int i = 0;i < 10000 && !Thread.currentThread().isInterrupted();i++) {
//                    System.out.println(i);
//
//                    //模拟中断线程
//                    Thread.sleep(1000);
//                    //通过标志位自行实现，无法解决线程阻塞导致无法中断
//                    //Thread.sleep(100000),当出现这样的代码，就无法中断
//                    //因为休眠时间过长，根本进不去for循环判断！STOP这段代码，导致无法中断
//                }
//                } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

            //中断以后，继续执行

                    for(int i = 0;i < 10000 && !Thread.currentThread().isInterrupted();i++) {
                        System.out.println(i);

                        //模拟中断线程
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //通过标志位自行实现，无法解决线程阻塞导致无法中断
                        //Thread.sleep(100000),当出现这样的代码，就无法中断
                        //因为休眠时间过长，根本进不去for循环判断！STOP这段代码，导致无法中断
                    }


            }

        });
        t.start();//线程启动，中断标志位为false

        System.out.println("t start");
        //模拟，t执行了5s，还没有结束,要中断，停止t线程
        Thread.sleep(5000);

        //如果t线程处于阻塞状态，会抛出InterruptedException
        t.interrupt();//
        // 告诉t线程，要中断了(设置t线程的中断标志位为true)，由t的代码自行决定是否中断


        System.out.println("t stop");
    }
}

package thread_1028;


public class SynchronizedTest {

    /**
     * 有一个教室，座位有50个，同时有三个老师安排同学座位
     * 每个老师安排100个同学到这个教室，模拟多线程实现
     * 座位编号1-50/0-49，三个线程同时启动来安排同学
     * 同学可以循环操作安排，直到座位安排满
     */

    private static int SITCOUNT = 50;

    public static void main(String[] args) {

        Runnable r = new Runnable() {
            @Override
            public void run() {

                while (SITCOUNT > 0) {
                    synchronized (SynchronizedTest.class) {
                        System.out.println(Thread.currentThread().getName() + "老师安排"+SITCOUNT--+"号学生坐下");
                    }
                    try {
                        Thread.sleep(200);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(r,"郑").start();
        new Thread(r,"高").start();
        new Thread(r,"陈").start();
    }

}

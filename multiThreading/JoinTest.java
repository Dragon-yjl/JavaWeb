package multiThreading;

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {

        Thread t= new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t");
            }
        });
        t.start();
        t.join();//当前线程无条件等待t线程全部执行完在执行当前线程

        System.out.println("main");
        //先打印t在打印main
    }
}

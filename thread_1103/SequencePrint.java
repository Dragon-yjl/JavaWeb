package thread_1103;

public class SequencePrint {

    /**
     * 有三个线程，线程名为a ， b ，c
     * 每个线程打印自己的名称
     * 需要让他们同时启动，按c，b，a打印
     *
     */
    public static void main(String[] args) {

        Thread c = new Thread(()->{//兰姆达表达式
            System.out.println(Thread.currentThread().getName());
        },"C");
        Thread b = new Thread(()->{//兰姆达表达式
            try {
                c.join();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B");
        Thread a = new Thread(()->{//兰姆达表达式
            try {
                b.join();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A");

        a.start();
        b.start();
        c.start();
    }
}

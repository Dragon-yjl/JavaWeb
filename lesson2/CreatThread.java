package lesson2;

/**
 * thread类是JVM用来管理线程的一个类，换句话说，每个线程都有唯一的
 * Thread对象与之相关联
 * java来说 至少有一个非守护线程还没终止，进程就不会结束
 */
public class CreatThread {

    public static void main(String[] args) {

        Runnable r = new Runnable() {//任务描述的可执行类：传入线程对象的构造方法
            @Override
            public void run() {//线程运行态时，执行

            }
        };
        Thread t = new Thread(r,"子线程A");
        t.setDaemon(true);//设置守护线程为true
        t.start();

        System.out.println(t.getId());
        System.out.println(t.getName());
        System.out.println(t.getPriority());
        System.out.println(t.getState());
        System.out.println(t.isAlive());
        System.out.println(t.isDaemon());
        System.out.println(t.isInterrupted());

        //合并的代码
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        },"子线程B").start();


        //lambda表达式  runnable只有一个接口方法，可以直接用lambda表达式
        new Thread(() -> {
            System.out.println();//和run()方法里面写代码一样的效果
        },"子线程C").start();
    }
}

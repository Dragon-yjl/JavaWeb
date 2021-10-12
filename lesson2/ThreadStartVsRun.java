package lesson2;

/**
 * start()  与  run()
 */
public class ThreadStartVsRun {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                }
            }
        }).run();
    }
    /**
     * 如果是run()
     * main线程直接调用Thread对象的run方法，会直接在main线程
     * 运行Thread对象得到run() ---> 传入的runnable对象.run()
     * 结果；main线程直接运行while(true)，有main线程，无子线程
     * 如果是start()
     * main线程就终止了，无main线程 有子线程
     */
}

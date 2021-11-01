package thread1101;

public class SequencePrint {

    public static void main(String[] args) {
        /**
         * 有三个线程，每个线程只能打印A或B或C
         * 要求：同时执行三个线程，按CBA顺序打印
         */

        Thread c = new Thread(new PrintTask("C",null));
        Thread b = new Thread(new PrintTask("B",c));
        Thread a = new Thread(new PrintTask("A",b));

        /**
         * 打印c的时候加入一个null，
         * 执行b线程的时候吧c线程传进去，先让c执行
         * 执行a线程的时候吧b传进去，先让b线程传进去
         */
        a.start();
        b.start();
        c.start();
    }


    private static class PrintTask implements Runnable{

        private String content;
        private Thread joinTask;

        public PrintTask(String content, Thread joinTask) {
            this.content = content;
            this.joinTask = joinTask;
        }

        @Override
        public void run() {
            try {
                if(joinTask != null)
                    joinTask.join();
                System.out.println(content);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

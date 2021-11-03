package thread_1103;


 /**
 * 有三个线程，线程名为a ， b ，c
 * 每个线程打印自己的名称
 * 需要让他们同时启动，按A,B,C打印10次,
 *
 */


public class SequencePrint2 {

     public static void main(String[] args) {

         Thread a = new Thread(new Task("A"));
         Thread b = new Thread(new Task("B"));
         Thread c = new Thread(new Task("C"));
         c.start();
         b.start();
         a.start();
     }


     private static class Task implements Runnable{

         private String content;
         //顺序打印的内容，可以循环打印
         private static String[] ARR = {"A","B","C"};
         private static int INDEX;//从数组那个索引开始打印

         public Task(String content) {
             this.content = content;
         }

         @Override
         public void run() {

             try {
                 for(int i = 0;i < 10;i++) {
                     synchronized (ARR) {//三个线程使用同一把锁
                         //从数组索引位置打印，如果当前线程要打印的内容不一致，释放对象锁
                         while (!content.equals(ARR[INDEX])) {
                             ARR.wait();
                         }
                         //如果数组要打印的内容和当前线程要打印的一致，就打印，
                         // 然后把数组索引切换到下一个位置，并通知其他线程
                         System.out.print(content);
                         if(INDEX == ARR.length-1)
                             System.out.println();
                         INDEX = (INDEX+1)%ARR.length;
                         ARR.notifyAll();
                     }
                 }
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     }
}

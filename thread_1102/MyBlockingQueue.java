package thread_1102;

/**
 * 实现阻塞队列：
 * 1.线程安全问题：在多线程下，put，take不具有原子性，4个属性，不具有可见性
 * 2.put操作，如果存满了，需要阻塞等待 ，take如果是空，需要阻塞等待
 * @param <T>
 */

public class MyBlockingQueue<T> {

    //使用数组实现循环队列
    private Object[] queue;

    /**
     * 存放元素的索引
     */
    private int putIndex;

    /**
     * 取元素的索引
     */
    private int takeIndex;

    /**
     * 当前存放元素的数量
     */
    private int size;


    public MyBlockingQueue(int len) {
        queue = new Object[len];
    }

    /**
     * 存放元素
     * @param e
     * 需要考虑：1.putIndex超过数组的长度
     *          2.size达到数组最大长度
     */
    public synchronized void put(T e) throws InterruptedException {
        //当我们阻塞等待到被唤醒并再次竞争成功对象锁，
        // 恢复后往下执行时，条件可能会被其他线程修改
        while (size == queue.length) {
            this.wait();//或者wait();
        }
        //存放到数组中方元素的位置
        queue[putIndex] = e;
        //存放位置超过数组的最大索引，需要取模放在0位置
        putIndex = (putIndex+1) % queue.length;
        size ++;
        notifyAll();//或者this.notifyAll;
    }

    /**
     * 取元素
     */
    public synchronized T take() throws InterruptedException {

        while (size == 0) {
            wait();
        }
        T t = (T) queue[takeIndex];//把元素取出来
        queue[takeIndex] = null;//取完元素之后置空
        takeIndex = (takeIndex) % queue.length;//之后更新取元素的索引
        size --;
        notifyAll();
        return t;
    }

    public synchronized int size() {
        return size;
    }


    public static void main(String[] args) {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(10);

        //多线程调试方式：1.写打印语句 2.jconsole

        for(int i = 0;i < 3;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for(int j = 0;j< 1000;j++) {
                         queue.put(j);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for(int i = 0;i < 3;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for(;;) {
                            int i = queue.take();
                            System.out.println(Thread.currentThread().getName()+": "+i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
//
//    public static void main(String[] args) {
//        MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
//        queue.put("A");
//        queue.put("B");
//        queue.put("C");
//        System.out.println(queue.size());
//
//        queue.take();
//
//        System.out.println(queue.size);
//
//        for(Object s : queue.queue) {
//            System.out.println(s);
//        }
//    }
}

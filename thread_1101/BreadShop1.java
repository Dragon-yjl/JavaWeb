package thread_1101;


import java.util.GregorianCalendar;

/**
 * 面包店
 * 10个生产者，每人每次生产3个
 * 20个消费者，每人每次消费1个
 *
 * 升级版需求：面包师傅灭个最多生产30次：面包店每天生产10*30*3=900个面包
 *              消费者不再一直消费，吧900个面包消费完结束
 */

public class BreadShop1 {

    private static int CONSUMER_NUM = 10;//消费者数量
    private static int CONSUME_COUNT = 5;//消费者每次消费5个面包
    private static int PRODUCER_NUM = 5;//生产者数量
    private static int PRODUCE_TIMES = 10;//生产者生产10次
    private static int PRODUCE_COUNT = 3;//生产者一次生产3个
    private static int MAX_COUNT = 100;//最大库存，

    private static int COUNT;
    //面包店库存

    private static int PRODUCE_NUMBER;
    //面包店生产面包的总数，不会消费

    //消费者
    public static class Consumer implements Runnable{

        private String name;

        public Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            //把生产者生产的面包消费完就结束
            try {
                while (true) {
                    synchronized (BreadShop1.class) {
                        if(PRODUCE_NUMBER == PRODUCER_NUM * PRODUCE_TIMES * PRODUCE_COUNT) {
                            break;
                        }

                        //库存到达下限，不能继续消费，需要阻塞等待
                        if(COUNT - CONSUME_COUNT < 0) {
                            BreadShop1.class.wait();//释放对象锁

                        }else {
                            //库存 满足消费条件
                            // 允许消费

                            COUNT -= CONSUME_COUNT;
                            //通知由于BreadShop.class.wait();代码进入阻塞的线程

                            System.out.printf("消费者 %s 消费了%s个面包,库存%s\n",name,CONSUME_COUNT,COUNT);

                            BreadShop1.class.notifyAll();
                            //模拟消费的耗时
                            Thread.sleep(200);
                        }
                    }
                    //优化
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //生产者
    public static class Producer implements Runnable{


        private String name;

        public Producer(String name) {
            this.name = name;
        }


        @Override
        public void run() {
            //规定达到生产次数后不能生产
            try {
                for(int i = 0;i < PRODUCE_TIMES;i++) {
                    synchronized (BreadShop1.class) {
                        //库存到达上限，不能继续生产，需要阻塞等待
                        while (COUNT + PRODUCE_COUNT > MAX_COUNT) {
                            BreadShop1.class.wait();//释放对象锁

                        }
                            //库存满足生产条件

                            COUNT += PRODUCE_COUNT;
                            PRODUCE_NUMBER += PRODUCE_COUNT;
                            //通知由于BreadShop.class.wait();代码进入阻塞的线程

                            System.out.printf("生产者 %s 生产了%s个面包,库存%s,生产的数量%s\n",name,PRODUCE_COUNT,COUNT,PRODUCE_NUMBER);

                            BreadShop1.class.notifyAll();
                            //模拟消费的耗时
                            Thread.sleep(200);

                    }
                    //优化
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        //同时启动消费者线程
        Thread[] consumers = new Thread[CONSUMER_NUM];
        for(int i = 0;i < CONSUMER_NUM;i++) {
            consumers[i] = new Thread(new Consumer(String.valueOf(i)));
        }
        //同时启动生产者线程
        Thread[] producers = new Thread[PRODUCE_TIMES ];
        for(int i = 0;i < PRODUCE_TIMES ;i++) {
            producers[i] = new Thread(new Producer(String.valueOf(i)));
        }

        for(Thread t : consumers) {
            t.start();
        }

        for(Thread t : producers) {
            t.start();
        }

    }

}

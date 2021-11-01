package thread_1101;

public class BreadShop {

    private static int COUNT;//面包店库存

    //消费者
    public static class Consumer implements Runnable{

        private String name;

        public Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            //一直消费
            try {
                while (true) {
                    synchronized (BreadShop.class) {
                        //库存到达下限，不能继续消费，需要阻塞等待
                        if(COUNT == 0) {
                            BreadShop.class.wait();//释放对象锁

                        }else {
                            //库存 > 0 允许消费
                            System.out.printf("消费者 %s 消费了1个面包\n",name);
                            COUNT --;
                            //通知由于BreadShop.class.wait();代码进入阻塞的线程
                            BreadShop.class.notifyAll();
                            //模拟消费的耗时
                            Thread.sleep(1000);
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
            //一直生产
            try {
                while (true) {
                    synchronized (BreadShop.class) {
                        //库存到达上限，不能继续生产，需要阻塞等待
                        if(COUNT + 3 > 100) {
                            BreadShop.class.wait();//释放对象锁

                        }else {
                            //库存满足生产条件
                            System.out.printf("生产者 %s 生产了3个面包\n",name);
                            COUNT += 3;
                            //通知由于BreadShop.class.wait();代码进入阻塞的线程
                            BreadShop.class.notifyAll();
                            //模拟消费的耗时
                            Thread.sleep(500);
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

    public static void main(String[] args) {

        //同时启动20个消费者线程
        Thread[] consumers = new Thread[20];
        for(int i = 0;i < 20;i++) {
            consumers[i] = new Thread(new Consumer(String.valueOf(i)));
        }
        //同时启动10个生产者线程
        Thread[] producers = new Thread[10];
        for(int i = 0;i < 10;i++) {
            producers[i] = new Thread(new Producer(String.valueOf(i)));
        }

        for(int i = 0;i < 20;i++) {
            consumers[i].start();
        }

        for(int i = 0;i < 10;i++) {
            producers[i].start();
        }

    }

}

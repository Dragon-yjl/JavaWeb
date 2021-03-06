package thread_1029;

public class Singleton {

    private static volatile Singleton INSTANCE;
    private Singleton() {}
    public static Singleton getInstance() {
        if(INSTANCE == null) {
            synchronized (Singleton.class) {
                INSTANCE = new Singleton();//非原子性的new操作
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {

        for(int i=0; i<20;i++) {
            Singleton s = Singleton.getInstance();
        }
    }
}

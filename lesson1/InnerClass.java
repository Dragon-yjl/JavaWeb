package lesson1;

/**
 * 匿名内部类
 */
public class InnerClass {
    public static void main(String[] args) {
        /**
         * 匿名内部类:重新定义了一个A的子类，重写的方法,运行的时候并不打印
         */
        A a = new A(){
            @Override
            public void x() {
                System.out.println("Y");
            }
        };
        //需要明确的调用，才会执行方法
    }

    /**
     * 静态内部类与普通类没啥区别，只需要明确是哪个类的内部类
     * 其他包使用的时候：InnerClass.A();
     */
    public static class A{
        public void x() {
            System.out.println("A");
        }
    }
}

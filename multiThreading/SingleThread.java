package multiThreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 多线程的使用场景，作用，特性
 * （1）  并发并行的执行，来提高性能，效率
 * （2） 阻塞代码，让后续代码能够执行，不受阻塞代码影响
 */

public class SingleThread {

    public static void main(String[] args) {

        //第一种情况，场景:耗时多的任务
        calculator(new ArrayList<>());
        calculator(new ArrayList<>());

        //第二种场景，情况:阻塞任务
        Scanner sc = new Scanner(System.in);
        print(sc);
        print(sc);//这一行代码没有机会执行，就要考虑施行多线程

    }

    public static int calculator(List<Integer> list) {
        //计算量很大，，假如要执行一次，耗时5s

        return 0;
    }

    public static void print(Scanner sc) {
        while (sc.hasNext()) {
            System.out.println(sc.nextLine());
        }
    }
}

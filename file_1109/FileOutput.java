package file_1109;

import java.io.*;

public class FileOutput {

    public static void main(String[] args) throws IOException {

        //路径上没有该文件，new File 不会报错，但是操作输入输出流会抛FileNotFoundException
        File file = new File("D:\\bit_java\\idea_test\\1.txt");
        //把 a-z 换行输出到某个文件，需要考虑文件是否存在这个问题
        if(!file.exists()) {
            file.createNewFile();
        }


        //类似输入的几种方法都是ok
//        new FileWriter()/new FileOutputStream()/new BufferedWriter()/new PrintWriter()

        //缓冲字符输出流
//        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
//        bw.write("\n");

        //打印输出流
//        PrintWriter pw = new PrintWriter(new FileWriter(file));
        PrintWriter pw = new PrintWriter(new FileOutputStream(file));
        //快速打印a-z
        for(int i = 'a';i <= 'z';i++) {
            pw.println((char) i);
        }

        pw.flush();

    }
}

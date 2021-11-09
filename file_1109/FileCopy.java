package file_1109;

import java.io.*;

public class FileCopy {

    public static void main(String[] args) throws IOException {

        //文件复制
        File input = new File("E:\\ChromeDownload\\wemeetapp.exe");
        File output = new File("E:\\Users\\a\\wemeet.exe");
        if(!output.exists()) {
            output.createNewFile();
        }

        //定义输入输出流
        FileInputStream fis = new FileInputStream(input);
        FileOutputStream fos = new FileOutputStream(output);

        long start = System.currentTimeMillis();

        byte[] bytes =new byte[1024*8];
        int len;
        //每次从输入流读取到byte[]的内容，直接输出到某个文件，就是复制
        while ((len=fis.read(bytes)) != -1) {
           fos.write(bytes,0,len);
        }

        long end =System.currentTimeMillis();

        System.out.println(end - start);

        fis.close();
        fos.close();

    }
}

package file_1108;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListFile {

    public static void main(String[] args) {

        File dir = new File("F:\\QQdownload");
        List<File> files = listDir2(dir);

        //jdk1.8集合框架使用stream操作可以使用lambda表达式
        files.stream()//集合的stream操作，提供更多的功能，效率也很高
//                .filter()//过滤
                .map(f-> {
                    return f.getName();
                })//把集合中的元素映射为另外一种类型
//                .collect()//进行流操作重新返回
                .forEach(System.out::println);

    }

    public static List<File> listDir(File dir) {
        List<File> list = new ArrayList<>();
        if(dir.isFile()) {//如果是文件
            list.add(dir);//直接添加
        }else if(dir.isDirectory()) {//如果是文件夹
            File[] children = dir.listFiles();//就把文件文件加入数组
            if(children != null) {//说明dir就是目录
                for(File child : children) {//递归调用
                    List<File> files = listDir(child);
                    list.addAll(files);
                }
            }
        }

        return list;
    }

    public static List<File> listDir2(File dir) {
        List<File> list = new ArrayList<>();
            //获取目录下一级的子文件，子文件夹
        File[] children = dir.listFiles();
        if(children != null) {//如果下一层还是目录
            for(File child : children) {//循环遍历children数组
//                //返回的文件列表不包含文件夹
//                if(child.isDirectory()) {
//                        list.addAll(listDir2(child));//如果是子文件夹，递归调用获取
//                }else {
//                        list.add(child);
//                }
            //如果要包含文件夹
            list.add(child);
            if(child.isDirectory()) {//如果是子文件夹，递归调用获取
                list.addAll(listDir2(child));
            }
            }
        }

        return list;
    }
}

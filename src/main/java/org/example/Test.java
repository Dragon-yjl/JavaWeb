package org.example;

/**
 * JDBC操作步骤
 * 1.创建数据库连接Connection
 * 2.创建操作命令对象Statement
 * 3.执行sql
 * 4.处理结果集Result（查询操作） 类似 list< map <String字,段类型> >结构 键值对
 *                               一般面向对象编程会将结果集处理为一个对象或是
 *                               多个对象（list<对象类型>）
 * 5.释放资源
 *
 * 结合第一节课数据库客户端和数据库服务端数据传输的步骤来理解
 * 客户端先建立数据库
 * 输入sql语句
 * 发送sql到数据库服务器
 * 数据库服务器执行sql
 * 返回结果（插入/修改/删除返回处理成功后的行数，查询操作返回结果集）
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //JDBC操作的第一步，创建连接
            // 加载JDBC驱动程序：反射，这样调用初始化com.mysql.jdbc.Driver类，即将该类加载到JVM方法
            //区，并执行该类的静态方法块、静态属性。
            Class.forName("com.mysql.jdbc.Driver");
            // 第一步 创建数据库连接
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=123456&useUnicode=true&characterEncoding=UTF-8");
            System.out.println(connection);

            //第二步 创建操作命令对象Statement
            statement = connection.createStatement();

            //第三步 执行sql
            String sql = "select id,name,chinese,math,english from exam;";
            resultSet =  statement.executeQuery(sql);

            List<Exam> examList = new ArrayList<>();
            //第四步 处理结果集(查询操作)
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double chinese = resultSet.getDouble("chinese");
                double math = resultSet.getDouble("math");
                double english = resultSet.getDouble("english");
                System.out.printf("id=%s,name=%s,chinese=%s,math=%s,english=%s%n",id,name,chinese,math,english);

                Exam exam = new Exam();
                exam.setId(id);
                exam.setName(name);
                exam.setChinese(chinese);
                exam.setMath(math);
                exam.setEnglish(english);

                examList.add(exam);

            }

            System.out.println(examList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //第五步 无论如何都需要释放资源


            try {
                if(resultSet != null)
                    resultSet.close();

                if(statement != null)
                    statement.close();

                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    //一般来说 对象中属性不提供给对外直接修改

    /**
     * 1.Getter/Setter方法
     * 2.构造方法来设置
     */
    private static class Exam{
        private Integer id;
        private String name;
        private Double chinese;
        private Double math;
        private Double english;

//        public Exam(Integer id, String name, Double chinese, Double math, Double english) {
//            this.id = id;
//            this.name = name;
//            this.chinese = chinese;
//            this.math = math;
//            this.english = english;
//        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getChinese() {
            return chinese;
        }

        public void setChinese(Double chinese) {
            this.chinese = chinese;
        }

        public Double getMath() {
            return math;
        }

        public void setMath(Double math) {
            this.math = math;
        }

        public Double getEnglish() {
            return english;
        }

        public void setEnglish(Double english) {
            this.english = english;
        }

        @Override
        public String toString() {
            return "Exam{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", chinese=" + chinese +
                    ", math=" + math +
                    ", english=" + english +
                    '}';
        }
    }
}

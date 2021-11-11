package org.example;

import org.example.config.AppConfig;
import org.example.controller.LoginController;
import org.example.model.User;
import org.example.service.LoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        //根据Spring配置文件路径创建容器：应用上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //注册Bean的方式一：类注解
        LoginController loginController = (LoginController) context.getBean("loginController");
        System.out.println(loginController);

        //验证loginController依赖注入的loginService是否为容器中的Bean对象
        LoginService loginService = context.getBean(LoginService.class);
        System.out.println(loginController.getLoginService() == loginService);//true说明是

        ///注册Bean的方式二：@Bean
        User u1 = (User) context.getBean("u1");
        System.out.println(u1);

        //同一个类型，注册了多个Bean对象到容器中，不能通过类型获取,会抛异常
//        User u2 = context.getBean(User.class);//NoUniqueBeanDefinitionException:不唯一的Bean类型异常
//        System.out.println(u2);


        //注册Bean的方式三：@Configuration
        AppConfig appConfig = context.getBean(AppConfig.class);
        System.out.println(appConfig);

        //关闭容器
        ((ClassPathXmlApplicationContext) context).close();
    }
}
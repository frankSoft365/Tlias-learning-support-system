package com.microsoft;


import com.microsoft.controller.EmpController;
import com.microsoft.pojo.Emp;
import com.microsoft.pojo.LoginInfo;
import com.microsoft.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class TestReflect {
    @Test
    public void testClass() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Emp> empClass = Emp.class;
        log.info("类名+.class是一个Class类型的对象 : {}", empClass);
        Class<?> empClass2 = Class.forName("com.microsoft.pojo.Emp");
        log.info("Class的静态方法，需要传一个类的reference的字符串，返回一个Class类型的对象 : {}", empClass2);
        Emp emp = new Emp();
        Class<? extends Emp> empClass3 = emp.getClass();
        log.info("类对象+.getClass方法，返回一个Class类型对象 : {}", empClass3);
        // 获取reference字符串
        String name = empClass.getName();
        log.info("获取类的reference : {}", name);

        // 获取构造器
        Constructor<?>[] constructors = empClass.getConstructors();
        // 一个无参构造器和有参构造器
        for (Constructor<?> constructor : constructors) {
            log.info("构造器是 : {}", constructor);
            log.info("类型是 : {}", constructor.getClass());
        }
        // 获取特定构造器 参数顺序要一一对应
        Constructor<Result> constructor = Result.class.getConstructor(Integer.class, String.class, Object.class);
        log.info("Result的特定构造器 : {}", constructor);
        // 用构造器新建对象
        Result result = constructor.newInstance(1, "success", null);
        log.info("新建Result对象 : {}", result);

        // 只有对象才有get开头的方法，getClass返回Class类型的对象，getName返回这个对象的类的reference（Class类对象）
        log.info("emp.getClass() : {}", emp.getClass());
        log.info("emp.getClass().getName() : {}", emp.getClass().getName());
        // 一个空的emp对象，其name值没有赋值
        log.info("emp.getName() : {}", emp.getName());

    }

    /**
     * 获取字段
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void testField() throws NoSuchFieldException, IllegalAccessException {
        // 获取字段
        Class<LoginInfo> loginInfoClass = LoginInfo.class;
        Field[] declaredFields = loginInfoClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName() + " " + declaredField.getType().getName());
        }
        // 获取字段后，set与get都不能脱离对象
        Field token = loginInfoClass.getDeclaredField("token");
        LoginInfo loginInfo = new LoginInfo();
        // private设置访问可行性
        token.setAccessible(true);
        token.set(loginInfo, "一个token");
        System.out.println("获取值：" + token.get(loginInfo));
    }

    @Test
    public void testMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 测试EmpController
        Class<EmpController> empControllerClass = EmpController.class;
        // 获取他的方法
        Method[] methods = empControllerClass.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }
}

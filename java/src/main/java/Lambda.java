import java.util.Arrays;
import java.util.stream.Stream;

/**
 *  使用lombda语法，运行效率大于等于java语法
 *  使用范围：函数式接口（只有一个方法的接口）实现
 *  基本语法: (parameters) -> expression  或  (parameters) ->{ statements; }
 *  Stream是lombda编程的核心接口,一个对象仅支持一次完成的遍历吗，因此最好每次都创建一个新的stream对象
 */
public class Lambda {
    public static void main(String[] args) {
        //1. 使用lombok语法构造匿名内部类
        B b2 = new B(
                () -> System.out.println("lambok a1!"),
                () -> System.out.println("lambok c1!"));
        B b3 = new B(() -> {
            System.out.println("lambok a2!");
        }, () -> {
            System.out.println("lambok c2!");
        });

        //2.使用lombok语法遍历（实现Consumer）
        String[] strings = {"1", "2", "3", "4"};
        Arrays.asList(strings).forEach(s -> System.out.println(s));
        Arrays.asList(strings).forEach(System.out::println);//输出当前对象
        //Stream为jdk8新增的,所有操作都是并行安全的，借助lombda表达式可使代码更加清晰
        Stream.of(strings).map(String::length).forEach(System.out::println);   //遍历获取所有字符串长度
    }
}

@FunctionalInterface
interface A {
    void a();
}

@FunctionalInterface
interface C {
    void c();
}

class B {
    B(A a, C c){
        a.a();
        c.c();
        System.out.println("b!");
    }
}


package anontation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;


@Documented								//将注解中的元素包含到 Javadoc 中去
@Retention(RetentionPolicy.RUNTIME)		//注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们
@Target({ElementType.TYPE})				//注解应用于类属性
@Inherited								//父类注解应用于子类
public @interface Service {
	String value();
}

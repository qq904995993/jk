package instance.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloInvocationHandler implements InvocationHandler {

    private Hello hello;

    public HelloInvocationHandler(Hello hello) {
        this.hello = hello;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy!!!");
        method.invoke(hello, args);
        return null;
    }

    public static void main(String[] args) {
        InvocationHandler handler = new HelloInvocationHandler(new HelloImpl());
        Hello helloProxy = (Hello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(),
                HelloImpl.class.getInterfaces(), handler);
        helloProxy.sayHello();
    }

}


package instance.hello;

public class HelloProxy implements Hello {

    private Hello hello = new HelloImpl();

    public void sayHello() {
        hello.sayHello();
    }

    public static void main(String[] args) {
        HelloProxy helloProxy = new HelloProxy();
        helloProxy.sayHello();
    }

}

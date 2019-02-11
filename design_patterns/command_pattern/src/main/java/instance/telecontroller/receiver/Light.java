package instance.telecontroller.receiver;

public class Light {

    private String name;

    public Light(String name) {
        this.name = name;
    }

    public void on() {
        System.out.println(name + ",light on!");
    }

}

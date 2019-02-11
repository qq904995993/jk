package instance.sort;

import java.util.Arrays;

public class Duck implements Comparable {

    private int id;
    private String name;

    public Duck(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id:" + id + ", name:" + name;
    }

    public int compareTo(Object o) {
        Duck duck = (Duck) o;
        return (this.id > duck.getId() ? 1 : this.id == duck.getId() ? 0 : -1);
    }

    public static void main(String[] args) {
        Duck[] ducks = {
                new Duck(2, "a"),
                new Duck(4, "a"),
                new Duck(3, "a"),
                new Duck(1, "a"),
                new Duck(1, "a"),
                new Duck(4, "a")

        };
        Arrays.sort(ducks);
        for (int i = 0; i < ducks.length; i ++) {
            System.out.println(ducks[i]);
        }
    }

}

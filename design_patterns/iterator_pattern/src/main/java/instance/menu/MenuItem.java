package instance.menu;

import java.util.Iterator;

public class MenuItem {

    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "name:" + name + ", price:" + price;
    }
}

class Waitress {
    public static void main(String[] a) {
        Waitress waitress = new Waitress();
        waitress.printMenu(new DinerMenu());
        System.out.println();
        waitress.printMenu(new PancakeHouseMenu());
    }

    private static void printMenu(Iterable iterable) {
        Iterator iterator = iterable.iterator();
        while (iterator.hasNext()) {
            MenuItem menuItem = (MenuItem) iterator.next();
            System.out.println(menuItem);
        }
    }

}

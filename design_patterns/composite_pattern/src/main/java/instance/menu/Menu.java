package instance.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu extends MenuComponent {

    private String name;
    private List<MenuComponent> menuComponents = new ArrayList<MenuComponent>();

    public Menu(String name) {
        this.name = name;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return menuComponents.get(i);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void print() {
        System.out.println("name: " + name);
        for(MenuComponent menuComponent : menuComponents) {
           menuComponent.print();
        }
    }
}

class Waitress {
    private MenuComponent menus;

    public Waitress(MenuComponent menus) {
        this.menus = menus;
    }

    public void printMenu() {
        menus.print();
    }

    public static void main(String[] args) {
        MenuComponent menus = new Menu("all menu");
        MenuComponent pancakeHouseMenu = new Menu("pancake house menu");
        MenuComponent dinerMenu = new Menu("diner menu");
        MenuComponent cafeMenu = new Menu("cafeMenu menu");
        MenuComponent dessertMenu = new Menu("dessert menu");
        menus.add(pancakeHouseMenu);
        menus.add(dinerMenu);
        menus.add(cafeMenu);
        dinerMenu.add(new MenuItem("Pasta", 3.89));
        dinerMenu.add(dessertMenu);
        dessertMenu.add(new MenuItem("Apple Pie", 1.59));
        pancakeHouseMenu.add(new MenuItem("Pancake", 2.29));
        cafeMenu.add(new MenuItem("American Cafe", 1.99));

        Waitress waitress = new Waitress(menus);
        waitress.printMenu();

    }

}

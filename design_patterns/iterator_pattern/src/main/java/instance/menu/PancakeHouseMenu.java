package instance.menu;

import instance.menu.iterator.PancakeHouseMenuIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PancakeHouseMenu implements Iterable {

    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public PancakeHouseMenu() {
        addItem(new MenuItem("K&B pancake Breakfast", 2.99));
        addItem(new MenuItem("Regular Pancake Breakfast", 2.99));
        addItem(new MenuItem("Blueberry Pancakes", 3.45));
        addItem(new MenuItem("Walffles", 3.59));
    }

    public boolean addItem(MenuItem menuItem) {
        return menuItems.add(menuItem);
    }

    public Iterator iterator() {
        return new PancakeHouseMenuIterator(menuItems);
    }
}

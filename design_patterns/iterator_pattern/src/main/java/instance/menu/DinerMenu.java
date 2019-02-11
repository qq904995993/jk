package instance.menu;

import instance.menu.iterator.DinerMenuIterator;

import java.util.Iterator;

public class DinerMenu implements Iterable{

    final int MAX_ITEMS = 6;
    MenuItem[] menuItems = new MenuItem[6];
    int numberOfIteam = 0;

    public DinerMenu() {
        addItem(new MenuItem("Vegetarian  BLT", 2.99));
        addItem(new MenuItem("BLT", 2.99));
        addItem(new MenuItem("Soup of the day", 3.29));
        addItem(new MenuItem("Hotdog", 3.09));
    }

    public boolean addItem(MenuItem menuItem) {
        if(numberOfIteam >= MAX_ITEMS) {
            return false;
        }
        menuItems[numberOfIteam ++] = menuItem;
        return true;
    }

    public Iterator iterator() {
        return new DinerMenuIterator(menuItems);
    }
}

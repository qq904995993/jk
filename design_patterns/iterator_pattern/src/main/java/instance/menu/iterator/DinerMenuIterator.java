package instance.menu.iterator;

import instance.menu.MenuItem;

import java.util.Iterator;

public class DinerMenuIterator implements Iterator {

    private MenuItem[] menuItems;
    int position = 0;

    public DinerMenuIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    public boolean hasNext() {
        if(position >= menuItems.length || menuItems[position] == null) {
            return false;
        }
        return true;
    }

    public Object next() {
        return menuItems[position ++];
    }

    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}

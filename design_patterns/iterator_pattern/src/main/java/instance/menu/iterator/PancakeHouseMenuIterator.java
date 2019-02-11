package instance.menu.iterator;

import java.util.Iterator;
import java.util.List;

public class PancakeHouseMenuIterator implements java.util.Iterator {

    private Iterator iterator;

    public PancakeHouseMenuIterator(List iteams) {
        this.iterator = iteams.iterator();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public Object next() {
        return iterator.next();
    }

    public void remove() {
        iterator.remove();
    }
}

package instance.iterator;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class IteratorEnumeration implements Enumeration {

    private Iterator iterator;

    public IteratorEnumeration(Iterator iterator) {
        this.iterator = iterator;
    }

    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    public Object nextElement() {
        return iterator.next();
    }

    public static void main(String[] args) {
        List ls = Arrays.asList(new String[]{"1", "2", "3"});
        IteratorEnumeration enumeration = new IteratorEnumeration(ls.iterator());
        while(enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
    }

}

package io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public abstract class AbstractNio {

    protected ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    protected Selector selector;

    public void reactor() throws IOException {
        selector.select(1000);
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();
            if (key.isValid()) {
                if (key.isAcceptable()) {
                    accept(key);
                }
                if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    abstract void accept(SelectionKey key) throws IOException;

    abstract void read(SelectionKey key) throws IOException;

}

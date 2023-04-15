package assignment2;

//import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.NoSuchElementException;

public class MyStack<E> {
    private MyDoublyLinkedList<E> storage;

    public MyStack() {
        this.storage = new MyDoublyLinkedList<E>();
    }

    public void push(E element) {
        this.storage.addFirst(element);
    }

    public E pop() {
        this.storage.checkEmpty();

        return this.storage.removeFirst();
    }

    public E peek() {
        this.storage.checkEmpty();

        return this.storage.peekFirst();
    }

    public boolean isEmpty() {
        return this.storage.getSize() == 0;
    }

    public void clear() {
        this.storage.clear();
    }

    public int getSize() {
        return this.storage.getSize();
    }


}

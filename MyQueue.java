package assignment2;

public class MyQueue<E>{

    private MyDoublyLinkedList<E> storage;

    public MyQueue() {
        this.storage = new MyDoublyLinkedList<E>();
    }

    public void enqueue(E element) {
        this.storage.addLast(element);
    }

    //public E peekFirst() {
        //return this.storage.peekFirst();
    //}

    public E dequeue() {
        this.storage.checkEmpty();

        return this.storage.removeFirst();
    }

    public boolean isEmpty() {
        return this.storage.isEmpty();
    }

    public void clear() {
        this.storage.clear();
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println(obj instanceof MyQueue);

        return (obj instanceof MyQueue) && this.storage.equals(((MyQueue<?>) obj).storage);
    }

    public boolean isDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}

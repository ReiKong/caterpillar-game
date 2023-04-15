package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyLinkedList<E> {
	private DNode head;
	private DNode tail;

	/*public void getHead() {
		System.out.println("Printing head:" + this.head.element);
		System.out.println("Printing after head" + this.head.next.element);
	}

	public void getTail() {
		System.out.println("Printing tail:" + this.tail.element);
		System.out.println("Printing before tail" + this.tail.prev.element);
	}

	public void getAllHead() {
		System.out.println("Printing list from head --------------------------");
		DNode node = this.head;
		//System.out.println(node);
		while (node != null) {
			System.out.println(node.element);
			node = node.next;
		}
	}

	public void getAllTail() {
		System.out.println("Printing list from tail --------------------------");
		DNode node = this.tail;
		//System.out.println(node);
		while (node != null) {
			System.out.println(node.element);
			node = node.prev;
		}
	}*/

	
	public void add(E element) {
		if (this.isEmpty()) {
			addFirst(element);
			return;
		}

		// if not empty
		// creates a new node
		DNode newNode = new DNode();
		newNode.element = element;

		// adds element to the end
		this.tail.next = newNode;
		DNode oldTail = this.tail;
		this.tail = this.tail.next;
		this.tail.prev = oldTail;
		this.size++;
	}

	public void addLast(E element) {
		add(element);
	}

	public void addFirst(E element) {
		// creates a new node
		DNode newNode = new DNode();
		newNode.element = element;

		if (this.isEmpty()) {
			this.head = newNode;
			this.tail = this.head;
			this.size++;
			return;
		}

		// adds element to the beginning
		DNode oldHead = this.head;
		this.head = newNode;
		this.head.next = oldHead;
		this.head.next.prev = this.head;
		this.size++;
	}

	public void checkEmpty() {
		if (this.isEmpty()) {
			throw new NoSuchElementException("The list is empty.");
		}
	}

	public E remove() {
		checkEmpty();

		DNode removedNode = this.tail;

		if (this.getSize() == 0 || this.getSize() == 1) {
			this.head = null;
			this.tail = null;
		} else {
			this.tail = this.tail.prev;
			this.tail.next = null;
		}

		this.size--;

		return removedNode.element;
	}

	public E removeFirst() {
		checkEmpty();

		DNode removedNode = this.head;

		if (this.size == 1) {
			this.head = null;
		} else {
			this.head = this.head.next;
			this.head.prev = null;
		}

		this.size--;

		return removedNode.element;
	}

	public E removeLast() {
		return remove();
	}

	public void clear() {
		//while (this.size > 0) {
			//this.remove();
		//}

		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public E peekFirst() {
		checkEmpty();

		return this.head.element;
	}

	public E peekLast() {
		checkEmpty();

		return this.tail.element;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MyDoublyLinkedList)) {
			return false;
		}

		MyDoublyLinkedList<E> other = (MyDoublyLinkedList<E>) obj;

		if (this.size != other.size) {
			return false;
		}

		DNode thisNode = this.head;
		DNode otherNode = other.head;

		while (thisNode != null && otherNode != null) {
			System.out.println(thisNode.element.equals(otherNode.element));
			if (!(thisNode.element.equals(otherNode.element))) {
				return false;
			}

			thisNode = thisNode.next;
			otherNode = otherNode.next;
		}

		return true;
	}

	public boolean isElementOf(E element) {
		DNode thisNode = this.head;

		while (thisNode != null) {
			if (!thisNode.element.equals(element)) {
				return false;
			}
			thisNode = thisNode.next;
		}

		return true;
	}

	public Iterator<E> iterator() {
		return new DLLIterator();
	}

	private class DNode {
		private E element;
		private DNode next;
		private DNode prev;

		//public E getElement() {
			//return this.element;
		//}
	}

	private class DLLIterator implements Iterator<E> {
		DNode curr;

		public DLLIterator() {
			this.curr = head;
		}

		public boolean hasNext() {
			return this.curr != null;
		}

		public E next() {
			if (!this.hasNext())
				throw new NoSuchElementException();

			E element = this.curr.element;
			this.curr = this.curr.next;
			return element;
		}
	}
}

package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode(null);
		tail = new LLNode(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element == null)
			return false;
		int n = size;
		this.add(n,element);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		LLNode<E> current = new LLNode<E>();
		current = head;
		if(index < 0 || index >= size || size == 0)
				throw new IndexOutOfBoundsException();
		for(int i=0 ; i<=index;i++){
			current = current.next;
		}
			
		return current.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(index < 0 || index >this.size())
			throw new IndexOutOfBoundsException();
		
		if (element ==null)
			throw new NullPointerException();

		int i = 0;
		LLNode<E> prevNode = head;
		LLNode<E> tmp = new LLNode<E>(element);
		while(true){
			
			if(i == index)
				break;
			else{
				prevNode = prevNode.next;
				i++;}
		}
		tmp.next = prevNode.next;
		tmp.prev = prevNode;
		prevNode.next.prev = tmp;
		prevNode.next =tmp;
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index < 0 || index >size)
			throw new IndexOutOfBoundsException();
		
		LLNode<E> current = head;
		for(int i=0;i<index+1;i++)
			current = current.next;
		current.next.prev = current.prev;
		current.prev.next = current.next;
		current.prev =null;
		current.next =null;
		size--;
		return current.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		if (element ==null)
			throw new NullPointerException();

			LLNode<E> current = head;
			LLNode<E> to_set = new LLNode<E>(element);
			LLNode<E> tmp = new LLNode<E>(null);
			//System.out.println(to_set.data);
			for(int i=0;i<index+1;i++)
				current = current.next;
			tmp.data = current.data;
			current.data = to_set.data;
			return tmp.data;
			
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor
	public LLNode() 
	{
		this.data = null;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}

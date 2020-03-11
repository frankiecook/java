/** Linked List implementation of our List abstract data type */
public class List<T> {
  // put all fields from ListAsLinkedList class here
	/** pointer to first node in the list */
	private Node<T> head;
	
	/** pointer to last node in the list */
	private Node<T> tail;
	
	/** constructor that sets head and tail */
	public List() {
		head = tail = null;
	}
  
  // put all methods from ListAsLinkedList class here
	/** Adds a value to the end of the list */
	public void append(T value) {
		// wrap data in a node
		Node<T> temp = new Node<T>(value);
		
		// if no nodes currently in linked list
		// make this one the first node
		if (tail == null) {
			tail = temp;
			head = temp;
		} else {
			// take the current end of the list and set this new node to be after that
			tail.setNext(temp);
			
			// set the new node as our new tail
			tail = temp;
		}
	}
	
	/** Adds a value to the beginning of the list */
	public void prepend(T value) {
		// wrap data in a node
		Node<T> temp = new Node<T>(value);
		
		// if no nodes currently in linked list
		// make this one the first node
		if (head == null) {
			tail = temp;
			head = temp;
		} else {
			// take the current end of the list and set this new node to be after that
			temp.setNext(head);
			
			// set the new node as our new tail
			head = temp;
		}
	}
	
	/** Deletes the value at the position in the list */
	public void deleteAt(int position) {
		// temporary node starts at the head of the list
		Node<T> temp = head;
		
		// edge case for the first index
		if (position == 0) {			
			// move the head pointer to the second node in the list
			head = head.getNext();
		} else {
			
			// cycle through each index up to the index before position
			for (int i = 0; i < position - 1; i++) {
				// set the temporary node equal to the next node
				temp = temp.getNext();
			}
			
			// set the node before the positional node to link
			// to the node after the positional node
			temp.setNext(temp.getNext().getNext());
		}
	}
	
	/** Returns the size of the list */
	public int size() {
		// initalize and declare size counter and the temporary node
		int size = 0;
		Node<T> temp = head;
		
		// as long as the current node is not empty
		while (temp != null ) {
			
			// set the temporary node to the next node
			temp = temp.getNext();
			// increment the size counter
			size++;
		}
		
		// return the size of the list
		return size;
	}
	
	/** retrieve the value */
	public T getValueAt(int position) {
		// validation check
		if (head == null) {
			return null;
		}
		
		// set temporary node equal to the head node
		Node<T> temp = head;
		
		// cycle through each node until node at the position
		for (int i = 0; i < position; i++) {
			// set the temporary node equal to the next node
			temp = temp.getNext();
		}
		
		// return the value at the position
		return temp.getData();
	}
	
	/** find the position of a value */
	public int positionOf(T value) {
		// current node being checked
		Node<T> temp = head;
		// index counter
		int index = 0;
		
		// iterate through the data in the list
		// starting from head
		// until the data being searched matches
		while (temp.getData() != value) {
			temp = temp.getNext();
			index++;
		}
		
		// return the index of the data
		return index;
	}
  
}

/** A linked list node for our linked list */
class Node<T> {
  // put all fields from Node class here
	/** information stored in the node */
	private T data;
	
	/** link to the node */
	private Node<T> next;
  
  // put all methods from Node class here
	/** constructor taking data to be stored in the node */
	public Node(T data) {
		this.data = data;
		this.next = null;
	}
	
	/** accessor for data */
	public T getData() {
		return data;
	}
	
	/** accessor next node */
	public Node<T> getNext() {
		return next;
	}
	
	/** mutator for data*/
	public void setData(T data) {
		this.data = data;
	}
	
	/** mutator for next node */
	public void setNext(Node<T> next) {
		this.next = next;
	}
  
}

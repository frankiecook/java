/*
 * Name: Norman Cook
 * Date: 03-01-2020
 * Description:  Creates lists using an interface and each list
 * 		implements a linked list method and array method.
 */

/** The interface for our List (Abstract Data Type) */
interface IList {
  /** Adds the given value to the end of the list */
  void append(char value);
  
  /** Adds the given value to the beginning of the list */
  void prepend(char value);
  
  /** Deletes the container at the given position (a container holds a value) */
  void deleteAt(int position);
  
  /** Returns the number of values currently in our list */
  int size();

  /** Retrieves the value at the given position */
  char getValueAt(int position);

  /** Searches for the FIRST occurence of a given value in our list.
    * If found, it returns the position of that value.
    * If not found, it returns -1 */
  int positionOf(char value);
}

/** Array implementation of our List */
class ListAsArray implements IList {
	// initialize array to a size of 30 elements
	// this will prevent the need to resize our array
	private char array[] = new char[30];
	// variable that tracks the last position being used in the array
	private int endPosition = 0;
	
	/** Appends a value to the end of the list */
	public void append(char value) {
		// places the value at the last position in the list
		array[endPosition] = value;
		// increments the last position
		endPosition++;
	}
	
	/** Adds a value to the beginning of the list */
	public void prepend(char value) {
		// Cycle through each value in the list from the last position to the first position
		for (int i = endPosition; i > 0; i--) {
			// set the value at the current index equal to the previous value in the list
			array[i] = array[i - 1];
		}
		
		// place the given value to the first position in the list
		array[0] = value;
		endPosition++;
	}
	
	/** Deletes a value at the given index */
	public void deleteAt(int position) {
		// check if the position is within the list
		if (position >= 0 && position < endPosition) {
			
			// cycle through the positions in the list starting
			// at the position to the end
			for (int i = position; i < size() - 1; i++) {
				
				// shift the list values to the previous spot
				array[i] = array[i + 1];
			}
			// decrease the last position to match the updated list
			endPosition--;
		}
	}
	
	/** Returns the size of the list */
	public int size() {
		
		// the last position in the list is the size of the list
		return endPosition;
	}
	
	/** Retrieves the value at a given position in the list */
	public char getValueAt(int position) {
		// checks if the position is within the list
		if (position >= 0 && position < endPosition) {
			
			// returns the value at the position
			return array[position];
		} else {
			
			// position is not in the list
			return '?';
		}
	}
	
	/** Locates the position of a character */
	public int positionOf(char value) {
		// cycle through each index in the list until the end of the list
		for (int i = 0; i < endPosition; i++) {
			// when the value in the list equal the given value
			if (array[i] == value) {
				// return the index
				return i;
			}
		}
		// return -1 if not found
		return -1;
	}
}

/** Singly Linked List implementation of our List */
class ListAsLinkedList implements IList {
	/** pointer to first node in the list */
	private Node head;
	
	/** pointer to last node in the list */
	private Node tail;
	
	/** constructor that sets head and tail */
	public void LinkedList() {
		head = tail = null;
	}
	
	/** Adds a value to the end of the list */
	public void append(char value) {
		// wrap data in a node
		Node temp = new Node(value);
		
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
	public void prepend(char value) {
		// wrap data in a node
		Node temp = new Node(value);
		
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
		Node temp = head;
		
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
		Node temp = head;
		
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
	public char getValueAt(int position) {
		// set temporary node equal to the head node
		Node temp = head;
		
		// cycle through each node until node at the position
		for (int i = 0; i < position; i++) {
			// set the temporary node equal to the next node
			temp = temp.getNext();
		}
		
		// return the value at the position
		return temp.getData();
	}
	
	/** find the position of a value */
	public int positionOf(char value) {
		// current node being checked
		Node temp = head;
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

/** A singly linked list node for our singly linked list */
class Node {
	/** information stored in the node */
	private char data;
	
	/** link to the node */
	private Node next;
	
	/** constructor taking data to be stored in the node */
	public Node(char data) {
		this.data = data;
		this.next = null;
	}
	
	/** accessor for data */
	public char getData() {
		return data;
	}
	
	/** accessor next node */
	public Node getNext() {
		return next;
	}
	
	/** mutator for data*/
	public void setData(char data) {
		this.data = data;
	}
	
	/** mutator for next node */
	public void setNext(Node next) {
		this.next = next;
	}
} 

/** contains our entry point */
public class Main {
  /** entry point - DO NOT CHANGE the pre-existing code below */
  public static void main(String[] args) {
    int[] numbers = {105,116,112,115,65,58,47,47,116,105,110,121,88,117,114,108,46,99,111,109,47};
    int[] numbers2 = {97,59,111,53,33,111,106,42,50};
    int[] numbers3 = {116,104,32,111,116,32,111,71};
    
   
    /// List as an Array
    IList array = new ListAsArray();
    
    // add values
    for(int num : numbers) {
      array.append((char)num);
    }
    for(int num : numbers3) {
      array.prepend((char)num);
    }
    
    // delete some values
    int position;
    
    position = array.positionOf((char)105);
    array.deleteAt(position);
    
    position = array.positionOf((char)65);
    array.deleteAt(position);
    
    position = array.positionOf((char)88);
    array.deleteAt(position);
   
    // print em
    position = 0;
    while (position < array.size()) {
      System.out.print(array.getValueAt(position));
      position++;
    }
 
    
    /// List as a Linked List
    IList linkedList = new ListAsLinkedList();
    
    // add values
    for(int num : numbers2) {
      linkedList.append((char)num);
    }
    linkedList.prepend((char)55);
    linkedList.prepend((char)121);

    // delete some values
    position = linkedList.positionOf((char)59);
    linkedList.deleteAt(position);
    
    position = linkedList.positionOf((char)33);
    linkedList.deleteAt(position);
    
    position = linkedList.positionOf((char)42);
    linkedList.deleteAt(position);
    
    // print em
    position = 0;
    while (position < linkedList.size()) {
      System.out.print(linkedList.getValueAt(position));
      position++;
    }
    
    System.out.println();
    
    // ???
    // initial grade for assignment one
    double grade = 17.5;
    // secret number to be calculated
    double secretNum;
    // multiply grade by 2
    secretNum = grade * 2;
    // round secret number down to nearest whole integer
    secretNum = Math.floor(secretNum);
    // mod the secret number by 10
    secretNum = secretNum % 10;
    // print the secret number
    System.out.println(secretNum);
  }
}

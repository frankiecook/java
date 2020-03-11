/** Stack abstract data type */
public class Stack<T> {
  /** List objects to hold our stack items.
      Use List operations to implement the methods below */
  private List<T> list;

  /** Constructor for the Stack*/
  public Stack() {
    // instantiate list here
	  list = new List<T>();
  }

  /** Add a value to the top of the stack*/
  public void push(T value) {
	  // adds value to the beginning of the list
	  list.prepend(value);
  }

  /** Delete and return the value at the top of the stack*/
  public T pop() {	  
	  // stores the value at the beginning of the list
	  T value = list.getValueAt(0);
	  // delete the value at the beginning of the list
	  list.deleteAt(0);
	  return value;
  }

  /** Return the value at the top of the stack*/
  public T peek() {
	  // returns the value at the beginning of the list
	  return list.getValueAt(0);
  }

  /** Check if the stack is empty*/
  public boolean isEmpty() {
	  // checks of the list size is zero
	  if (list.size() == 0) {
		  return true;
	  }
	  // returns false otherwise
	  return false;
  }
}

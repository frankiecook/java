/** Queue abstract data type */
public class Queue<T> {
  /** List objects to hold our queue items.
      Use List operations to implement the methods below */
  private List<T> list;
  
  /** Constructor for the Queue*/
  public Queue() {
    // instantiate list here
	  list = new List<T>();
  }
  
  /** Add a value to the end of the queue*/
  public void enqueue(T value) {
	  // adds value to the end of the list
	  list.append(value);
  }
  
  /** Delete and return the value at the front of the queue*/
  public T dequeue() {
	  // retrieves the value at the beginning of the list
	  T value = list.getValueAt(0);
	  // deletes the value at the beginning of the list
	  list.deleteAt(0);
	  return value;
  }

  /** Return the value at the front of the queue*/
  public T front() {
	  // retrieve the value at the beginning of the list
	  return list.getValueAt(0);
  }
  
  /** reverses the order of the list */
  public void reverse() {
	  // empty stack to reverse the list in
	  Stack<T> s = new Stack<T>();
	  
	  // while the list is not empty
	  while (list.size() > 1) {
		  // push the first value in the list to the stack
		  s.push(list.getValueAt(0));
		  // delete the first value in the list
		  list.deleteAt(0);
	  }
	  
	  // while the stack is not empty
	  while (!s.isEmpty()) {
		  // pop a value from the stack and append it to the list
		  list.append(s.pop());
	  }
  }

  /** Check if the queue is empty*/
  public boolean isEmpty() {
	  // check if the list size is zero
	  if (list.size() == 0) {
		  return true;
	  }
	  // return false otherwise
	  return false;
  }
}

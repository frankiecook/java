/** 
 * Name: Norman Cook
 * Date: 02/05/2020
 * Description: Min Heap
 * */

public class MinHeap {
	/** fields */
	public int[] array;
	public int count;
	
	/** default constructor */
	public MinHeap() {
		this.array = new int[7];
	}
	
	/** adds a new key */
	public void insert(int key) {
		// insert the new value at the current count index
		// increase count
		array[count] = key;
		count++;
		
		// call minHeapifyUp to sort the array
		minHeapifyUp();
	}
	
	/** returns the maximum key */
	public int findMin() {	
		// if the array is empty return -1
		if (isEmpty()) {
			return -1;
		} 
		
		// else return the root
		else {
			return array[0];
		}
	}
	
	/** returns the minimum key */
	public int extractMin() {
		// if the array is empty return -1
		if (isEmpty()) {
			return -1;
		} 
		
		// else extract the root
		else {
			// store the root in as a temporary variable
			// set the root equal to the last inserted value
			int min = array[0];
			array[0] = array[count - 1];
			array[count - 1] = 0;
			
			// decrease the counter
			// call minHeapifyDown to sort the array
			count--;
			minHeapifyDown();
			
			// return the minimum value
			return min;
		}
	}
	
	/** fixes max heap violations */
	public void minHeapifyUp() {
		// declare variables
		int parent;
		int child;
		int temp;
		
		// cycle from the last inserted value to the first
		for (int i=(count - 1); i > 0; i--) {
			// find the parent and child for the current index
			parent = (int) Math.floor((i - 1) / 2);
			child = i;
			
			// if the parent value is greater than the child value
			// swap the values
			if (array[parent] > array[child]) {
				temp = array[parent];
				array[parent] = array[child];
				array[child] = temp;
			}
		}
	}
	
	/** fixes max heap violations */
	public void minHeapifyDown() {
		minHeapifyDownRecursive(0);
	}
	
	/** checks if the heap is empty */
	public boolean isEmpty() {
		// cycles through every item in the array
		// the array is empty if all values are 0
		for (int i=0; i<array.length; i++) {
			if (array[i] != 0) {
				return false;
			}
		}
		return true;
	}
	
	/** minHeapifyDown Recursion */
	public void minHeapifyDownRecursive(int index) {
		// declare variables
		int parent = index;
		int leftChild = 2 * index + 1;
		int rightChild = 2 * index + 2;
		int temp;
		
		// if the right child index is not out of the counter's range
		if (rightChild < count) {
			// check if the left child value is less than the right child value
			if (array[leftChild] < array[rightChild]) {
				// swap the left child value with the parent value
				temp = array[parent];
				array[parent] = array[leftChild];
				array[leftChild] = temp;
				
				// if the leftchild index is still a root
				// call the function
				if (leftChild < 3) {
					minHeapifyDownRecursive(leftChild);
				}
			}
			
			// check if the left child value is greater than the right child value
			else if (array[leftChild] > array[rightChild]) {
				// swap the parent value with the right child value
				temp = array[parent];
				array[parent] = array[rightChild];
				array[rightChild] = temp;
				
				// if the right child index is still a root
				// call the function
				if (rightChild < 3) {
					minHeapifyDownRecursive(rightChild);
				}
			}
		}
		
		// if the left child is less than the counter
		else if (leftChild < count) {
			// check if the parent is greater than the left child value
			if (array[parent] > array[leftChild]) {
				// swap the parent value with the child value
				temp = array[parent];
				array[parent] = array[leftChild];
				array[leftChild] = temp;
			}
		}
	}
}
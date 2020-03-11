/** 
 * Name: Norman Cook
 * Date: 02/05/2020
 * Description: Max Heap
 * */

public class MaxHeap {
	/** fields */
	public int[] array;
	
	/** default constructor */
	public MaxHeap() {
		this.array = new int[7];
	}
	
	/** adds a new key */
	public void insert(int key) {
		// searches through the array for the first index that is 0
		int i = 0;
		while (array[i] != 0) {
			i++;
		}
		// sets that index to the key
		array[i] = key;
		// calls for the maxHeapifyUp
		maxHeapifyUp();
	}
	
	/** returns the maximum key */
	public int findMax() {
		// if the array is empty return -1
		if (isEmpty()) {
			return -1;
		} 
		// else return the root of the array
		else {
			return array[0];
		}
	}
	
	/** returns the maximum key */
	public int extractMax() {
		// if the array is empty return -1
		if (isEmpty()) {
			return -1;
		} 
		
		// else return the maximum value
		else {
			// store the root value in the array
			int max = array[0];
			// set the value at the root to zero
			array[0] = 0;
			// call maxHeapifyDown
			maxHeapifyDown();
			
			return max;
		}
	}
	
	/** fixes max heap violations */
	public void maxHeapifyUp() {
		// declare variables
		int parent;
		int child;
		
		// cycle through every element in the array
		for (int i=(array.length - 1); i > 0; i--) {
			// set the parent and child indexes that need to be checked
			parent = (int) Math.floor((i - 1) / 2);
			child = i;
			
			// if the current child is less than the parent
			// and the parent and child do not equal zero
			// swap the child and parent element
			if (array[parent] < array[child] && array[parent] != 0 && array[child] != 0) {
				int temp = array[parent];
				array[parent] = array[child];
				array[child] = temp;
			}
		}
	}
	
	/** fixes max heap violations */
	public void maxHeapifyDown() {
		// declare variables
		int parent;
		int leftChild;
		int rightChild;
		
		// cycle through the first three nodes
		// only the first three nodes can be parents
		for (int i=0; i<3; i++) {
			// find the parent and left child and right child index
			parent = i;
			leftChild = 2 * i + 1;
			rightChild = 2 * i + 2;
			
			// if the parent value is less than the left child value
			// and the parent is not zero
			// swap the parent value and left child value
			if (array[parent] < array[leftChild] || array[parent] == 0) {
				int temp = array[parent];
				array[parent] = array[leftChild];
				array[leftChild] = temp;
			}
			
			// if the parent value is less than the right child value
			// and the parent is not zero
			// swap the parent value and left child value
			if (array[parent] < array[rightChild] || array[parent] == 0) {
				int temp = array[parent];
				array[parent] = array[rightChild];
				array[rightChild] = temp;
			}
		}
	}
	
	/** checks if the heap is empty */
	public boolean isEmpty() {
		// check if any values in the list are not equal to zero
		// return false if any one value is not zero
		for (int i=0; i<array.length; i++) {
			if (array[i] != 0) {
				return false;
			}
		}
		
		// return false if every value in the list is zero
		return true;
	}
}

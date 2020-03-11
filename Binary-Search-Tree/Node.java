
public class Node {
	// fields for the Node Class
	/** left child link */
	private Node left;
	
	/** right child link */
	private Node right;
	
	/** integer data being held */
	private int data;
	
	
	// methods for the Node Class
	/** constructor takes in data to be stored */
	public Node(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
	
	/** accessor for left link */
	public Node getLeft() {
		return this.left;
	}
	
	/** accessor for right link */
	public Node getRight() {
		return this.right;
	}
	
	/** accessor for data */
	public int getData() {
		return this.data;
	}
	
	/** mutator for left link */
	public void setLeft(Node left) {
		this.left = left;
	}
	
	/** mutator for right link */
	public void setRight(Node right) {
		this.right = right;
	}
	
	/** mutator for data */
	public void setData(int data) {
		this.data = data;
	}
}

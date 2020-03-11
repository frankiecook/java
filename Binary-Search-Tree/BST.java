
public class BST {
	// fields for the binary search tree
	/** pointer to the root of the tree */
	public Node root;
	
	
	// methods for the binary search tree
	/** default constructor */
	public BST() {
		this.root = null;
	}
	
	/** inserts value into the logical place in the tree */
	public void insert(int value) {
		// create a new node with the given value
		Node tempNode = new Node(value);
		
		// if this is the first value make it the root
		if (root == null) {
			root = tempNode;
		}
		
		// check for the empty position to insert value
		else {
			checkPosition(root, tempNode);
		}
		
	}
	
	/** checks if given value is in the true */
	public boolean search(int value) {
		// create a new node initially set to the root
		Node tempNode = root;
		
		// search through the binary tree while the node is not empty
		while (tempNode != null) {
			// if the value is found return true
			if (tempNode.getData() == value) {
				return true;
			} else {
				// if the current node value is greater than the value given
				// grab the left node
				if (value < tempNode.getData()) {
					tempNode = tempNode.getLeft();
				}
				
				// if the current node value is less than the value given
				// grab the right node
				else if (value > tempNode.getData()) {
					tempNode = tempNode.getRight();
				}
			}
		}
		
		// return false if the value is never found
		return false;
	}
	
	/** deletes given value from the tree */
	public void delete(int value) {
		// variables for the parent node and the node of the given value
		Node parentNode = root;
		Node curNode = root;
		
		// check if the given value is in the tree
		if (search(value)) {
			
			// perform a binary search for the nod of the given value and its parent node
			while (curNode.getData() != value) {
				// go to the left node if the value is less than the current node value
				if (value < curNode.getData()) {
					// check if the left node exist
					if (curNode.getLeft() != null) {
						// store the parentNode
						if (curNode.getLeft().getData() == value) {
							parentNode = curNode;
						}
					}
					// grab the next left node
					curNode = curNode.getLeft();
				}
				
				// go to the right node if the value is less than the current node value
				else if (value > curNode.getData()) {
					// check if the right node exist
					if (curNode.getRight() != null) {
						// check if the left node exist
						if (curNode.getRight().getData() == value) {
							parentNode = curNode;
						}
					}
					// grab the next right node
					curNode = curNode.getRight();
				}
			}
			
			
			// case 0
			// the node has 0 children
			if (curNode.getLeft() == null && curNode.getRight() == null) {
				// set the left child node of the parent to null
				if (value < parentNode.getData()) {
					parentNode.setLeft(null);
				}
				
				// set the right child node of the parent to null
				else if (value > parentNode.getData()) {
					parentNode.setRight(null);
				}
			}
			
			
			// case 1
			// the node to be deleted has 1 child
			// the value is on the left side of the parent
			if (value < parentNode.getData()) {
				// the left node is empty and the right node exist
				if (curNode.getLeft() == null && curNode.getRight() != null) {
					parentNode.setLeft(curNode.getRight());
				}
				
				// the right node is empty and the left node exist
				else if (curNode.getLeft() != null && curNode.getRight() == null) {
					parentNode.setLeft(curNode.getLeft());
				}
			}
			
			// the value is on the right side of the parent
			else if (value > parentNode.getData()) {
				// the left node is empty and the right node exist
				if (curNode.getLeft() == null && curNode.getRight() != null) {
					parentNode.setRight(curNode.getRight());
				}
			
				// the right node is empty and the left node exist
				else if (curNode.getLeft() != null && curNode.getRight() == null) {
					parentNode.setRight(curNode.getLeft());
				}
			}
			
			
			// case 2
			// the node has two children
			if (curNode.getLeft() != null && curNode.getRight() != null) {
				// store the data from the successor to the given value's node 
				int successorData = successor(curNode).getData();
				
				// delete the successor to the given value
				delete(successor(curNode).getData());
				
				// set the given value's node to store the successor's value
				curNode.setData(successorData);
			}
		
		}
	}
	
	/** returns the smallest value in the tree */
	public int min() {
		// temporary node starting at the root
		Node tempNode = root;
		
		// when the temporary node does not exist return -1
		if (tempNode == null) {
			return -1;
		}
		
		// as long as the left node is not empty keep grabbing the left most node
		while (tempNode.getLeft() != null) {
			tempNode = tempNode.getLeft();
		}
		
		// return the value found
		return tempNode.getData();
	}
	
	/** returns the largest value in the tree */
	public int max() {
		// temporary node starting at the root
		Node tempNode = root;
		
		// when the temporary node does not exist return -1
		if (tempNode == null) {
			return -1;
		}
		
		// as long as the right node is not empty keep grabbing the right most node
		while (tempNode.getRight() != null) {
			tempNode = tempNode.getRight();
		}
		
		// return the value found
		return tempNode.getData();
	}
	
	/** inorder traversal */
	public String inorder() {
		// return an empty string if the root is empty
		if (root == null) {
			return "";
		} else {
			return inorderString(root, "");
		}
	}
	
	/** preorder traversal */
	public String preorder() {
		// return an empty string if the root is empty
		if (root == null) {
			return "";
		} else {
			return preorderString(root, "");
		}
	}
	
	/** postorder traversal */
	public String postorder() {
		// return an empty string if the root is empty
		if (root == null) {
			return "";
		} else {
			return postorderString(root, "");
		}
	}
	
	/** finds the position of a given node */
	private void checkPosition(Node curNode, Node tempNode) {
		// check if the given data is on the left side of th current node
		if (tempNode.getData() < curNode.getData()) {
			if (curNode.getLeft() == null) {
				curNode.setLeft(tempNode);
			} else {
				checkPosition(curNode.getLeft(), tempNode);
			}
		}
		
		// check if the data is on the right side of the current node
		else if (tempNode.getData() > curNode.getData()) {
			if (curNode.getRight() == null) {
				curNode.setRight(tempNode);
			} else {
				checkPosition(curNode.getRight(), tempNode);
			}
		}
	}
	
	/** creates the string for inorder traversal */
	private String inorderString(Node curNode, String order) {
		// check if the node is empty
		if (curNode != null) {
			// recursively call the inorderString function for the left node
			order = inorderString(curNode.getLeft(), order);
			
			// add the current parent node value to the string
			order += Integer.toString(curNode.getData()) + "  ";
			
			// recursively call the inorderString function for the right node
			order = inorderString(curNode.getRight(), order);
		}
		
		// return the string
		return order;
	}
	
	/** creates the string for preorder traversal */
	private String preorderString(Node curNode, String order) {
		if (curNode != null) {
			// add the current parent node value to the string
			order += Integer.toString(curNode.getData()) + "  ";
		
			// recursively call the preorderString function for the left node
			order = preorderString(curNode.getLeft(), order);
			
			// recursively call the preorderString function for the right node
			order = preorderString(curNode.getRight(), order);
		}
		
		// return the string
		return order;
	}
	
	/** creates the string for postorder traversal */
	private String postorderString(Node curNode, String order) {
		if (curNode != null) {		
			// recursively call the postorderString function for the left node
			order = postorderString(curNode.getLeft(), order);
			
			// recursively call the inorderString function for the right node
			order =  postorderString(curNode.getRight(), order);
			
			// add the current parent node value to the string
			order += Integer.toString(curNode.getData()) + "  ";
		}
		
		// return the string
		return order;
	}
	
	/** finds the successor for the given node */
	private Node successor(Node node) {
		// temporary node that starts as the right child for the given node
		Node tempNode = node.getRight();
		
		// grab the left node as long as the left child node is not empty
		while (tempNode.getLeft() != null) {
			tempNode = tempNode.getLeft();
		}
		
		// return the final node
		return tempNode;
	}
}

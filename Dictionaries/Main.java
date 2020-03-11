/**
 * Name: Norman Cook
 * Date: 02/21/2020
 * Description: Implementation of a dictionary class that uses a hash table
 * 	as the backing data structure.
 */

public class Main {

	public static void main(String[] args) {
		// Declare a new dictionary and the names and salaries in arrays
		Dictionary<String,Integer> dictionary = new Dictionary<String,Integer>();
		String[] names = {"Bob", "Bill", "Roger", "Kevin", "Jerry", "Liam"};
		int[] salaries = {50, 120, 80, 350, 65, 500};
		
		// inserts the given names and salaries into the dictionary
		for (int i=0; i<names.length; i++) {
			dictionary.insert(names[i], salaries[i]);
		}
		
		// prints every name key and the associated salary value
		for (int i=0; i<names.length; i++) {
			System.out.println(names[i] + " makes $" + dictionary.search(names[i]) + "K a year.");
		}
		System.out.println();
		
		// deletes Bob, Roger, and Jerry
		dictionary.delete("Bob");
		dictionary.delete("Roger");
		dictionary.delete("Jerry");
		
		// inserts Liam and 650
		dictionary.insert("Liam", 650);
		
		// returns if the person has been fired or that person's salary
		for (int i=0; i<names.length; i++) {
			if (dictionary.search(names[i]) == null) {
				System.out.println(names[i] + " got fired");
			} else {
			System.out.println(names[i] + " makes $" + dictionary.search(names[i]) + "K a year.");
			}
		}
	}

}

/** Dictionary */
class Dictionary<K,V> {
	private KeyValuePair<K,V>[] arr;
	private KeyValuePair<K,V> DELETED;
	
	/* constructor */
	public Dictionary() {
		this.arr = new KeyValuePair[7];
		this.DELETED = new KeyValuePair<K,V>(null, null); 
	}
	
	/** inserts a key value pair into the dictionary */
	public void insert(K key, V value) {
		int m = arr.length;
		int p = prehash(key);
		int attempt = 1;
		
		// check if the value at the key location is null, deleted, or present
		// and return the appropriate information
		while (attempt < m) {
			int index = hash(p, attempt);
			if (arr[index] == null || arr[index] == DELETED) {
				arr[index] = new KeyValuePair(key, value);
				break;
			} 
			
			else if (arr[index].getKey() == key) {
				arr[index].setValue(value);
				break;
			}
			attempt++;
		}
		
		/** dictionary resize does not work
		if (attempt >= m) {
			KeyValuePair<K,V>[] temp = new KeyValuePair[arr.length];
			System.arraycopy( arr, 0, temp, 0, arr.length);
			arr = new KeyValuePair[temp.length * 2];
			
			m = arr.length;
			p = prehash(key);
			attempt = 1;
			System.out.println(temp[0]);
			
			for (int i=0; i<temp.length; i++) {
				p = prehash(temp[i].getKey());
				int index = hash(p, 1);
				arr[p] = new KeyValuePair(temp[i].getKey(), temp[i].getValue());
			}
		}
		*/
		
	}
	
	/** searches the dictionary for the given key */
	public V search(K key) {
		int m = arr.length;
		int p = prehash(key);
		int attempt = 1;
		
		// if the current index value is null or deleted return null
		// or if the key is found return the value
		while (attempt < m) {
			int index = hash(p, attempt);
			if (arr[index] == null || arr[index] == DELETED) {
				return null;
			} 
			
			else if (arr[index].getKey() == key) {
				return arr[index].getValue();
			}
			attempt++;
		}
		return null;
	}
	
	/** delete the key value pair */
	public void delete(K key) {
		// hash
		int hash = hash(prehash(key), 1);
		arr[hash] = DELETED;
	}
	
	/** returns the prehash value of a key */
	private int prehash(K key) {
		return key.hashCode();
	}
	
	/** returns the hash of the given prehash */
	private int hash(int p, int i) {
		return (p % arr.length) + (i - 1);
	}
}
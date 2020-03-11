/**
 * Name: Norman Cook
 * Date: 12/17/2019 
 * Description: Creation of a People class based off of the class diagram.
 * 	This includes an addPerson() method, findPersonByName() method, and a
 * 	findPersonByAge() method.
 */

// Create People class here
/** Assigns People to a group array */
class People {
	
	/** declaration of an array and index for People */
	private Person[] group;
	private int index;
	
	// constructor
	// initializes the array and index for a group of People
	public People() {
		Person[] g = new Person[5];
		this.group = g;
		this.index = 0;
	}
	
	/** adds a Person object to the group array */
	public boolean addPerson(Person p) {
		
		// checks the index for empty spots in the group array
		if (this.index < 5) {
			this.group[this.index] = p;
			this.index++;
			return true;
		}
		return false;
	}

	/** finds a Person object by searching for the name */
	public Person findPersonByName(String name) {
		
		// iterates through the group array and compares the names
		for (int i = 0; i < this.group.length; i++) {
			Person currentPerson = this.group[i];
			
			// return the Person if name is found
			if (currentPerson.name.equals(name)) {
				return currentPerson;
			}
		}
		return null;
	}

	/** finds a Person object by searching for the age */
	public Person findPersonByAge(int age) {
		
		// iterates through each Person object in the group array
		for (int i = 0; i < this.group.length; i++) {
			Person currentPerson = this.group[i];
			
			// returns the Person if the age is found
			if (currentPerson.age == age) {
				return currentPerson;
			}
		}
			return null;
	}
}

// Put Person class from last assignment here (Student class is not needed)
/** creates a person with a name and age */
class Person {
	
	/** 
	 * declare variables for a person's name, age,
	 *  and whether or not the person is a student 
	 */
	public String name;
	public int age;
	public boolean isStudent;
	
	// constructor
	// initializes the name, age, and student state of the Person
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
		this.isStudent = false;
	}
	
	/** person's birthday increases the age of the person */
	public void birthday() {
		this.age++;
	}
	
	/** returns the name, age, and student classification of a person */
	public String toString() {
		String result = "";
		result += this.name + " is ";
		
		// check if the person is a student
		// add the state of being a student to the string
		if (this.isStudent) {
			result += "a student ";
		} else {
			result += "not a student ";
		}
		result += "and is " + this.age + " years old";
		return result;
	}

}

/** Main class - contains entry point */
// DO NOT MODIFY ANY CODE BELOW
public class Main {
  public static void main(String[] args) {
    // instantiate a new People object
    People people = new People();

    // add some Person objects
    boolean addedAllPeople = true;
    addedAllPeople &= people.addPerson(new Person("Eleanor",    38));
    addedAllPeople &= people.addPerson(new Person("Tahani",     33));
    addedAllPeople &= people.addPerson(new Person("Chidi",      39));
    addedAllPeople &= people.addPerson(new Person("Jason",      31));
    addedAllPeople &= people.addPerson(new Person("Michael", 20000));
    addedAllPeople &= people.addPerson(new Person("Janet",   10000));
    
    // create pointers to two Persons
    Person p1;
    Person p2;
    
    // initialize them with the results of our searches
    p1 = people.findPersonByName("Chidi");
    p2 = people.findPersonByAge(10000);
    
    // check if our results are correct
    Mysterious.checkAnswer(addedAllPeople, p1, p2);
  }
}

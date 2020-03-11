/*
* Name: Norman Cook
* Description: Implementation of three sorting algorithms: cocktail sort, quick sort,
* 	and counting sort.
*/

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// generate an array of 20 random integers
		Random random = new Random(); // create random object
		int[] arrayRandom = new int[20];
		for (int i = 0; i < arrayRandom.length; i++) {
			arrayRandom[i] = random.nextInt((151));
		}
		
		// make three copies of the array
		int[] arrayCocktail = new int[20];
		int[] arrayQuick = new int[20];
		int[] arrayCounting = new int[20];
		System.arraycopy(arrayRandom, 0, arrayCocktail, 0, arrayRandom.length);
		System.arraycopy(arrayRandom, 0, arrayQuick, 0, arrayRandom.length);
		System.arraycopy(arrayRandom, 0, arrayCounting, 0, arrayRandom.length);
		
		// send each sorting algorithm its own array
		Cocktail(arrayCocktail);
		quicksort(arrayQuick, 0, arrayQuick.length - 1);
		Counting(arrayCounting);
		
		// print the original array
		System.out.println("TESTING with n = 20");
		System.out.print("Original   List: ");
		printArray(arrayRandom);
		
		// print out each sorted algorithm's array
		System.out.print("  Cocktail Sorted: ");
		printArray(arrayCocktail);
		System.out.print("  Quick    Sorted: ");
		printArray(arrayQuick);
		System.out.print("  Counting Sorted: ");
		printArray(arrayCounting);
		System.out.println();
		
		// generate an array with 20000 random integers
		int[] arrayRandomNew = new int[20000];
		for (int i = 0; i < arrayRandomNew.length; i++) {
			arrayRandomNew[i] = random.nextInt((151));
		}
		
		// make three copies of the new array
		int[] arrayCocktailNew = new int[20000];
		int[] arrayQuickNew = new int[20000];
		int[] arrayCountingNew = new int[20000];
		System.arraycopy(arrayRandomNew, 0, arrayCocktailNew, 0, arrayRandomNew.length - 1);
		System.arraycopy(arrayRandomNew, 0, arrayQuickNew, 0, arrayRandomNew.length - 1);
		System.arraycopy(arrayRandomNew, 0, arrayCountingNew, 0, arrayRandomNew.length - 1);
		
		// run time of each algorithm
		long timeElapsed;
		System.out.println("TIMING with n = 20,000");
		Instant start = Instant.now();
		Cocktail(arrayCocktailNew);
		Instant finish = Instant.now();
		timeElapsed = Duration.between(start, finish).toMillis();
		System.out.println("  Cocktail took " + timeElapsed + " ms");
		
		start = Instant.now();
		quicksort(arrayQuickNew, 0, arrayQuickNew.length - 1);
		finish = Instant.now();
		timeElapsed = Duration.between(start, finish).toMillis();
		System.out.println("  Quick    took " + timeElapsed + " ms");
		
		start = Instant.now();
		Cocktail(arrayCocktailNew);
		finish = Instant.now();
		timeElapsed = Duration.between(start, finish).toMillis();
		System.out.println("  Cocktail took " + timeElapsed + " ms");
	}
	
	/** cocktail sorting */
	public static void Cocktail(int[] array) {
		boolean swapMade = false;
		int temp;
		
		for (int i=1; i<((array.length - 1) / 2 + 1); i++) {
			// forward direction
			swapMade = false;
			for (int j=i; j<(array.length - i); j++) {
				if (array[j] < array[j - 1]) {
					temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
					
					swapMade = true;
				}
			}
			
			if (!swapMade) {
				break;
			}
			
			// backward direction
			swapMade = false;
			for (int j=(array.length - i); j>(i - 1); j--) {
				if (array[j] < array[j - 1]) {
					temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
					
					swapMade = true;
				}
			}
			
			if (!swapMade) {
				break;
			}
		}
	}
	
	/** Quick Sorting Algorithm */
	public static void quicksort(int[] array, int left, int right) {
		if (left < right) {
			int p = partition(array, left, right);
			quicksort(array, left, p - 1);
			quicksort(array, p + 1, right);
		}
	}
	
	/** Quick Sorting Algorithm */
	public static int partition(int[] array, int left, int right) {
		int pivotPos = left;
		int temp;
		
		left++;
		while (true) {
			while (left < array.length && array[left] < array[pivotPos]) {
				left++;
			}
			
			while (right >= 0 && array[right] > array[pivotPos]) {
				right--;
			}
			
			if (left >= right) {
				break;
			} else {
				temp = array[left];
				array[left] = array[right];
				array[right] = temp;
				
				left++;
				right--;
			}
		}
		
		temp = array[right];
		array[right] = array[pivotPos];
		array[pivotPos] = temp;
		return right;
	}
	
	/** Counting Sort Algorithm */
	public static void Counting(int[] array) {
		// search for the max value
		int max = array[0];
		
		for (int i=0; i<array.length - 1; i++) {
			if (max < array[i + 1]) {
				 max = array[i + 1];
			}
		}
		
		// allocate count array and initialize to all zeros
		int[] count = new int[max + 1];
		
		// calculate the histogram of key frequencies
		for (int i=0; i<array.length; i++) {
			count[array[i]] = count[array[i]] + 1;
		}
		
		// calculate the starting index for each key
		int oldCount;
		int total = 0;
		for (int i=0; i<count.length; i++) {
			oldCount = count[i];
			count[i] = total;
			total = total + oldCount;
		}
		
		// allocate output array
		int[] output = new int[array.length];
		
		// copy to output array, keeping order of inputs with equal keys (aka stable sort)
		int value;
		for (int i=0; i<array.length; i++) {
			value = array[i];
			output[count[value]] = value;
			count[value] = count[value] + 1;
		}
		
		for (int i=0; i<array.length; i++) {
			array[i] = output[i];
		}
	}
	
	/** print out an array */
	public static void printArray(int[] array) {
		for (int i=0; i<array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
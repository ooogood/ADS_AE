
/**
 * @author 2669729C Ching-Yuan Chen
 */

public class BSTBag<E extends Comparable<E>> implements Bag<E> {

	// ////////// Inner class ////////////
	private static class Node<E extends Comparable<E>> {
		CountedElement<E> val;
		Node<E> l = null ,r = null;
		Node( E e ) {
			val = new CountedElement<E>( e );
		}
	}

	// ////////// Variables ////////////
	Node<E> root = null;
	int bagSize = 0;

	// ////////// Accessors ////////////

	// Return true if and only if this bag is empty.
	public boolean isEmpty() { 
		return bagSize == 0;
	}
	// Return the size of this set.
	public int size() { 
		return bagSize;
	}

	// Return true if and only if element is a member of this bag.
	public boolean contains(E element) {
		return true;
	}

	// Return true if and only if this bag is equal to that.
	public boolean equals(Bag<E> that) {
		return true;
	}

	// //////////Transformers ////////////

	// Make this bag empty.
	public void clear() {

	} 

	// Add element to bag.
	// increment the number of element items in the bag
	public void add(E element) {

	}

	// Remove it from this set.
	// Do nothing if no item in bag pertaining to element
	// otherwise decrement number of element items (lazy deletion)
	public void remove(E element) {

	}

	// ////////// Iterator ////////////

	// Return an iterator that will visit all members of this
	// bag, in no particular order
	public Iterator<E> iterator() {
		return null;
	}
}

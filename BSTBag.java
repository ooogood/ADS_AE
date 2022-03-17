import java.util.Iterator;

/**
 * @author 2669729C Ching-Yuan Chen
 * This BST implement lazy deletion (do not delete Node, only decrease the count)
 */

public class BSTBag<E extends Comparable<E>> implements Bag<E> {

	// ////////// BST elements ////////////
	private static class Node<E extends Comparable<E>> {
		CountedElement<E> val;
		Node<E> l = null ,r = null;
		Node( E e ) {
			val = new CountedElement<E>( e );
		}
	}
	// search for node that contains element e
	private Node<E> search( E e ) {
		Node<E> cur = root;
		while( true ) {
			if( cur == null ) return null;
			int cp = cur.val.getElement().compareTo( e );
			if( cp == 0 ) return cur;
			else if( cp < 0 ) cur = cur.r;
			else cur = cur.l;
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
		Node<E> node = search( element );
		return ( node != null && node.val.getCount() > 0 );
	}

	// Return true if and only if this bag is equal to that.
	public boolean equals(Bag<E> that) {
		// trivial case
		if( this.size() != that.size() ) return false;
		/**
		 * Complicated case:
		 *  1. Copy itself.
		 *  2. Traverse through that bag and delete the element inside the copy one by one
		 *  3. Return false is cannot perform deletion
		 */
		BSTBag<E> copy = this.copy();
		Iterator<E> it = that.iterator();
		while( it.hasNext() ) {
			if( !copy.remove_( it.next() ) ) return false;
		}
		return true;
	}

	// clone this bag
	private BSTBag<E> copy() {
		BSTBag<E> ret = new BSTBag<>();
		Iterator<E> it = iterator();
		while( it.hasNext() ) {
			ret.add( it.next() );
		}
		return ret;
	}

	// //////////Transformers ////////////

	// Make this bag empty.
	public void clear() {
		root = null;
		bagSize = 0;
	} 

	// Add element to bag.
	// increment the number of element items in the bag
	public void add(E element) {
		bagSize++;
		// special case: root is null
		if( root == null ) {
			root = new Node<>( element );
			return;
		}
		// invariant: cur must not be null
		Node<E> cur = root;
		while( true ) {
			int cp = cur.val.getElement().compareTo( element );
			// current node == element
			if( cp == 0 ) { 
				cur.val.setCount( cur.val.getCount() + 1 );
				return;
			}
			// current node < element
			else if( cp < 0 ) {
				if( cur.r == null ) {
					cur.r = new Node<>( element );
					return;
				}
				else
					cur = cur.r;
			}
			// current node > element
			else {
				if( cur.l == null ) {
					cur.l = new Node<>( element );
					return;
				}
				else
					cur = cur.l;
			}
		}
	}

	// Remove it from this set.
	// Do nothing if no item in bag pertaining to element
	// otherwise decrement number of element items (lazy deletion)
	public void remove(E element) {
		remove_( element );
	}
	// private version of remove
	// return if the removal is success
	private boolean remove_(E element) {
		Node<E> node = search( element );
		// only decrease the amount if the node exist and the count is bigger than 0
		if( node != null && node.val.getCount() > 0 ) {
			node.val.setCount( node.val.getCount() - 1 );
			bagSize--;
			return true;
		}
		return false;
	}

	// ////////// Iterator ////////////
	// notice: add or delete element during traversal might cause the Iterator malfunction
	private class InOrderIterator implements Iterator<E> {
		// notice: nodes in the stack might have 0 count
		private Stack<Node<E>> st = new LinkedStack<>();
		// record the elements left in this bag
		private int remain;
		// record the elements left in the topmost node in the stack
		private int topNodeRemain = 0;

		private InOrderIterator() {
			remain = bagSize;
			for( Node<E> cur = root; cur != null; cur = cur.l ) {
				st.push( cur );
			}
			if( remain > 0) {
				topNodeRemain = st.peek().val.getCount();
			}
		}

		public boolean hasNext() {
			return remain > 0;
		}

		public E next() {
			if( remain == 0 ) return null;
			// if the topmost node is out of elements
			while( topNodeRemain <= 0 ) {
				for( Node<E> cur = st.pop().r; cur != null; cur = cur.l ) {
					st.push( cur );
				}
				topNodeRemain = st.peek().val.getCount();
			}
			remain--;
			topNodeRemain--;
			return st.peek().val.getElement();
		}
	}

	// Return an iterator that will visit all members of this
	// bag, in no particular order
	public Iterator<E> iterator() {
		return new InOrderIterator();
	}

	// for testing purpose
	public String toString() {
		String ret = "";
		Iterator<E> it = iterator();
		while( it.hasNext() ) {
			ret += it.next().toString();
			ret += ", ";
		}
		return ret;
	}
}

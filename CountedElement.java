
/**
 * @author 2669729C Ching-Yuan Chen
 */

public class CountedElement<E extends Comparable<E>> implements Comparable<CountedElement<E>> {
	private E element;
	private int count;

	public CountedElement(E e, int count){
		//constructor - to complete
		this.element = e;
		this.count = count;
	}
	
	public CountedElement(E e){
		//constructor - to complete
		this.element = e;
		this.count = 1;
	}

	//add getters and setters
	// element getter method
	public E getElement() {
		return this.element;
	}
	// element setter method
	public void setElement( E element ) {
		this.element = element;
	}
	// count getter method
	public int getCount() {
		return this.count;
	}
	// count setter method
	public void setCount( int count ) {
		if( count < 0 ) throw new RuntimeException("Try to set count lower than zero!");
		this.count = count;
	}
	
	//add toString() method
	public String toString() {
		return "(" + element + "," + count + ")";
	}
	

	
	public int compareTo(CountedElement<E> sC1) {
		//to complete
		return this.element.compareTo( sC1.element );
	}

}


//import classes for file input - scanner etc.
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
//import implementing set (eg. TreeSet)
import java.util.TreeSet;

/**
 * @author 2669729C Ching-Yuan Chen
 */


public class WordProcessor {
	private static <E> String displaySet(Set<E> inputSet){
		//implement this static method to create a
		// String representation of set - 5 comma separated elements per line
		// assume that type E has a toString method
		String ret = "";
		int cnt = 0;
		for( E e : inputSet ) {
			ret += (e.toString() + ", ");
			cnt++;
			// newline if this line has 5 elements
			if( cnt == 5 ) {
				ret += "\n";
				cnt = 0;
			}
		}
		return ret;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// args = new String[]{"file0.txt", "file1.txt", "file2.txt"};
		//create a set of type String called wordSet
		Set<String> wordSet = new TreeSet<>();
		//create a set of type CountedElement<String> called countedWordSet 
		TreeSet<CountedElement<String>> countedWordSet = new TreeSet<>();
		
		//for each input file (assume 3 arguments, each the name of a file)
		for( String fn : args ) {
			// open file
			try (
				Scanner sc = new Scanner( new File( fn ) );
			) {
			//  for each word w
				while( sc.hasNext() ) {
					String wd = sc.next();
			//     if wordset doesnt contain w:
					if( !wordSet.contains( wd ) ) {
			//        add w to wordset
						wordSet.add( wd );
			//        add new element to countedWordSet
						countedWordSet.add( new CountedElement<String>( wd ) );
					}
			//     else
					else {
			//        increment numeric part of element in countedWordSet containing w
						var ce = countedWordSet.floor( new CountedElement<String>( wd ) );
						ce.setCount( ce.getCount() + 1 );
					}
				}
			} catch ( FileNotFoundException e ) { e.printStackTrace(); }
		}
		

	System.out.println(displaySet(countedWordSet));

	}
}

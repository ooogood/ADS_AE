
//import classes for file input - scanner etc.
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
//import implementing set (eg. TreeSet)

/**
 * @author 2669729C Ching-Yuan Chen
 */


public class WordProcessor2 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		args = new String[]{"file0.txt","file1.txt","file2.txt"};
		// args = new String[]{"file0.txt"};
		BSTBag<String> bst = new BSTBag<>();
		BSTBag<String> bst2 = new BSTBag<>();
		BSTBag<String> bst3 = new BSTBag<>();
		/**
		 * test add function
		 */
		//for each input file (assume 3 arguments, each the name of a file)
		for( String fn : args ) {
			// open file
			try (
				Scanner sc = new Scanner( new File( fn ) );
			) {
				while( sc.hasNext() ) {
					String wd = sc.next();
					bst.add( wd );
					bst2.add( wd );
					if( wd.length() <= 3 ) {
						bst3.add( wd );
					}
				}
			} catch ( FileNotFoundException e ) { e.printStackTrace(); }
		}
		System.out.println( bst );
		/**
		 * test contain
		 */
		System.out.print( bst.contains( "ant" ) );
		System.out.print( bst.contains( "jumper" ) );
		System.out.print( bst.contains( "zeal" ) );
		System.out.print( bst.contains( "xylophone" ) );
		System.out.print( bst.contains( "camping" ) );
		System.out.println(  );
		System.out.print( bst.contains( "antt" ) );
		System.out.print( bst.contains( "jumber" ) );
		System.out.print( bst.contains( "zea" ) );
		System.out.print( bst.contains( "xylophone zeal" ) );
		System.out.print( bst.contains( "amping" ) );
		System.out.println(  );
		/**
		 * test equals
		 */
		System.out.println( bst.equals( bst2 ) ); // true
		System.out.println( bst.equals( bst3 ) ); // false
		bst2.remove("ant");
		bst2.add("aunt");
		System.out.println( bst.equals( bst2 ) ); // false
		bst2.remove("cow");bst2.remove("cow");bst2.remove("cow");bst2.remove("cow");bst2.remove("cow");
		bst2.add("wow");bst2.add("wow");bst2.add("wow");bst2.add("wow");bst2.add("wow");bst2.add("wow");
		System.out.println( bst.equals( bst2 ) ); // false
		bst2.remove("aunt");
		bst2.add("ant");
		bst2.add("cow");bst2.add("cow");bst2.add("cow");bst2.add("cow");bst2.add("cow");
		bst2.remove("wow");bst2.remove("wow");bst2.remove("wow");bst2.remove("wow");bst2.remove("wow");bst2.remove("wow");
		System.out.println( bst.equals( bst2 ) ); // true
		/**
		 * test clear
		 */
		bst2.clear();
		System.out.println( bst2 );
		/**
		 * test remove
		 */
		bst.remove("ant");
		bst.remove("ant");
		bst.remove("ant");
		bst.remove("ant");
		bst.remove("ant");
		bst.remove("table");
		bst.remove("tablet");
		bst.remove("yak");
		bst.remove("yak");
		bst.remove("box");
		bst.remove("box");
		System.out.println( bst );
		bst2.add("ant");
		System.out.println( bst2 );
		bst2.remove("ant");
		System.out.println( bst2 );
	}
}

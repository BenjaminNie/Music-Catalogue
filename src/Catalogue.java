package ca.ubc.ece.eece210.mp3;

import java.io.*;
import java.util.*;

/**
 * Container class for all the albums and genres. Its main 
 * responsibility is to save and restore the collection from a file.
 * 
 * @author Benjamin Nie
 * 
 */
public final class Catalogue {
	
	protected List<Genre> myCatalogue;
	
	/**
	 * Builds a new, empty catalogue.
	 */
	public Catalogue() {
		myCatalogue = new ArrayList<Genre>();
	}

	/**
	 * Builds a new catalogue and restores its contents from the 
	 * given file. It populates the catalogue with all the 
	 * root genres.
	 * 
	 * @param fileName  - the file from where to restore the library.
	 * @throws FileNotFoundException 
	 */
	public Catalogue(String fileName) throws FileNotFoundException {
		this();
		Genre myGenre;
		Scanner input = new Scanner( new File( fileName ) );
		
		input.useDelimiter("<C!>");
		
		while( input.hasNext() ){
			myGenre = Genre.restoreCollection( input.next() );
			this.myCatalogue.add(myGenre);
		}
		input.close();
	}

	/**
	 * Saves the contents of the catalogue to the given file.
	 * @param fileName - the file where to save the library
	 * @throws FileNotFoundException 
	 */
	public void saveCatalogueToFile(String fileName) throws FileNotFoundException {
		int i,size;
		PrintStream outFile = new PrintStream( new File(fileName) );
		
		size= this.myCatalogue.size();
		
		for(i=0;i<size;i++){
			outFile.print("<C!>");
			outFile.print( this.myCatalogue.get(i).getStringRepresentation() );
		}
		outFile.close();
	}
}
package ca.ubc.ece.eece210.mp3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Represents a genre (or collection of albums/genres).
 * 
 * @author Benjamin Nie
 * 
 */
public final class Genre extends Element {
	
	private String myName;
	private static int index=0;	//This index is used to generate the different opening and closing identifiers
	
	/**
	 * Creates a new genre with the given name.
	 * 
	 * @param name
	 *            the name of the genre.
	 */
	public Genre(String name) {
		this.initArray(true);
		this.myName = name;
	}
	

	/**
	 * Restores a genre from its given string representation.
	 * 
	 * @param stringRepresentation - The format of the string representation can be 
	 * found in the README of the project.
	 * @return Genre - The root genre of the stringRepresentation
	 */
	public static Genre restoreCollection(String stringRepresentation) {
		Genre myGenre;
		Album myAlbum;
		String myString="";;
		
		Scanner input = new Scanner(stringRepresentation);
		input.useDelimiter(" ") ;
	
		myGenre = new Genre( input.nextLine() ); //First token in stringRepresentation is the root Genre.
		index++;	//Used to delimit the hierarchy of sub-genres
		while( input.hasNext() ){
				myString=input.next();
			if( myString.equals("*A!") ) {		//Identified an album.
				myAlbum = new Album( input.nextLine().trim() );
				myAlbum.addToGenre( myGenre );
			}
			else if ( myString.equals( "<@"+index+"!>" ) ){ //Identified a subGenre
				input.useDelimiter( "<@"+index+"!>" );
				myGenre.addToGenre( restoreCollection( input.next().trim() ) );
				input.nextLine(); //Get rid off the closing identifier
			}
			input.useDelimiter(" ");
		}
		
		index--;
		input.close();
		
		return myGenre;
	}

	/**
	 * Returns the string representation of a genre
	 * 
	 * @return the string representation -The format of the string representation can 
	 * be found in the README.
	 * 				
	 */
	@Override
	public String getStringRepresentation() {
		
		String myString;
		int i; //index to go through the children//
		List<Element> childList = new ArrayList<Element>();
		
		if(index==0)	
			myString = this.myName+ "\n"; 
		else
			myString = "<@"+index+"!> "+this.myName+ "\n"; //Generates the identifier for a sub-genre ( <"@1!>,<"@2!>...)
		
		index ++;	
		
		childList = this.getChildren();
		for( i=0; i<childList.size(); i++)	
			myString = myString + childList.get(i).getStringRepresentation() + "\n";
		
		index --;
		if(index != 0)
			myString = myString+ "<@"+index+"!>"; 	//Adds the closing identifier for a sub-genre ( <"@1!>,<"@2!>...)
		
		return myString;
	}
	
	/**
	 * Returns the string name of the genre
	 * 
	 * @return myName - 
	 */
	public String getString() {
		return myName;
	}

	/**
	 * Adds the given element to this genre. Throws an exception if the element already has a parent. 
	 * 
	 * @param b - the element to be added to the collection.
	 * @throws IllegalArgumentException
	 */
	public void addToGenre(Element b) {
		if( b.hasParent == false ){
			addChild(b);
		}
		else
			throw new IllegalArgumentException("This element cannot have multiple parents");

	}
	
	
	/**
	 * Removes the element from this genre. If the element doesn't have a parent throws an exception
	 * 
	 * @param b - the element to be removed from the genre.
	 * @throws IllegalArgumentException
	 */
	 public void removeFromGenre(Element b) {
		if(b.hasParent == true){
			removeChild(b);
		}
		else
			throw new IllegalArgumentException("Cannot remove this element, it doesn't have parent");
	}
	 
	 
	
	/**
	 * Returns true, since a genre can contain other albums and/or
	 * genres.
	 */
	@Override
	public boolean hasChildren() {
		return true;
	}
}
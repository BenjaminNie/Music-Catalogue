package ca.ubc.ece.eece210.mp3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Benjamin Nie
 * 
 * This class contains the information needed to represent 
 * an album in our application.
 * 
 */

public final class Album extends Element {

	private  String title;
	private  String performer;
	private  ArrayList<String> songList ;
	private  Genre genre=null;  //reference genre that has the parent genre the album belongs too
	
	/**
	 * Builds an album with the given title, performer and song list
	 * 
	 * @param title - the title of the album
	 * @param author - the performer 
	 * @param songlist - the list of songs in the album
	 */
	
	public Album(String title, String performer, ArrayList<String> songlist) {

		this.initArray(false);
		this.title = title;
		this.performer = performer;
		this.songList = new ArrayList<String>(songlist);
		
	}

	/**
	 * Builds an album from the string representation of the object. It is used
	 * when restoring an album from a file.
	 * 
	 * @param stringRepresentation- the string representation , the format for the 
	 * correct string representation can be found in the README.
	 */
	public Album(String stringRepresentation) {
		this.initArray( false );
		this.songList = new ArrayList<String>();

		Scanner input = new Scanner( stringRepresentation );
		input.useDelimiter("~");
		
		input.next(); //Skip the genre token.
		this.title=input.next();
		this.performer=input.next();
		
		while( input.hasNext() ){
			this.songList.add( input.next() );
		}
		
		input.close();	
	}

	/**
	 * Returns the string representation of the given album. The representation
	 * contains the title, performer and songlist, as well as the genre
	 * that the album belongs to. 
	 * 
	 * @return the string representation - The format for the album string representation
	 * can be found in the README. 
	 */
	@Override
	public String getStringRepresentation() {
		 String song="";
		 int i;
		 for(i = 0; i < this.songList.size(); i++){
			 song = song +"~"+ this.songList.get(i);
		 }
		return ("*A! "+genre.getString()+"~"+this.title+"~"+this.performer+song);
	}

	/**
	 * Adds this album to the given genre. If it is successful it will set 
	 * the internal reference genre to the passed genre. 
	 * Throws and exception if the album is already part of a genre.
	 * 
	 * @param genre - the genre to add the album to.
	 * @throws IllegalArgumentException
	 */
	
	public void addToGenre(Genre genre) {
		if(this.hasParent == false){
			this.genre = genre;
			genre.addToGenre( this );
		}
		else
			throw new IllegalArgumentException("Cannot add an album to multiple genres");
		
	}
	
	/**	
	 * Removes the album from the passed genre, if it is successful it will set the internal
	 * reference genre to null. Throws an exception if the album does not have a parent.
	 * 
	 * @param genre - genre from which the album is to be removed
	 * @throws IllegalArgumentException
	 */
	  public void removeFromGenre(Genre genre){
		if( this.hasParent == true ){
			genre.removeFromGenre(this);
			this.genre = null;
		}
		else
			throw new IllegalArgumentException("This Album does not have a parent");
	}
	 
	/**
	 * Returns the parent genre that this album belongs to.
	 * 
	 * @return the genre that this album belongs to
	 */
	public Genre getGenre() {
		return this.genre;
	}

	/**
	 * Returns the title of the album
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Returns the performer of the album
	 * 
	 * @return the performer
	 */
	public String getPerformer() {
		return this.performer;
	}

	/**
	 * An album cannot have any children (it cannot contain anything).
	 */
	@Override
	public boolean hasChildren() {
		return false;
	}
}

package ca.ubc.ece.eece210.mp3;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class to represent an entity in the catalogue. The element (in this
 * implementation) can either be an album or a genre.
 * 
 * @author Benjamin Nie
 * 
 */
public abstract class Element {

	
	protected List<Element> myChild ;
	protected  boolean hasParent = false;
	
	/**
	 * This method initializes a new holder for the children.
	 * @param b - true for Genres, false for albums
	 */
	protected void initArray(boolean b){
		if( b == true ){
			myChild = new ArrayList<Element>();
		}
		else{
			myChild = null;
		}
	}
	
	/**
	 * Returns all the children of this entity. They can be albums or
	 * genres. In this particular application, only genres can have
	 * children. Therefore, this method will return the albums or genres
	 * contained in this genre.
	 * 
	 * @return the children - the children for this element
	 */
	public List<Element> getChildren() {
			if(this.hasChildren()==false){
				throw new IllegalArgumentException("Leaf object does not have children");
			}
		return myChild;
	}

	/**
	 * Adds a child to this entity. If it is successful it will modify b so that 
	 * its status is that it has a parent. If we try to add to an Album it throws an exception. 
	 *
	 * @param b - the entity to be added.
	 * @throws IllegalArgumentException
	 */
	protected void addChild(Element b) {
		if( this.hasChildren() == false ){
			throw new IllegalArgumentException("Cannot add to leaf object");
		}
		else{
			myChild.add(b);
			b.hasParent = true;
		}
	}

	/**
	 * This method removes a child (either an album or genre) from this entity. If it is successful
	 * it will modify b so that its status is that it no longer has a parent. Throws an exception if 
	 * this entity does not contain the element.
	 * 
	 * @param b - The entity to be removed
	 */
	 protected void removeChild(Element b) {		 
		if( this.myChild.contains(b) && this.hasChildren()==true ){
			this.myChild.remove(b);
			b.hasParent = false;
		} 
		else
			throw new IllegalArgumentException("The parent does not contain this element");
	}
	 

	/**
	 * Abstract method to determine if a given entity can (or cannot) contain
	 * any children.
	 * 
	 * @return true if the entity can contain children, or false otherwise.
	 */
	public abstract boolean hasChildren();

	/**
	 * Abstract method to generate the string representation of an element.
	 * 
	 * @return string representation of the element
	 */
	public abstract String getStringRepresentation();

}
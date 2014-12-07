package ca.ubc.ece.eece210.mp3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class AlbumTest {
	
	/**
	 * This Test checks to see if we can successfully add an Album to a Genre
	 */
	@Test
	public void addAlbumTest() {
		ArrayList<String> songList = new ArrayList<String>();
		songList.add("Counting Stars");
		songList.add("Feel Again");
		
		Album myAlbum = new Album("Native","One Republic",songList);
		Genre myGenre = new Genre("Rock");
		
		myAlbum.addToGenre(myGenre);
		if( myGenre.getChildren().get(0) == myAlbum  )
			assertEquals(0,0);
		else
			assertEquals(1,0);
	}
	
	
	/**
	 * This Test checks to see if adding an album to two genres produces as error
	 */
	@Test
	public void addAlbumTest2() {
		ArrayList<String> songList = new ArrayList<String>();
		songList.add("Counting Stars");
		songList.add("Feel Again");
		
		Album myAlbum = new Album("Native","One Republic",songList);
		Genre myGenre = new Genre("Rock");
		Genre myGenre2 = new Genre("Pop");
		
		myAlbum.addToGenre(myGenre);	//Added to rock
		try{
			myAlbum.addToGenre(myGenre2);
			assertEquals(1,0);
		}catch(IllegalArgumentException e){
			assertEquals(0,0);
		}
	}
	
	
	/**
	 * This test checks to see if we can successfully remove an Album from a Genre 
	 */
	@Test
	public void removeAlbumTest() {
		ArrayList<String> songList = new ArrayList<String>();
		songList.add("Counting Stars");
		songList.add("Feel Again");
		
		Album myAlbum = new Album("Native","One Republic",songList);
		Genre myGenre = new Genre("Rock");
		
		myAlbum.addToGenre(myGenre);
		myAlbum.removeFromGenre(myGenre);
		if( myGenre.getChildren().size() == 0  )
			assertEquals(0,0);
		else
			assertEquals(1,0);
	}
	
	
	/**
	 * This test checks to see if an error is produced if we remove an album that doesn't have
	 * a parent genre.
	 */
	@Test
	public void removeAlbumTest2() {
		ArrayList<String> songList = new ArrayList<String>();
		songList.add("Counting Stars");
		songList.add("Feel Again");
		
		Album myAlbum = new Album("Native","One Republic",songList);
		Genre myGenre = new Genre("Rock");
		
		try{
			myAlbum.removeFromGenre(myGenre);
			assertEquals(1,0);
		}catch(IllegalArgumentException e){
			assertEquals(0,0);
		}
	}
	

	/**
	 * This test check to see if an error is produced is we remove an album from a genre
	 * that does not contain that album
	 */
	@Test
	public void removeAlbumTest3() {
		ArrayList<String> songList = new ArrayList<String>();
		songList.add("Counting Stars");
		songList.add("Feel Again");
		
		Album OneR = new Album("Native","One Republic",songList);
		Genre Rock = new Genre("Rock");
		Genre Pop = new Genre("Pop");
		
		OneR.addToGenre(Rock);
		
		try{
			OneR.removeFromGenre(Pop);
			assertEquals(1,0);
		}catch(IllegalArgumentException e){
			assertEquals(0,0);
		}
	}
	
	/**
	 * This test checks to see if we can generate the correct string representation of an Album
	 */
	@Test
	public void stringAlbumTest() {
		String myAlbumRep = "*A! Rock~Native~One Republic~Counting Stars~Feel Again";
		ArrayList<String> songList = new ArrayList<String>();
		songList.add("Counting Stars");
		songList.add("Feel Again");
		
		Album myAlbum = new Album("Native","One Republic",songList);
		Genre myGenre = new Genre("Rock");
		
		myAlbum.addToGenre(myGenre);
		
		if( myAlbum.getStringRepresentation().equals( myAlbumRep ) )	
			assertEquals(0,0);
		else
			assertEquals(1,0);
	}

	
	/**
	 * This test checks to see if we can generate an album from its string representation
	 */
	@Test
	public void generateAlbumTest() {
		String myAlbumRep = "*A! Rock~Native~One Republic~Counting Stars~Feel Again";
		
		Album myAlbum = new Album(myAlbumRep);
		if( myAlbum.getTitle().equals("Native") & myAlbum.getPerformer().equals("One Republic"))	
			assertEquals(0,0);
		else
			assertEquals(1,0);
	}

}

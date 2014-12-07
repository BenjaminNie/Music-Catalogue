package ca.ubc.ece.eece210.mp3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GenreTest {
	
	/**
	 * This tests checks to see if the generated stringRepresentation of a genre is correct
	 */
	@Test
	public void saveGenreTest() {
		//This should be the correct output of the stringRepresentation of the genre created
		String testGenreString = "Rock\n"+
				"<@1!> Pop\n"+
				"*A! Pop~Mylo~ColdPlay~Cancion 1~Cancion 2\n"+
				"<@1!>\n"+
				"*A! Rock~Echoes~Foo Fighters~Song 1~Song 2~Song 3\n"+
				"*A! Rock~Native~One Republic~Song 1~Song 2~Song 3\n";
		
		ArrayList<String> song = new ArrayList<String>();
		ArrayList<String> song2 = new ArrayList<String>();
		
		song.add("Song 1");
		song.add("Song 2");
		song.add("Song 3");

		song2.add("Cancion 1");
		song2.add("Cancion 2");
		
		Genre Rock = new Genre("Rock");
		Genre Pop = new Genre("Pop");
		
		Album FooF = new Album("Echoes","Foo Fighters", song);
		Album Mylo = new Album("Mylo","ColdPlay",song2);
		Album OneR = new Album("Native", "One Republic",song);
		
		Rock.addToGenre(Pop);
		FooF.addToGenre(Rock);
		Mylo.addToGenre(Pop);
		OneR.addToGenre(Rock);
		
		System.out.print(Rock.getStringRepresentation());
		
		if( Rock.getStringRepresentation().equals( testGenreString ) )
			assertEquals(1,1);
		else
			assertEquals(1,0);
	}
	
	/**
	 * This tests checks to see if we can correctly restore a genre from its stingRepresentation
	 */
	@Test
	public void recreateGenreTest() {
		String testGenreString = "Rock\n"+
				"<@1!> Pop\n"+
				"*A! Pop~Mylo~ColdPlay~Cancion 1~Cancion 2\n"+
				"<@1!>\n"+
				"*A! Rock~Echoes~Foo Fighters~Song 1~Song 2~Song 3\n"+
				"*A! Rock~Native~One Republic~Song 1~Song 2~Song 3\n";
		
		Genre myGenre;
		
		myGenre=Genre.restoreCollection( testGenreString );
		
		if( myGenre.getStringRepresentation().equals( testGenreString ) )
			assertEquals(1,1);
		else
			assertEquals(1,0);
		
	}

	/**
	 * This tests checks to see if an exception is thrown when we attempt to add 1 genre to multiple parents
	 */
	@Test
	public void inclusionGenreTest() {
		Genre Rock = new Genre("Rock");
		Genre Pop = new Genre("Pop");
		Genre World = new Genre("World");
		
		Rock.addToGenre(Pop); //Added Pop to Genre
		try{
			World.addToGenre(Pop); //Attempting to add Pop to World
			assertEquals(1,0);
		}catch(Exception e){
			assertEquals(1,1);
		}
		
		
	}

}

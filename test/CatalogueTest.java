package ca.ubc.ece.eece210.mp3;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

public class CatalogueTest {

	//This test checks to see if we are able to successfully save a catalog to a file
	@Test	
	public void saveCatalogTest() {
		
		Catalogue C1=new Catalogue();
		
		ArrayList<String> song = new ArrayList<String>();
		ArrayList<String> song2 = new ArrayList<String>();
		song.add("Song 1");
		song.add("Song 2");
		song.add("Song 3");
		song2.add("Cancion 1");
		song2.add("Cancion 2");
		
		Genre Rock = new Genre("Rock");
		Genre World = new Genre("World");
		Genre Latin = new Genre("Latin");
		Genre Country = new Genre("Country");
		Genre Juke = new Genre("Juke");
		Genre Pop = new Genre("Pop");
		Genre Metal = new Genre("Metal");
		Genre Unclassified = new Genre("Unclassified");
		 
		Album Ricky = new Album("Latin","Ricky",song);
		Album Celine = new Album("Me","Celine",song2);
		Album FooF = new Album("Echoes","Foo Fighters", song);
		Album Mylo = new Album("Mylo","ColdPlay",song2);
		Album U2 = new Album("Sunday","U2",song);
		Album OneR = new Album("Native", "One Republic",song);
		Album Yanni = new Album("Greatest Hits", "Yanni",song);
		 
		 Rock.addToGenre(Pop);
		 Mylo.addToGenre(Rock);
		 U2.addToGenre(Rock);
		 OneR.addToGenre(Pop);
		 Pop.addToGenre(Metal);
		 FooF.addToGenre(Metal);
		 Yanni.addToGenre(Unclassified);

		 World.addToGenre(Juke);
		 Celine.addToGenre(World);
		 Juke.addToGenre(Country);
		 Country.addToGenre(Latin);
		 Ricky.addToGenre(Latin);
		 
		 C1.myCatalogue.add(Rock);
		 C1.myCatalogue.add(World);
		 C1.myCatalogue.add(Unclassified);
		 
		 try {
			 C1.saveCatalogueToFile("saveCatalogTest");
			 assertEquals(1,1);
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
			 assertEquals(0,1);
		 }
		 
	}

	//This test checks to see if we can recreate a catalog from a file.
	@Test
	public void recreateCatalogTest() {
		int size;
		int i;
		Catalogue C1=new Catalogue();
		
		ArrayList<String> song = new ArrayList<String>();
		ArrayList<String> song2 = new ArrayList<String>();
		song.add("Song 1");
		song.add("Song 2");
		song.add("Song 3");
		song2.add("Cancion 1");
		song2.add("Cancion 2");
		
		Genre Rock = new Genre("Rock");
		Genre World = new Genre("World");
		Genre Latin = new Genre("Latin");
		Genre Country = new Genre("Country");
		Genre Juke = new Genre("Juke");
		Genre Pop = new Genre("Pop");
		Genre Metal = new Genre("Metal");
		Genre Unclassified = new Genre("Unclassified");
		 
		Album Ricky = new Album("Latin","Ricky",song);
		Album Celine = new Album("Me","Celine",song2);
		Album FooF = new Album("Echoes","Foo Fighters", song);
		Album Mylo = new Album("Mylo","ColdPlay",song2);
		Album U2 = new Album("Sunday","U2",song);
		Album OneR = new Album("Native", "One Republic",song);
		Album Yanni = new Album("Greatest Hits", "Yanni",song);

		 
		Rock.addToGenre(Pop);
		Mylo.addToGenre(Rock);
		U2.addToGenre(Rock);
		OneR.addToGenre(Pop);
		Pop.addToGenre(Metal);
		FooF.addToGenre(Metal);
		Yanni.addToGenre(Unclassified);
		
		World.addToGenre(Juke);
		Celine.addToGenre(World);
		Juke.addToGenre(Country);
		Country.addToGenre(Latin);
		Ricky.addToGenre(Latin);
		 
		C1.myCatalogue.add(Rock);
		C1.myCatalogue.add(World);
		C1.myCatalogue.add(Unclassified);
		
		 try {
				C1.saveCatalogueToFile("CatalogTest");
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 }
		
		//Make second catalog from string representation of first catalog
		 Catalogue C2=new Catalogue();
		 try {
			C2= new Catalogue("CatalogTest");
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 }
		 
		 
		 size = C1.myCatalogue.size();	
			
		 //Check to see that the stringRepresentation of the first catalog and second catalog
		 //are the same. In particular it checks that the stringRepresentation of the genres in
		 //both catalogs match
		 for(i=0;i<size;i++){
			 if( C1.myCatalogue.get(i).getStringRepresentation().equals
					 (C2.myCatalogue.get(i).getStringRepresentation() ) ){
			
			 }
			 else
				 assertEquals(0,1);
		 }
	}

}

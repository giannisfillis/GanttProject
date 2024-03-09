package tests;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import parser.FileLoader;


public class FileLoaderTest {
	
	private static FileLoader myLoader;
	private static FileLoader myWrongLoader;
	private static String correctFilePath = "C:\\Users\\konos\\OneDrive\\Υπολογιστής\\Eggs.tsv";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		myLoader = new FileLoader();
		myLoader.parseFile(correctFilePath,"	");
	}


	@Test
	public void testLoadFile00() { //HappyDay
		assertEquals("Test if all the file is loaded",14,myLoader.getLineCount());
	}
	
	@Test
	public void testLoadFile01() { //Wrong file path
		String filePath2 = "C:\\Users\\konos\\OneDrive\\Υπολογιστής\\ΗΑΗΑ.tsv";
		try {
			myWrongLoader = new FileLoader();
			myWrongLoader.parseFile(filePath2,"	");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals("The number of tasks should be 0",0,myWrongLoader.getLineCount());
	} 
	

}

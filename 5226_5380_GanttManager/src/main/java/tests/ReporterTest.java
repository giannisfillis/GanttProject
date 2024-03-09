package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import parser.FileLoader;
import reporter.Reporter;

public class ReporterTest {
	
	private static FileLoader myLoader;
	private static Reporter myReporter;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		myLoader = new FileLoader();
		myReporter = new Reporter();
		myLoader.parseFile("C:\\Users\\konos\\OneDrive\\Υπολογιστής\\Eggs.tsv","	");
	}
	
	@After
	public void delTable() throws Exception{ //delete table after each test method
		int i =0;
		while (!myLoader.getTable().isEmpty()) {
			myLoader.getTable().remove(i);
		}
	}

	
	//test txt

	@Test
	public void testToTxtFileHappy() throws IOException {  //Happy Day
		File testFile = new File("C:\\Users\\konos\\Downloads\\Eggs.txt"); 
		myLoader.fillTable();
		String filePath = "C:\\Users\\konos\\Downloads\\Eggs.txt";
		myReporter.WriteTxtFile(filePath, myLoader);
		
	    long lines = 0;
	    try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\konos\\Downloads\\Eggs.txt"))){
	         while (reader.readLine() != null) {
	        	  lines++;
	         }
	     } catch (IOException e) {
	    	 e.printStackTrace();
	       }
	    
		assertTrue(testFile.exists());
		assertEquals("test if file is saved in correct path","C:\\Users\\konos\\Downloads\\Eggs.txt",testFile.getPath());
		assertEquals("count lines",15,lines);
	}
	
	@Test
	public void testToTxtFileEmpty() throws IOException{  //Empty task list
		File testFile = new File("C:\\Users\\konos\\OneDrive\\Υπολογιστής\\Eggs.txt"); 
		String filePath = "C:\\Users\\konos\\OneDrive\\Υπολογιστής\\Eggs.txt";
		myReporter.WriteTxtFile(filePath, myLoader);
		
	    long lines = 0;
	    try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\konos\\OneDrive\\Υπολογιστής\\Eggs.txt"))){
	         while (reader.readLine() != null) {
	        	  lines++;
	         }
	     } catch (IOException e) {
	    	 e.printStackTrace();
	       }
	    
		assertTrue(testFile.exists());
		assertEquals("empty list",1,lines);
	}
	
	@Test
	public void testToTxtFileWrongPath() throws IOException {   //Wrong path
		String filePath = "C:\\Users\\konos\\OneDrive\\haha\\Eggs.txt";
		myReporter.WriteTxtFile(filePath, myLoader);
		File testFile = new File(filePath);
		
		assertFalse(testFile.exists());
		
	}
	
	//test html
	
	@Test
	public void testToHtmlFileHappy() throws IOException {  //Happy Day
		File testFile = new File("C:\\Users\\konos\\Downloads\\Eggs.html"); 

		myLoader.fillTable();
		String filePath = "C:\\Users\\konos\\Downloads\\Eggs.html";
		myReporter.WriteHtmlFile(filePath, myLoader);
		
	    long lines = 0;
	    try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\konos\\Downloads\\Eggs.html"))){
	         while (reader.readLine() != null) {
	        	  lines++;
	         }
	     } catch (IOException e) {
	    	 e.printStackTrace();
	       }
	    
		assertTrue(testFile.exists());
		assertEquals("test if file is saved in correct path","C:\\Users\\konos\\Downloads\\Eggs.html",testFile.getPath());
		assertEquals("count lines",16,lines);
	}
	
	@Test
	public void testToHtmlFileEmpty() throws IOException{  //Empty task list
		File testFile = new File("C:\\Users\\konos\\OneDrive\\Υπολογιστής\\Eggs.html"); 

		String filePath = "C:\\Users\\konos\\OneDrive\\Υπολογιστής\\Eggs.html";
		myReporter.WriteHtmlFile(filePath, myLoader);
		
	    long lines = 0;
	    try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\konos\\OneDrive\\Υπολογιστής\\Eggs.html"))){
	         while (reader.readLine() != null) {
	        	  lines++;
	         }
	     } catch (IOException e) {
	    	 e.printStackTrace();
	       }
	    
		assertTrue(testFile.exists());
		assertEquals("empty list",2,lines);
	}
	
	@Test
	public void testToHtmlFileWrongPath() throws IOException {   //Wrong path
		String filePath = "C:\\Users\\konos\\OneDrive\\haha\\Eggs.html";
		myReporter.WriteTxtFile(filePath, myLoader);
		File testFile = new File(filePath);
		
		assertFalse(testFile.exists());
		
	}
	
	//test markdown
	
	@Test
	public void testToMdFileHappy() throws IOException {  //Happy Day
		File testFile = new File("C:\\Users\\konos\\Downloads\\Eggs.md"); 

		myLoader.fillTable();
		String filePath = "C:\\Users\\konos\\Downloads\\Eggs.md";
		myReporter.WriteMarkdownFile(filePath, myLoader);
		
	    long lines = 0;
	    try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\konos\\Downloads\\Eggs.md"))){
	         while (reader.readLine() != null) {
	        	  lines++;
	         }
	     } catch (IOException e) {
	    	 e.printStackTrace();
	       }
	    
		assertTrue(testFile.exists());
		assertEquals("test if file is saved in correct path","C:\\Users\\konos\\Downloads\\Eggs.md",testFile.getPath());
		assertEquals("count lines",15,lines);
	}
	
	@Test
	public void testToMdFileEmpty() throws IOException{  //Empty task list
		File testFile = new File("C:\\Users\\konos\\OneDrive\\Υπολογιστής\\Eggs.md"); 

		String filePath = "C:\\Users\\konos\\OneDrive\\Υπολογιστής\\Eggs.md";
		myReporter.WriteMarkdownFile(filePath, myLoader);
		
	    long lines = 0;
	    try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\konos\\OneDrive\\Υπολογιστής\\Eggs.md"))){
	         while (reader.readLine() != null) {
	        	  lines++;
	         }
	     } catch (IOException e) {
	    	 e.printStackTrace();
	       }
	    
		assertTrue(testFile.exists());
		assertEquals("empty list",1,lines);
	}
	
	@Test
	public void testToMdFileWrongPath() throws IOException {   //Wrong path
		String filePath = "C:\\Users\\konos\\OneDrive\\haha\\Eggs.md";
		myReporter.WriteMarkdownFile(filePath, myLoader);
		File testFile = new File(filePath);
		
		assertFalse(testFile.exists());
		
	}
	

}

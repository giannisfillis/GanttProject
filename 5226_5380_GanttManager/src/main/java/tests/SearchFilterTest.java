package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import parser.FileLoader;
import parser.SearchFilter;

public class SearchFilterTest {
	
	private static FileLoader myLoader;
	private static SearchFilter myFilter;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		myLoader = new FileLoader();
		myLoader.parseFile("C:\\Users\\konos\\OneDrive\\Υπολογιστής\\Eggs.tsv","	");
		myLoader.fillTable();
		myFilter = new SearchFilter();
	}


	@Test
	public void testFilterByPrefix00() { //HappyDay
	ArrayList<String[]> ArrayListToBeInsertedToTable = myFilter.filterByPrefix(myLoader,"Put");
	assertEquals("The size of the table(number of tasks)",2,ArrayListToBeInsertedToTable.size());
	}
	
	@Test
	public void testFilterByPrefix01() { //insert absolutely nothing as a prefix
		ArrayList<String[]> ArrayListToBeInsertedToTable = myFilter.filterByPrefix(myLoader,"");
		assertEquals("The size of the table(number of tasks)",14,ArrayListToBeInsertedToTable.size());
	}
	
	@Test
	public void testFilterByPrefix02() { //insert a prefix that no task starts with
		ArrayList<String[]> ArrayListToBeInsertedToTable = myFilter.filterByPrefix(myLoader,"HAHA");
		assertEquals("The size of the table(number of tasks)",0,ArrayListToBeInsertedToTable.size());
	}
	
	@Test
	public void testFilterByID00() { //Happy Day
		ArrayList<String[]> ArrayListToBeInsertedToTable = myFilter.filterByID(myLoader,200);
		assertEquals("The size of the table(number of tasks)",1,ArrayListToBeInsertedToTable.size());
	}
	
	@Test
	public void testFilterByID01() { //No tasks with id given
		ArrayList<String[]> ArrayListToBeInsertedToTable = myFilter.filterByID(myLoader,690);
		assertEquals("The size of the table(number of tasks)",0,ArrayListToBeInsertedToTable.size());
	}
	
	@Test
	public void testTopLevel00() {  //Happy Day
		ArrayList<String[]> ArrayListToBeInsertedToTable = myFilter.filterByTopLevelTasks(myLoader);
		int actualOutput = ArrayListToBeInsertedToTable.size();
		int expectedOutput = 3;
		assertEquals("number of top level tasks",expectedOutput,actualOutput);
	}
	
	@Test
	public void testTopLevel01() {  //file not loaded
		FileLoader wrongLoader = new FileLoader();
		ArrayList<String[]> ArrayListToBeInsertedToTable = myFilter.filterByTopLevelTasks(wrongLoader);
		int actualOutput = ArrayListToBeInsertedToTable.size();
		int expectedOutput = 0;
		assertEquals("number of top level tasks",expectedOutput,actualOutput);
	}

}

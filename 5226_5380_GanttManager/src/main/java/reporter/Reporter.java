package reporter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import parser.FileLoader;


public class Reporter {
	
	public int WriteTxtFile(String filePath,FileLoader loadTasks) {
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(new FileOutputStream(filePath));
		} catch (FileNotFoundException e) {
			System.err.println("Problem opening Emp report.");
			return -1;
		}

		ArrayList<String[]> tasks = loadTasks.getTable();
		outputStream.println("TaskId	TaskText	MamaId	Start	End	Cost");

		for (int i=0; i<tasks.size(); i++) {
			for (int j=0; j<tasks.get(i).length; j++) {
				outputStream.print(tasks.get(i)[j]+ "	");
			}
			outputStream.println("");
		}
		outputStream.close();
		return 0;
	}
	
	public int WriteHtmlFile(String filePath,FileLoader loadTasks) {
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(new FileOutputStream(filePath));
		} catch (FileNotFoundException e) {
			System.err.println("Problem opening Emp report.");
			return -1;
		}
		ArrayList<String[]> tasks = loadTasks.getTable();
		
		String text = "<title>Gantt Project Data</title>";
		text += "<body><table><tr>"
				+ "<td>TaskId</td><td>TaskText</td><td>MamaId</td><td>Start</td>"
				+ "<td>End</td><td>Cost</td></tr>"
				+ "\n";
		
		for (int i=0; i<tasks.size(); i++) {
			text += "<tr>";
			for (int j=0; j<tasks.get(i).length; j++) {
				String s = tasks.get(i)[j];
				text += "<td>"+s+"</td>";
			}
			text += "</tr>"+ "\n";
		}
		text += "</table>";
		outputStream.println(text);
		outputStream.close();
		return 0;
	}
	
	public int WriteMarkdownFile(String filePath,FileLoader loadTasks) {
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(new FileOutputStream(filePath));
		} catch (FileNotFoundException e) {
			System.err.println("Problem opening Emp report.");
			return -1;
		}
		ArrayList<String[]> tasks = loadTasks.getTable();		
		
		String text = "*TaskId*	*TaskText*	*MamaId*	*Start*	*End*	*Cost*";
		outputStream.println(text);
		
		for (int i=0; i<tasks.size(); i++) {
			String s = "";
			for (int j=0; j<tasks.get(i).length; j++) {
				if (Integer.parseInt(tasks.get(i)[0])%100==0) {
					s += "**" + tasks.get(i)[j] + "**" + " ";
				}
				else {
					s += tasks.get(i)[j] + " ";
				}
			}
			outputStream.println(s);
		}
		outputStream.close();
		return 0;
	}
	
}
package parser;

import java.util.ArrayList;


public class SearchFilter {
	
	
	public ArrayList<String[]> filterByTopLevelTasks(FileLoader fileLoad) {
		ArrayList<String[]> table = new ArrayList<String[]>(); //table used in simpleTableModel
		for (int i=0;i<fileLoad.getLineCount();i++) {
			if(fileLoad.returnTopLevelTask(i) != null) {
				table.add(fileLoad.returnTopLevelTask(i));
			}
		}
		return table;
	}
	
	public ArrayList<String[]> filterByID(FileLoader fileLoad,int id){
		ArrayList<String[]> table = new ArrayList<String[]>(); //table used in simpleTableModel
		for (int i=0;i<fileLoad.getLineCount();i++) {
			if(fileLoad.returnTaskByID(i,id) != null) {
				table.add(fileLoad.returnTaskByID(i,id));
			}
		}
		return table;
	}
	
	public ArrayList<String[]> filterByPrefix(FileLoader fileLoad,String prefix){
		ArrayList<String[]> table = new ArrayList<String[]>(); //table used in simpleTableModel
		for (int i=0;i<fileLoad.getLineCount();i++) {
			if(fileLoad.returnTaskByPrefix(i,prefix) != null) {
				table.add(fileLoad.returnTaskByPrefix(i,prefix));
			}
		}
		return table;
	}

} //end class

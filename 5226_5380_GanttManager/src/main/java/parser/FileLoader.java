package parser;

import java.io.BufferedReader;
import dom.TopLevelTask;
import dom.SubTask;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class FileLoader {
	
	private String[] stringParts;
	private ArrayList<TopLevelTask> TopLevelTasksData = new ArrayList<TopLevelTask>();
	private ArrayList<SubTask> SubTasksData = new ArrayList<SubTask>();
	private ArrayList<String[]> table = new ArrayList<String[]>(); //table used in simpleTableModel
	private BufferedReader inputStream = null;
	private int lineCount=0;
	
	public int parseFile(String inputFilePath, String delimiter) throws FileNotFoundException, IOException{
		try{

			inputStream = new BufferedReader(new FileReader(inputFilePath));
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("\nFileNotFoundException: unable to open " + inputFilePath + "\n" + e.getLocalizedMessage()+ "\n"); 
		}
		String nextInputLine = "";
		while ((nextInputLine = inputStream.readLine()) != null) {
			stringParts = nextInputLine.split(delimiter);
				if (stringParts.length ==3){
					TopLevelTask myTopLevelTask = createTopLevelTask(stringParts);
					TopLevelTasksData.add(myTopLevelTask);
				} 
				else {
					SubTask mySubTask = createSubTask(stringParts);
					SubTasksData.add(mySubTask);
				}
				lineCount++;
		}
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				System.err.println("IOException: Could not close input file stream for file " + inputFilePath);
			}
		}
		System.out.println("Done with " + inputFilePath);
		sortVar();
		return lineCount;
	
	}
	
	public int getLineCount() {
		return lineCount;
	}
	
	public ArrayList<String[]> getTable(){
		return table;
	}
	
	public ArrayList<String[]> fillTable() { //fills the table
		for (int i=0;i<TopLevelTasksData.size();i++) {
			String[] tempArray = allocateTopLevelTasks(TopLevelTasksData,i);
			table.add(tempArray);
			for (int j=0;j<TopLevelTasksData.get(i).numberOfSubTasks();j++) {
				String[] tempArray2 = allocateSubTasks(TopLevelTasksData.get(i),j);
				table.add(tempArray2);
			}
		}
		return table;
	}
	
	public String[] allocateSubTasks(TopLevelTask myTopLevelTask, int i) { //helps with subtasks
		String myId = Integer.toString(myTopLevelTask.getSubTask(i).getID());
		String myTaskText = myTopLevelTask.getSubTask(i).toString();
		String myMamaId = Integer.toString(myTopLevelTask.getSubTask(i).getMamaID());
		String myStart = Integer.toString(myTopLevelTask.getSubTask(i).getStart());
		String myEnd = Integer.toString(myTopLevelTask.getSubTask(i).getEnd());
		String myCost = Double.toString(myTopLevelTask.getSubTask(i).getCost());
		String[] HelpingArray = {myId,myTaskText,myMamaId,myStart,myEnd,myCost};
		return HelpingArray;
	}
	
	public String[] allocateTopLevelTasks(ArrayList<TopLevelTask> myArrayList,int i) { //helps with topleveltasks
		String myId = Integer.toString(myArrayList.get(i).getID());
		String myTaskText = myArrayList.get(i).toString();
		String myMamaId = Integer.toString(myArrayList.get(i).getMamaID());
		String myStart = Integer.toString(myArrayList.get(i).getStart());
		String myEnd = Integer.toString(myArrayList.get(i).getEnd());
		String myCost = Double.toString(myArrayList.get(i).getCost());
		String[] HelpingArray = {myId,myTaskText,myMamaId,myStart,myEnd,myCost};
		return HelpingArray;
	}
	
	private TopLevelTask createTopLevelTask(String[] myArray) {
		int myId = Integer.parseInt(myArray[0]);
		String myTaskText = myArray[1];
		int myMamaId = Integer.parseInt(myArray[2]);
		int myStart = 0;
		int myEnd = 0;
		int myCost = 0;
		return new TopLevelTask(myId,myTaskText,myMamaId,myStart,myEnd,myCost);
		
	}
	
	private SubTask createSubTask(String[] myArray) {
		int myId = Integer.parseInt(myArray[0]);
		String myTaskText = myArray[1];
		int myMamaId = Integer.parseInt(myArray[2]);
		int myStart = Integer.parseInt(myArray[3]);
		int myEnd = Integer.parseInt(myArray[4]);
		int myCost = Integer.parseInt(myArray[5]);
		return new SubTask(myId,myTaskText,myMamaId,myStart,myEnd,myCost);
	}
	
	public void sortVar() {
		for (int i=0;i<SubTasksData.size();i++) {
			for (int j=0;j<TopLevelTasksData.size();j++) { //adds the subtasks to the toplevel tasks
				if (SubTasksData.get(i).getMamaID() == TopLevelTasksData.get(j).getID()) {
					TopLevelTasksData.get(j).addTask(SubTasksData.get(i));
					TopLevelTasksData.get(j).editCost();
					TopLevelTasksData.get(j).editEnd();
					TopLevelTasksData.get(j).editStart();
				}
			}
		
		}
		int pos;
		for (int i=0;i<TopLevelTasksData.size()-1;i++) { //sorts the topleveltasks
			pos = i;
			for (int j=i+1;j<TopLevelTasksData.size();j++) {
				if (TopLevelTasksData.get(j).getStart() < TopLevelTasksData.get(pos).getStart()) {
					pos = j;
				}
				if (TopLevelTasksData.get(j).getStart() == TopLevelTasksData.get(pos).getStart()) {
					if (TopLevelTasksData.get(j).getID() < TopLevelTasksData.get(pos).getID()) {
						pos = j;
					}
				}
			}
			swap(pos,i);
		}
	}
	
	public SubTask returnSubTask(int i) {
		return SubTasksData.get(i);
	}

	public String[] returnTopLevelTask(int i) { //checks if mamaID is 0
		if (Integer.parseInt(table.get(i)[2]) == 0) {
			return table.get(i);
		}
		return null;
	}

	public String[] returnTaskByID(int i,int id) {
		if (Integer.parseInt(table.get(i)[0]) == id) {
			return table.get(i);
		}
		return null;
	}

	public String[] returnTaskByPrefix(int i,String prefix) {
		String tablePrefix = table.get(i)[1].substring(0,prefix.length());
		if (tablePrefix.equals(prefix)) {
			return table.get(i);
		}
		return null; //no tasks found with requested prefix
	}	
	

	private void swap(int sourceIndex,int destIndex){        
        TopLevelTask temp = TopLevelTasksData.get(destIndex);
        TopLevelTasksData.set(destIndex, TopLevelTasksData.get(sourceIndex));
        TopLevelTasksData.set(sourceIndex, temp);
    }
	
} //end class

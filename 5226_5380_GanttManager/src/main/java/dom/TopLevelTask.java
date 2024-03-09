package dom;

import java.util.ArrayList;

public class TopLevelTask extends Task {

	private ArrayList<SubTask> myTasks = new ArrayList<SubTask>();
	
	public TopLevelTask(int id, String taskText,int mamaID , int start, int end, int cost) {
		super(id, taskText, mamaID , start,end,cost);
	}
	
	public int numberOfSubTasks() {
		return myTasks.size();
	}
	
	public SubTask getSubTask(int i) {
		return myTasks.get(i);
	}
	
	public void addTask(SubTask task) {
		myTasks.add(task);
		sortSubTasks();
	}
	
	public void editStart() {
		setStart(myTasks.get(0).getStart());
	}
	
	public void editEnd() {
		setEnd(myTasks.get(myTasks.size()-1).getEnd());
	}
		
	public void editCost() {
		int totalCost = 0;
		for (int i=0; i<myTasks.size(); i++) {
			totalCost += myTasks.get(i).getCost();
		}
		setCost(totalCost);
	}
	
	
	private void sortSubTasks() {
		int pos;
		if (myTasks.size() == 1) {
			return;
		}
			for (int i=0;i<myTasks.size()-1;i++) { //sorts the subtasks
				pos = i;
				for (int j=i+1;j<myTasks.size();j++) {
					if (myTasks.get(j).getStart() == myTasks.get(pos).getStart()) {
						if (myTasks.get(j).getID() < myTasks.get(pos).getID()) {
							pos = j;
						}
					}
					else {
						if (myTasks.get(j).getStart() < myTasks.get(pos).getStart()) {
							pos = j;
						}
					}
				}
			swap(pos,i);
		}
	}
	
	private void swap(int sourceIndex,int destIndex){        
		SubTask temp = myTasks.get(destIndex);
		myTasks.set(destIndex, myTasks.get(sourceIndex));
		myTasks.set(sourceIndex, temp);
    }
	
}

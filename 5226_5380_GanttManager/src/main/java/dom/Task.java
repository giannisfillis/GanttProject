package dom;

public abstract class Task 
{
	private int id;
	private int mamaID;
	private String taskText;
	private int start;
	private int end;
	private double cost;
	
	public Task(int id, String taskText,int mamaID , int start, int end, int cost)
	{
		this.id = id;
		this.mamaID = mamaID;
		this.taskText = taskText;
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
	
	public int getID(){
		return id;
	}
	
	public int getMamaID() {	
		return mamaID;
	}

	
	public String toString() {
		return taskText;
	}
	
	public double getCost() {
		return cost;
	}
	
	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setMamaID(int id){
		this.mamaID = id;
	} 
	
	public void setStart(int start) {
		this.start = start;
	}

	
	public void setEnd(int end) {
		this.end = end;
	} 
	public void setCost(int cost) {
		this.cost = cost;
	} 

}

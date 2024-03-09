package dom;

public class SubTask extends Task
{
	
	private TopLevelTask myTopLevel;

	public SubTask(int id, String taskText,int mamaID , int start, int end, int cost) 
	{
		super(id, taskText, mamaID , start,end,cost); 		
	}
	
	public void setMyID(){
		setMamaID(myTopLevel.getID());
	}
	
	public void setMyTopLevel(TopLevelTask top){
		myTopLevel = top;
	}
}

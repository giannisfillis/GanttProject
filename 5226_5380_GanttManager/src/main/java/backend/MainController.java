package backend;

import java.io.FileNotFoundException;
import java.io.IOException;

import dom2app.SimpleTableModel;
import parser.FileLoader;
import parser.SearchFilter;
import reporter.Reporter;

public class MainController implements IMainController {

	private FileLoader fileLoad = new FileLoader();
	private String[] COLUMN_NAMES = {"TaskId" , "TaskText", "MamaId","Start" , "End" , "Cost" };
	private SearchFilter myFilter = new SearchFilter();
	
	
	@Override
	public SimpleTableModel load(String fileName, String delimiter) {
		try {
			fileLoad.parseFile(fileName,delimiter);
			SimpleTableModel myTable = new SimpleTableModel("Name","PrjName",COLUMN_NAMES,fileLoad.fillTable());
			return myTable;
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException " +  "\n" + e.getLocalizedMessage()+ "\n");
		} catch (IOException e) {
			System.err.println("IOException " +  "\n" + e.getLocalizedMessage()+ "\n");		}
		return null;
	}

	@Override
	public SimpleTableModel getTasksByPrefix(String prefix) {
		SimpleTableModel myTable = new SimpleTableModel("Name","PrjName",COLUMN_NAMES,myFilter.filterByPrefix(fileLoad,prefix));
		return myTable;
	}

	@Override
	public SimpleTableModel getTaskById(int id) {
		SimpleTableModel myTable = new SimpleTableModel("Name","PrjName",COLUMN_NAMES,myFilter.filterByID(fileLoad,id));
		return myTable;
	}

	@Override
	public SimpleTableModel getTopLevelTasks() {
		SimpleTableModel myTable = new SimpleTableModel("Name","PrjName",COLUMN_NAMES,myFilter.filterByTopLevelTasks(fileLoad));//At the last position I had table
		return myTable;
	}

	@Override
	public int createReport(String path, ReportType type) {
		Reporter Report = new Reporter();
		if (type.equals(ReportType.TEXT)) {
			Report.WriteTxtFile(path,fileLoad);
			return fileLoad.getLineCount();
		}
		if (type.equals(ReportType.HTML)) {
			Report.WriteHtmlFile(path,fileLoad);
			return fileLoad.getLineCount();
		}
		if (type.equals(ReportType.MD)) {
			Report.WriteMarkdownFile(path,fileLoad);
			return fileLoad.getLineCount();
		}
		return -1;
	}

}
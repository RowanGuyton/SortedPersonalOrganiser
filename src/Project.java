
public class Project {
	private String projectName;
	private String projectStartDate;
	private String projectDueDate;
	private String projectPriority;
	
	public Project (String projectName, String projectStartDate, 
			String projectDueDate, String projectPriority) {
		
		this.projectName = projectName;
		this.projectStartDate = projectStartDate;
		this.projectDueDate = projectDueDate;
		this.projectPriority = projectPriority;
	}
	
	public String getProjectName(){
		return projectName;
	}
	
	public String getprojectStartDate(){
		return projectStartDate;
	}
	
	public String getProjectDueDate(){
		return projectDueDate;
	}
	
	public String getProjectPriority(){
		return projectPriority;
	}

}

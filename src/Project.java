/**
 * @author Rowan Guyton
 * Project class is a data transfer object used for instantiating 
 * Project objects to be manipulated by GUI elements
 */
public class Project {												// 4 private variables encapsulate all necessary data
	private String projectName;										// String data type used for ubiquity currently
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
	
	public String getProjectName(){									// Project Name getter
		return projectName;
	}
	
	public String getprojectStartDate(){							// Project Start Date getter
		return projectStartDate;
	}
	
	public String getProjectDueDate(){								// Project Due Date getter
		return projectDueDate;
	}
	
	public String getProjectPriority(){								// Project Priority getter 
		return projectPriority;
	}

}


public class ProjectTest {

	public static void main(String[] args) {
		
		Project testProject = new Project("Sorted Personal Organiser", "01/02/2016", "29/04/2016", "HIGH");
		
		System.out.println("Your project "+testProject.getProjectName()+" is due on "+testProject.getProjectDueDate());
		
	}

}

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;


public class ButtonClickHandlers {
	
}
/*
	NewProjectGUI newProjectGUI = new NewProjectGUI();
	
	public void newProjectHandler() {
		Project newProject = new Project(
				newProjectTextField.getText(),
				LocalDate.now().toString(),
				(newProjectDateSpinnerDay.getValue().toString()+newProjectDateSpinnerMonth.getValue().toString()+newProjectDateSpinnerYear.getValue().toString()),
				newProjectComboBox.getSelectedItem().toString());
		
		newProjectTextField.setText(null);
		newProjectNameLbl.setText("Sorted!");
		
		//ProjectArraysAndStorage addNewProject = new ProjectArraysAndStorage();
		projectArraysAndStorage.addProjectToArrayList(newProject);
		
		newProjectTextField.setText(projectArraysAndStorage.projectArrayListSizeToString());
		
		File projectList = new File("projectlist.txt");
		
		try {
			PrintWriter projectPrinter = new PrintWriter (new BufferedWriter (new FileWriter(projectList, true)));
			projectPrinter.print(newProject.getProjectName()+",	"+newProject.getprojectStartDate()+",	"+newProject.getProjectDueDate()+",	"+newProject.getProjectPriority()+"\n");
			projectPrinter.close();
			} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		try {
			projectArraysAndStorage.newProjectManipulableList();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
}
}
*/
	
	


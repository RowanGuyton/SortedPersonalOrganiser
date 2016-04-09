import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjectArraysAndStorage {
	
	/*
	List <String> projectNameList;
	List <String> projectStartDateList;
	List <String> projectDueDateList;
	List <String> projectUrgencyList;
	*/
	String[] projectNames;
	String[] projectStartDates; 
	String[] projectDueDates;
	String[] projectPriorities;
	ProjectTest testAddingProjectToArrayList = new ProjectTest();
	String[] projectsStringArray = new String[50];
	ArrayList<Project> projectArrayList = new ArrayList<Project>();


	
public int getProjectsArrayLength() {
	return projectsStringArray.length;
}

public int getProjectArrayListSize() {
	return projectArrayList.size();
}

public void addProjectToArrayList(Project newProject) {
	projectArrayList.add(newProject);
}

public void newProjectManipulableList() throws FileNotFoundException {
	File projectFile = new File("projectlist.txt");
	Scanner scanIntoList = new Scanner(projectFile);
	int i = 0;
	while (scanIntoList.hasNextLine() && i < projectsStringArray.length) {
        projectsStringArray[i] = scanIntoList.nextLine();
        i++; 
	}
	scanIntoList.close();	
}

public void retrieveValuesFromTextStrings() throws IOException {
	List <String> projectNameList = new ArrayList<String>();
	List <String> projectStartDateList = new ArrayList<String>();
	List <String> projectDueDateList = new ArrayList<String>();
	List <String> projectUrgencyList = new ArrayList<String>();

	File myFile = new File("projectlist.txt");
	FileReader reader = new FileReader(myFile);
    BufferedReader bufferedReader = new BufferedReader(reader);
    
	String savedProjectValues;
	String[] splitProjectValues;
	while ((savedProjectValues = bufferedReader.readLine()) != null) {
		splitProjectValues = savedProjectValues.split(" ");
		
		if (splitProjectValues != null && splitProjectValues.length==4) {
			projectNameList.add(splitProjectValues[0]);
			projectStartDateList.add(splitProjectValues[1]);
			projectDueDateList.add(splitProjectValues[2]);
			projectUrgencyList.add(splitProjectValues[3]);
		}
	}
}

public String projectArrayListSizeToString() {
	// TODO Auto-generated method stub
	return String.valueOf(projectArrayList.size());
}



}

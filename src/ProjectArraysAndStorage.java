import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * @author Rowan
 * 
 */
public class ProjectArraysAndStorage { 
	
	/*
	List <String> projectNameList;
	List <String> projectStartDateList;
	List <String> projectDueDateList;
	List <String> projectUrgencyList;
	*/
	//String[] projectNames;
	//String[] projectStartDates; 
	//String[] projectDueDates;
	//String[] projectPriorities;
	ProjectTest testAddingProjectToArrayList = new ProjectTest();
	String[] projectNameArray = new String[50];
	String[] projectStartDatesArray = new String[50];
	String[] projectDueDatesArray = new String[50];
	String[] projectPriorityArray = new String[50];
	ArrayList<Project> projectArrayList = new ArrayList<Project>();
	


	
public int getProjectsArrayLength() {												// getter returns length of projectsStringArray array
	return projectNameArray.length;
}

public int getProjectArrayListSize() {												//Getter returns size of projectArrayList ArrayList
	return projectArrayList.size();
}

public void addProjectToArrayList(Project newProject) {								//once ProjectArraysAndStorage class instantiated,
	projectArrayList.add(newProject);												//method takes newProject created by btnCreate event handler as parameter,
}																					// adds newly instantiated Project Object to projectArrayList

public String[] getProjectNameArray() {
	return projectNameArray;
}

public void newProjectManipulableList() throws FileNotFoundException {				//Method populates an array using while loop to scan values
	File projectNames = new File("projectnames.txt");	
	File projectStartDates = new File("projectstartdates.txt");						//into array from text file 
	File projectDueDates = new File("projectduedates.txt");
	File projectPriorityLevels = new File("projectprioritylevels.txt");
	
	Scanner scanNames = new Scanner(projectNames);
	Scanner scanStartDates = new Scanner(projectStartDates);
	Scanner scanDueDates = new Scanner(projectDueDates);
	Scanner scanPriorityLevels = new Scanner(projectPriorityLevels);
	
	try{
	int i = 0;
	
	while (scanNames != null && (scanNames.hasNextLine() && i < projectNameArray.length)) {			
        projectNameArray[i] = scanNames.nextLine();
        projectStartDatesArray[i] = scanStartDates.nextLine();
        projectDueDatesArray[i] = scanDueDates.nextLine();
        projectPriorityArray[i] = scanPriorityLevels.nextLine();
        i++; 
        //
	}
	scanNames.close();
	scanStartDates.close();
	scanDueDates.close();
	scanPriorityLevels.close();
	}
	catch (NoSuchElementException e){
		// TODO Auto-generated catch block
					e.printStackTrace();
	}
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

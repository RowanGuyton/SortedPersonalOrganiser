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
	
	
	ProjectTest testAddingProjectToArrayList = new ProjectTest();
	String[] projectNameArray = new String[50];
	String[] projectStartDatesArray = new String[50];
	String[] projectDueDatesArray = new String[50];
	String[] projectPriorityArray = new String[50];
	ArrayList<Project> projectArrayList = new ArrayList<Project>();
	


	
public int getProjectsArrayLength() {												// getter returns length of projectsStringArray array
	return projectNameArray.length;
}

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
	
	while (scanNames != null && (scanNames.hasNextLine() && i < projectNameArray.length)) {		//Assumes all four Arrays will contain equal quantities of strings	
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
	catch (NoSuchElementException e){													//Prevent Exception being thrown at first run with no elements in arrays
		// TODO Auto-generated catch block
					e.printStackTrace();
	}
}

}

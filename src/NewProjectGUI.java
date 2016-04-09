import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.*;
import javax.swing.*;

public class NewProjectGUI {
	
	ButtonClickHandlers buttonClickHandlers = new ButtonClickHandlers();
	ProjectArraysAndStorage projectArraysAndStorage = new ProjectArraysAndStorage();
	ArrayList<Project> projectArrayList = new ArrayList<Project>();
	ArrayList<String> projectlist = new ArrayList<String>();
	String projectName;
	private JFrame newProjectFrame;
	private JTabbedPane sortedTabbedPane;
	private JPanel newProjectPanel, allProjectsPanel, calendarPanel;
	private JLabel newProjectNameLbl, lblNewProjectDueDateLbl, lblNewProjectUrgencyLbl;
	private JLabel allProjectsNameLbl, allProjectsStartDateLbl, allProjectsDueDateLbl, allProjectsUrgencyLbl; 
	private JScrollPane projectListScroller;
	private JList<String> allProjectsList;
	private JTextField newProjectTextField, indProjectNameTextField, indProjectStartDateTextField, indProjectDueDateTextField, indProjectUrgencyTextField;
	private JSpinner newProjectDateSpinnerDay, newProjectDateSpinnerMonth, newProjectDateSpinnerYear;
	private JComboBox<Object> newProjectComboBox;
	private JButton btnCreate, btnViewAll, btnViewSingleProject, btnMoveToNewProject;
	
	public static void main(String[] args) {											//Main Method
		new NewProjectGUI();

	}
	
	public NewProjectGUI() {															//CONSTRUCTOR
		generateNewProjectForm();
		newProjectLabels();
		newProjectTextField();
		newProjectButtons();
		newProjectDateSpinners();
		newProjectComboBox();
		allProjectsList();
		allProjectsButtons();
		allProjectsIndividualView();
		
		newProjectFrame.add(sortedTabbedPane);
		newProjectFrame.setVisible(true);
	}
	
	public void generateNewProjectForm() {												//Frame and Panel Instantiation
		newProjectFrame = new JFrame();
		newProjectFrame.setTitle("Sorted! (New Project)");
		newProjectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newProjectFrame.setSize(800,600);
		newProjectFrame.setResizable(false);
		
		newProjectPanel = new JPanel();
		newProjectPanel.setLayout(null);
		allProjectsPanel = new JPanel();
		allProjectsPanel.setLayout(null);
		calendarPanel = new JPanel();
		calendarPanel.setLayout(null);
		
		sortedTabbedPane = new JTabbedPane();
		sortedTabbedPane.addTab("New Project", newProjectPanel);
		sortedTabbedPane.addTab("All Projects", allProjectsPanel);
		sortedTabbedPane.addTab("Calendar", calendarPanel);
	}
	
	public void newProjectLabels() {													//JLabels for New Project Tab
		newProjectNameLbl = new JLabel("Name Your Project:");
		newProjectNameLbl.setBounds(250, 140, 150, 20);
		newProjectPanel.add(newProjectNameLbl);
		
		lblNewProjectDueDateLbl = new JLabel("When is it due?");
		lblNewProjectDueDateLbl.setBounds(250, 180, 150, 20);
		newProjectPanel.add(lblNewProjectDueDateLbl);
		
		lblNewProjectUrgencyLbl = new JLabel("How urgent is it?");
		lblNewProjectUrgencyLbl.setBounds(250, 220, 150, 20);
		newProjectPanel.add(lblNewProjectUrgencyLbl);
	}
	
	public void newProjectTextField() {													//JTextField for New Project Tab
		newProjectTextField = new JTextField();
		newProjectTextField.setBounds(400, 150, 150, 20);
		newProjectPanel.add(newProjectTextField);
	}
	
	public void newProjectDateSpinners() {						
		
		String[] yearStrings = {								//Year String instantiation
				"2016","2017","2018","2019","2020"
				}; 
		
		SpinnerListModel yearModel = new SpinnerListModel(yearStrings);
		
		newProjectDateSpinnerYear = new JSpinner(yearModel);
		newProjectDateSpinnerYear.setBounds(550, 180, 100, 25);
		newProjectPanel.add(newProjectDateSpinnerYear);
		
																//Day String instantiation
		String[] dayStrings = {									
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
				"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
				"24", "25", "26", "27", "28", "29", "30", "31"
				};
		
		SpinnerListModel dayModel = new SpinnerListModel(dayStrings);
		
		newProjectDateSpinnerDay = new JSpinner(dayModel);
		newProjectDateSpinnerDay.setBounds(500, 180, 50, 25);
		newProjectPanel.add(newProjectDateSpinnerDay);
		
		String[] monthStrings = {								//Month String instantiation	
				"January", "February", "March", "April",
				"May", "June", "July", "August",
				"September", "October", "November", "December"
				};
		
		SpinnerListModel monthModel = new SpinnerListModel(monthStrings);
		
		newProjectDateSpinnerMonth = new JSpinner(monthModel);
		newProjectDateSpinnerMonth.setBounds(400, 180, 100, 25);
		newProjectPanel.add(newProjectDateSpinnerMonth);
	}
	
	public void newProjectComboBox() {													//JComboBox generation for New Project Tab
		
		String[] urgencyLevels = {"High", "Medium", "Low"};
		
		newProjectComboBox = new JComboBox<Object>(urgencyLevels);
		newProjectComboBox.setBounds(400, 220, 80, 25);
		newProjectPanel.add(newProjectComboBox);
		
	}
	
	public void newProjectButtons() {													//JButtons 'btnCreate' and 'btnViewAll' for New Project Tab
		btnCreate = new JButton("Create New Project");
		btnCreate.setBounds(325, 265, 150, 50);
		btnCreate.addActionListener(new ActionListener() {								//Implementation of ActionListener handling button click with anonymous inner class
			
			public void actionPerformed(ActionEvent event) {							//Anonymous inner class generates a new Project object using 
																						//values retrieved from JTextField, JSpinners, and JComboBox as parameters.
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
				
				File projectList = new File("projectlist.txt");							//These two functions need to be split somehow
																						//Second part of the event handler code creates a file object and writes
				try {																	//to it values retrieved from the newly instantiated Project object using PW, BW, and FW
					PrintWriter projectPrinter = new PrintWriter (new BufferedWriter (new FileWriter(projectList, true)));
					projectPrinter.print(newProject.getProjectName()+" "+newProject.getprojectStartDate()+" "+newProject.getProjectDueDate()+" "+newProject.getProjectPriority()+"\n");
					projectPrinter.close();
					} 
				
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				
				/*
				try {
					projectArraysAndStorage.newProjectManipulableList();
				} 
				catch (FileNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				
			}
		});
		newProjectPanel.add(btnCreate);
		
		btnViewAll = new JButton("View All Projects");
		btnViewAll.setBounds(325, 340, 150, 50);
		btnViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sortedTabbedPane.setSelectedIndex(1);
			}
		});
		newProjectPanel.add(btnViewAll);
		
	}
	
	public void allProjectsButtons() {
		btnViewSingleProject = new JButton("View");
		btnViewSingleProject.setBounds(350, 300, 150, 50);
		
		btnViewSingleProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) { 
				
				String retVal = allProjectsList.getSelectedValue();
				String retValToString = retVal.toString();
				String[] splitProjectValues = retValToString.split(" ");
				
				indProjectNameTextField.setText(splitProjectValues[0]);
				indProjectStartDateTextField.setText(splitProjectValues[1]);
				indProjectDueDateTextField.setText(splitProjectValues[2]);
				indProjectUrgencyTextField.setText(splitProjectValues[3]);
				/*
				try 
				{
					projectArraysAndStorage.retrieveValuesFromTextStrings();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				*/
			}
		});
		allProjectsPanel.add(btnViewSingleProject);
		
		btnMoveToNewProject = new JButton("New Project");
		btnMoveToNewProject.setBounds(200, 300, 150, 50);
		btnMoveToNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sortedTabbedPane.setSelectedIndex(0);
			}
		});
		allProjectsPanel.add(btnMoveToNewProject);
	}
	
	@SuppressWarnings("unchecked")
	public void allProjectsList() {
		/**
		 * 
		 */
		try {
			projectArraysAndStorage.newProjectManipulableList();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		allProjectsList = new JList<String>(projectArraysAndStorage.projectsStringArray);
		allProjectsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		allProjectsList.setLayoutOrientation(JList.VERTICAL);
		allProjectsList.setBounds(50, 100, 300, 100);
		allProjectsList.setVisibleRowCount(1000);
		
		
		projectListScroller = new JScrollPane(allProjectsList);
		projectListScroller.setBounds(50, 100, 340, 140);
		allProjectsPanel.add(projectListScroller);
	}
	
	public void allProjectsIndividualView() {
		allProjectsNameLbl = new JLabel("Name:");
		allProjectsNameLbl.setBounds(500, 100, 100, 20);
		allProjectsPanel.add(allProjectsNameLbl);
		
		allProjectsStartDateLbl = new JLabel("Started:");
		allProjectsStartDateLbl.setBounds(500, 130, 100, 20);
		allProjectsPanel.add(allProjectsStartDateLbl);
		
		allProjectsDueDateLbl = new JLabel("Due:");
		allProjectsDueDateLbl.setBounds(500, 160, 100, 20);
		allProjectsPanel.add(allProjectsDueDateLbl);
		
		allProjectsUrgencyLbl = new JLabel("Priority:");
		allProjectsUrgencyLbl.setBounds(500, 190, 100, 20);
		allProjectsPanel.add(allProjectsUrgencyLbl);
		
		indProjectNameTextField = new JTextField();
		indProjectNameTextField.setBounds(600, 100, 150, 20);
		allProjectsPanel.add(indProjectNameTextField);
		
		indProjectStartDateTextField = new JTextField();
		indProjectStartDateTextField.setBounds(600, 130, 150, 20);
		allProjectsPanel.add(indProjectStartDateTextField);
		
		indProjectDueDateTextField = new JTextField();
		indProjectDueDateTextField.setBounds(600, 160, 150, 20);
		allProjectsPanel.add(indProjectDueDateTextField);
		
		indProjectUrgencyTextField = new JTextField();
		indProjectUrgencyTextField.setBounds(600, 190, 150, 20);
		allProjectsPanel.add(indProjectUrgencyTextField);
	}

}

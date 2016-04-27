import java.awt.*;
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
	Dimension maxLabelAndTextFieldSize = new Dimension (150, 20);
	
	private JFrame newProjectFrame;
	private JTabbedPane sortedTabbedPane;
	private JPanel newProjectPanel, 
			newProjectSubPanel1, 
			newProjectSubPanel2, 
			newProjectSubPanel3, 
			newProjectSubPanel4,
			newProjectSubPanel5,
			newProjectSubPanel6,
			newProjectSubPanel7,
			allProjectsPanel,
			allProjectsSubPanel1,
			allProjectsSubPanel2,
			allProjectsSubPanel3,
			allProjectsSubPanel4,
			allProjectsSubPanel5,
			allProjectsSubPanel6;
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
		generateForm();
		newProjectLabels();
		newProjectTextField();
		newProjectButtons();
		newProjectDateSpinners();
		newProjectComboBox();
		createAllProjectsList();
		allProjectsButtons();
		allProjectsIndividualView();
		
		newProjectFrame.add(sortedTabbedPane);
		newProjectFrame.setVisible(true);
	}
	
	public void generateForm() {														//Frame and Panel Instantiation
		newProjectFrame = new JFrame();
		newProjectFrame.setTitle("Sorted! (New Project)");
		newProjectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newProjectFrame.setSize(640,360);
		newProjectFrame.setResizable(true);
		
		newProjectPanel = new JPanel();
		newProjectPanel.setLayout(new GridLayout(2,1,5,5));
		//newProjectPanel.setBackground(Color.DARK_GRAY);
		
		newProjectSubPanel1 = new JPanel();
		newProjectSubPanel1.setLayout(new GridLayout(1,2));
		newProjectPanel.add(newProjectSubPanel1);
		
		newProjectSubPanel2 = new JPanel();
		newProjectSubPanel2.setLayout(new BoxLayout(newProjectSubPanel2, BoxLayout.LINE_AXIS));
		newProjectPanel.add(newProjectSubPanel2);
		
		newProjectSubPanel3 = new JPanel();
		newProjectSubPanel3.setLayout(new BoxLayout(newProjectSubPanel3, BoxLayout.PAGE_AXIS));
		//newProjectSubPanel3.setBackground(Color.DARK_GRAY);
		newProjectSubPanel1.add(newProjectSubPanel3);
		
		newProjectSubPanel4 = new JPanel();
		newProjectSubPanel4.setLayout(new GridLayout(3,1,5,5));
		//newProjectSubPanel4.setBackground(Color.DARK_GRAY);
		newProjectSubPanel1.add(newProjectSubPanel4);
		
		newProjectSubPanel5 = new JPanel();
		newProjectSubPanel5.setLayout(new GridLayout(1,1));
		newProjectSubPanel4.add(newProjectSubPanel5);
		
		newProjectSubPanel6 = new JPanel();
		newProjectSubPanel6.setLayout(new GridLayout(1,3));
		newProjectSubPanel4.add(newProjectSubPanel6);
		
		newProjectSubPanel7 = new JPanel();
		newProjectSubPanel7.setLayout(new GridLayout(1,1));
		newProjectSubPanel4.add(newProjectSubPanel7);
		
		/*
		newProjectSubPanel8 = new JPanel();
		newProjectSubPanel8.setLayout(new GridLayout(1,1));
		newProjectSubPanel3.add(newProjectSubPanel8);
		
		newProjectSubPanel9 = new JPanel();
		newProjectSubPanel9.setLayout(new GridLayout(1,1));
		newProjectSubPanel3.add(newProjectSubPanel9);
		
		newProjectSubPanel10 = new JPanel();
		newProjectSubPanel10.setLayout(new GridLayout(1,1));
		newProjectSubPanel3.add(newProjectSubPanel10);
		*/
		
		allProjectsPanel = new JPanel();
		allProjectsPanel.setLayout(new GridLayout(2,1,5,5));
		
		allProjectsSubPanel1 = new JPanel();
		allProjectsSubPanel1.setLayout(new GridLayout(1,2,5,5));
		allProjectsPanel.add(allProjectsSubPanel1);
		
		allProjectsSubPanel2 = new JPanel();
		allProjectsSubPanel2.setLayout(new BoxLayout(allProjectsSubPanel2, BoxLayout.LINE_AXIS));
		allProjectsPanel.add(allProjectsSubPanel2);
		
		allProjectsSubPanel3 = new JPanel();
		allProjectsSubPanel3.setLayout(new GridLayout(1,1));
		allProjectsSubPanel1.add(allProjectsSubPanel3);
		
		allProjectsSubPanel4 = new JPanel();
		allProjectsSubPanel4.setLayout(new GridLayout(1,2));
		allProjectsSubPanel1.add(allProjectsSubPanel4);
		
		allProjectsSubPanel5 = new JPanel();
		allProjectsSubPanel5.setLayout(new BoxLayout(allProjectsSubPanel5, BoxLayout.PAGE_AXIS));
		allProjectsSubPanel4.add(allProjectsSubPanel5);
		
		allProjectsSubPanel6 = new JPanel();
		allProjectsSubPanel6.setLayout(new BoxLayout(allProjectsSubPanel6, BoxLayout.PAGE_AXIS));
		allProjectsSubPanel4.add(allProjectsSubPanel6);
		//calendarPanel = new JPanel();
		//calendarPanel.setLayout(null);
		
		sortedTabbedPane = new JTabbedPane();
		sortedTabbedPane.addTab("New Project", newProjectPanel);
		sortedTabbedPane.addTab("All Projects", allProjectsPanel);
		//sortedTabbedPane.addTab("Calendar", calendarPanel);
	}
	
	public void newProjectLabels() {													//JLabels for New Project Tab
		
		newProjectNameLbl = new JLabel("Name Your Project:");
		newProjectSubPanel3.add(Box.createVerticalGlue());
		newProjectNameLbl.setMaximumSize(maxLabelAndTextFieldSize);
		
		newProjectSubPanel3.add(newProjectNameLbl);
		
		newProjectSubPanel3.add(Box.createVerticalGlue());
		
		lblNewProjectDueDateLbl = new JLabel("When is it due?");
		lblNewProjectDueDateLbl.setMaximumSize(maxLabelAndTextFieldSize);
		newProjectSubPanel3.add(lblNewProjectDueDateLbl);
		newProjectSubPanel3.add(Box.createVerticalGlue());
		
		lblNewProjectUrgencyLbl = new JLabel("How urgent is it?");
		lblNewProjectUrgencyLbl.setMaximumSize(maxLabelAndTextFieldSize);
		newProjectSubPanel3.add(lblNewProjectUrgencyLbl);
		newProjectSubPanel3.add(Box.createVerticalGlue());
	}
	
	public void newProjectTextField() {													//JTextField for New Project Tab
		newProjectTextField = new JTextField();
		newProjectTextField.setBounds(400, 150, 150, 20);
		newProjectTextField.add(Box.createVerticalGlue());
		newProjectSubPanel5.add(newProjectTextField);
	}
	
	public void newProjectDateSpinners() {												//3 JSpinners used to allow user to specify Project due dates
		
		String[] yearStrings = {														//Year String instantiation
				"2016","2017","2018","2019","2020"
				}; 
		
		SpinnerListModel yearModel = new SpinnerListModel(yearStrings);
		
		newProjectDateSpinnerYear = new JSpinner(yearModel);
		newProjectSubPanel6.add(newProjectDateSpinnerYear);
		
																						//Day String instantiation
		String[] dayStrings = {									
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
				"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
				"24", "25", "26", "27", "28", "29", "30", "31"
				};
		
		SpinnerListModel dayModel = new SpinnerListModel(dayStrings);
		
		newProjectDateSpinnerDay = new JSpinner(dayModel);
		newProjectSubPanel6.add(newProjectDateSpinnerDay);
		
		String[] monthStrings = {														//Month String instantiation	
				"January", "February", "March", "April",
				"May", "June", "July", "August",
				"September", "October", "November", "December"
				};
		
		SpinnerListModel monthModel = new SpinnerListModel(monthStrings);
		
		newProjectDateSpinnerMonth = new JSpinner(monthModel);
		newProjectSubPanel6.add(newProjectDateSpinnerMonth);
	}
	
	public void newProjectComboBox() {													//JComboBox generation for New Project Tab
		
		String[] urgencyLevels = {"High", "Medium", "Low"};
		
		newProjectComboBox = new JComboBox<Object>(urgencyLevels);
		newProjectSubPanel7.add(newProjectComboBox);
		
	}
	
	public void newProjectButtons() {													//JButtons 'btnCreate' and 'btnViewAll' for New Project Tab
		btnCreate = new JButton("Create New Project");
		
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
				
				
				File projectNames = new File("projectnames.txt");							//These two functions need to be split somehow
				File projectStartDates = new File("projectstartdates.txt");
				File projectDueDates = new File("projectduedates.txt");
				File projectPriorityLevels = new File("projectprioritylevels.txt");							//Second part of the event handler code creates a file object and writes
				
				try {																		//to it values retrieved from the newly instantiated Project object using PW, BW, and FW
					PrintWriter projectNamePrinter = new PrintWriter (new BufferedWriter (new FileWriter(projectNames, true)));
					PrintWriter projectStartDatePrinter = new PrintWriter (new BufferedWriter (new FileWriter(projectStartDates, true)));
					PrintWriter projectDueDatePrinter = new PrintWriter (new BufferedWriter (new FileWriter(projectDueDates, true)));
					PrintWriter projectPriorityLevelPrinter = new PrintWriter (new BufferedWriter (new FileWriter(projectPriorityLevels, true)));
					
					projectNamePrinter.print(newProject.getProjectName()+"\n");									//Print Project Name to file
					projectStartDatePrinter.print(newProject.getprojectStartDate()+"\n");						//Print Project Start Date to file
					projectDueDatePrinter.print(newProject.getProjectDueDate()+"\n");							//Print Project Due Date to file
					projectPriorityLevelPrinter.print(newProject.getProjectPriority()+"\n");					//Print Project Priority to file
					
					projectNamePrinter.close();
					projectStartDatePrinter.close();
					projectDueDatePrinter.close();
					projectPriorityLevelPrinter.close();
					
					} 
				
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				allProjectsSubPanel3.remove(projectListScroller);
				createAllProjectsList();
			}
			
		});
		newProjectSubPanel2.add(Box.createHorizontalGlue());
		newProjectSubPanel2.add(btnCreate);
		
		btnViewAll = new JButton("View All Projects");
		
		btnViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sortedTabbedPane.setSelectedIndex(1);
			}
		});
		newProjectSubPanel2.add(Box.createHorizontalGlue());
		newProjectSubPanel2.add(btnViewAll);
		newProjectSubPanel2.add(Box.createHorizontalGlue());
		
	}
	
	public void allProjectsButtons() {													// Creates two buttons and associated Event Handlers
		btnViewSingleProject = new JButton("View");										
		btnViewSingleProject.setSize(150, 50);
		allProjectsSubPanel2.add(Box.createGlue());
		btnViewSingleProject.addActionListener(new ActionListener() {					
			public void actionPerformed(ActionEvent event) {							//Anonymous inner class implements ActionHandler
				
				int selectedIndex = allProjectsList.getSelectedIndex();
				indProjectNameTextField.setText(projectArraysAndStorage.projectNameArray[selectedIndex]);
				indProjectStartDateTextField.setText(projectArraysAndStorage.projectStartDatesArray[selectedIndex]);
				indProjectDueDateTextField.setText(projectArraysAndStorage.projectDueDatesArray[selectedIndex]);
				indProjectUrgencyTextField.setText(projectArraysAndStorage.projectPriorityArray[selectedIndex]);
			}
		});
		allProjectsSubPanel2.add(btnViewSingleProject);
		
		btnMoveToNewProject = new JButton("New Project");
		btnMoveToNewProject.setSize(150, 50);
		allProjectsSubPanel2.add(Box.createGlue());
		btnMoveToNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sortedTabbedPane.setSelectedIndex(0);
			}
		});
		allProjectsSubPanel2.add(btnMoveToNewProject);
		allProjectsSubPanel2.add(Box.createGlue());
	}
	
	@SuppressWarnings("unchecked")
	public void createAllProjectsList() {														//Method creates JList and JScrollPane Objects
	
		try {
			projectArraysAndStorage.newProjectManipulableList();						//Array from ProjectArraysAndStorage is populated
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		allProjectsList = new JList<String>(projectArraysAndStorage.getProjectNameArray());
		allProjectsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			//List is created from projectsStringArray
		allProjectsList.setLayoutOrientation(JList.VERTICAL);
		allProjectsList.setVisibleRowCount(1000);
		
		
		projectListScroller = new JScrollPane(allProjectsList);
		projectListScroller.setSize(400,250);
		allProjectsSubPanel3.add(projectListScroller);
	}
	
	public void allProjectsIndividualView() {											//Method creates JLabel and JTextField Objects placed in the allProjects pane
		allProjectsNameLbl = new JLabel("Name:");
		allProjectsNameLbl.setMaximumSize(maxLabelAndTextFieldSize);
		allProjectsSubPanel5.add(Box.createGlue());
		allProjectsSubPanel5.add(Box.createHorizontalGlue());
		allProjectsSubPanel5.add(allProjectsNameLbl);
		
		allProjectsStartDateLbl = new JLabel("Started:");
		allProjectsStartDateLbl.setMaximumSize(maxLabelAndTextFieldSize);
		allProjectsSubPanel5.add(Box.createGlue());
		allProjectsSubPanel5.add(Box.createHorizontalGlue());
		allProjectsSubPanel5.add(allProjectsStartDateLbl);
		
		allProjectsDueDateLbl = new JLabel("Due:");
		allProjectsDueDateLbl.setMaximumSize(maxLabelAndTextFieldSize);
		allProjectsSubPanel5.add(Box.createGlue());
		allProjectsSubPanel5.add(Box.createHorizontalGlue());
		allProjectsSubPanel5.add(allProjectsDueDateLbl);
		
		allProjectsUrgencyLbl = new JLabel("Priority:");
		allProjectsUrgencyLbl.setMaximumSize(maxLabelAndTextFieldSize);
		allProjectsSubPanel5.add(Box.createGlue());
		allProjectsSubPanel5.add(Box.createHorizontalGlue());
		allProjectsSubPanel5.add(allProjectsUrgencyLbl);
		allProjectsSubPanel5.add(Box.createGlue());
		
		indProjectNameTextField = new JTextField();
		indProjectNameTextField.setMaximumSize(maxLabelAndTextFieldSize);
		allProjectsSubPanel6.add(Box.createGlue());
		allProjectsSubPanel6.add(indProjectNameTextField);
		
		indProjectStartDateTextField = new JTextField();
		indProjectStartDateTextField.setMaximumSize(maxLabelAndTextFieldSize);
		allProjectsSubPanel6.add(Box.createGlue());
		allProjectsSubPanel6.add(indProjectStartDateTextField);
		
		indProjectDueDateTextField = new JTextField();
		indProjectDueDateTextField.setMaximumSize(maxLabelAndTextFieldSize);
		allProjectsSubPanel6.add(Box.createGlue());
		allProjectsSubPanel6.add(indProjectDueDateTextField);
		
		indProjectUrgencyTextField = new JTextField();
		indProjectUrgencyTextField.setMaximumSize(maxLabelAndTextFieldSize);
		allProjectsSubPanel6.add(Box.createGlue());
		allProjectsSubPanel6.add(indProjectUrgencyTextField);
		allProjectsSubPanel6.add(Box.createGlue());
	}

}

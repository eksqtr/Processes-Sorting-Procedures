/*
 *	
 *	Processes Sorting Procedures  (Arrival Time - CPU Burst - Priorty)
 *	Hard Coded by: Eubie Jay Hernandez Clemente - BSIT301E
 *	Submitted to: Joel Jimenez
 *	
 *	Insights: 
 *		 A process have the following properties
 *			1. ID
 *			2. arrival time
 *			3. CPU burst time
 *			4. priority
 *
 *		Write a program that will allow the user to enter 5 to 7 processes and choose from the following sorting procedures
 *			1. by arrival time (first come - first serve)
 *			2. by CPU burst time (shortest CBT first). If both process have same CBT, break the tie using arrival time.
 *			3. by priority (1= highest priority, 5 = lowest priority) If both process have same priority, break the tie using CBT  
 *				If both process have same priority and CBT, break the tie using arrival time.
*/
import java.util.*;

public class LabExercise2 {
	static Scanner userInput = new Scanner(System.in);
	// Declaring series object
	static Process[] series;

	static String dialogMsg = null;
	public static void main (String[] args) {
		int userChoices = 0, dump = 0;
		String confirmation = "";

		String mainMenu[] = { // Array of main Menu
			"Set Number of Process",
			"Enter Processes",
			"Sort by Arrival Time",
			"Sort by CPU Burst",
			"Sort by Priority",
			"Exit"
		};

	
		if(series == null)
		{
			while(true)
			{
				System.out.println("There were no Number of Processes has been set, please Enter the number of process (5 - 7):");
				System.out.print(": ");
				try {
					
					while(true)
					{
						int temp = userInput.nextInt();	
						if(IsValidNumberProcessInput(temp))
						{
							series = new Process[temp]; // Setting number of process that user input to Series(Process)
							for (int i = 0; i < series.length; i++) {
								series[i] = new Process(); // Setting to its default / clear the data of the process (series)
							}
							break;
						}
						else {
							System.out.println("ERROR: Number of Processes should be input from 5 - 7!");
							System.out.print(": ");
							userInput = new Scanner (System.in);
						}
					}
				
					break;
				} 
				catch(InputMismatchException e) {
					System.out.println("ERROR: Invalid input must be integer!");
					userInput = new Scanner (System.in);
				}
			}
		}
		while(userChoices != 6)
		{
			
			System.out.println("\n\n\n[Main Menu]");
			for(int x = 0; x < mainMenu.length; x++ )
			{
				System.out.println((x+1) + ": "+ mainMenu[x]);
			}
			System.out.println("\nNumber of Processes: " + series.length);

			if(dialogMsg != null){
				System.out.print(dialogMsg);
				dialogMsg = null;
			}

			while(true)
			{
				System.out.print("\n\nChoose from the menu ( 1 - " + mainMenu.length + " ): ");
				try {
					userChoices = userInput.nextInt();
					break;
				}
				catch (InputMismatchException e)
				{
					System.out.println("ERROR: Invalid input must be integer!");
					userInput = new Scanner (System.in);
				}
			}
			
			switch(userChoices)
			{
				case 1: // Set Number of Process
					
					System.out.println("\nYou're setting the number of the process this means that processes that you input will be wipe.");
					System.out.println("\tAre you sure do you want to continue? Type 'Confirm' if yes then 'exit' if not");
					confirmation = userInput.nextLine();
					while(true)
					{
						System.out.print(": ");
						confirmation = userInput.nextLine();
						if(confirmation.equalsIgnoreCase("confirm")) // check if the user proceed to set Number of Processes
						{
							while(true)
							{
								System.out.print("Enter number of Process (5 - 7): ");
								try {
					
									while(true)
									{
										int temp = userInput.nextInt();	
										if(IsValidNumberProcessInput(temp))
										{
											series = new Process[temp]; // Setting number of process that user input to Series(Process)
											for (int i = 0; i < series.length; i++) {
												series[i] = new Process(); // Setting to its default / clear the data of the process (series)
											}
											break;
										}
										else {
											System.out.println("ERROR: Number of Processes should be input from 5 - 7!");
											System.out.print("Enter number of Process: ");
											userInput = new Scanner (System.in);
										}
									}
								
									break;
								} 
								catch(InputMismatchException e) {
									System.out.println("ERROR: Invalid input must be integer!");
									userInput = new Scanner (System.in);
								}
							}
							
							break;
						}
						else if(confirmation.equalsIgnoreCase("exit")) break;	
					}
					
					break;

				case 2: // Enter Processes
					setProcess(series.length); // We're going to set Process base on Series Arr Length.

					break;
				case 3: // Sort by Arrival Time
					// Algorithm for Sorting by Arrival Time
					dump = 0;
					if(countProcessAdded() == 0)
					{
						error("There were no Process found! Please enter the details of the processes first! (2 - Enter Processes)");
						break;
					}
					confirmation = userInput.nextLine();
					while(true)
					{
						for (int x = 0; x != series.length; x++) {

							for (int y = 1; y != series.length; y++) { 

								if (series[y-1].getProcessArrival() > series[y].getProcessArrival()) {
									
									dump = series[y - 1].getProcessId();
									series[y - 1].setProcessId(series[y].getProcessId());
									series[y].setProcessId(dump);

									dump = series[y - 1].getProcessArrival();
									series[y - 1].setProcessArrival(series[y].getProcessArrival());
									series[y].setProcessArrival(dump);

									dump = series[y - 1].getProcessCpuBurst();
									series[y - 1].setProcessCpuBurst(series[y].getProcessCpuBurst());
									series[y].setProcessCpuBurst(dump);

									dump = series[y - 1].getProcessPriority();
									series[y - 1].setProcessPriority(series[y].getProcessPriority());
									series[y].setProcessPriority(dump);
								}
							}
						}
						// End of sorting

						//Printing the result sorted by Arrival Time
						System.out.println("\n===================================");
						System.out.println("Sorted by Arival Time");
						System.out.println("-----------------------------------------------------------------");
						System.out.println("|\t#ID\t|\tAT\t|\tCBT\t|\tPT\t|\t");	
						System.out.println("-----------------------------------------------------------------");
						for (int i = 0; i != series.length; i++) {
							System.out.println("|\t#" + series[i].getProcessId() +"\t|\t"+ series[i].getProcessArrival() +"\t|\t"+ series[i].getProcessCpuBurst() +"\t|\t"+ series[i].getProcessPriority() + "\t|\t"); 
						}
						System.out.println("-----------------------------------------------------------------");

						// Ask user if the user wanted to go back to the menu by typing exit
						System.out.println("Type 'exit' to go back to the menu.");
						System.out.print(": ");
						confirmation = userInput.nextLine();
						if(confirmation.equalsIgnoreCase("exit")) break; // if user typed 'exit' it will go back to the menu
					}
					break;
				case 4: // Sort by Cpu Burst
					// Algorithm for Sorting by CPU Burst
					dump = 0;
					if(countProcessAdded() == 0)
					{
						error("There were no Process found! Please enter the details of the processes first! (2 - Enter Processes)");
						break;
					}
					confirmation = userInput.nextLine();
					while(true)
					{
						for (int x = 0; x != series.length; x++) {

							for (int y = 1; y != series.length; y++) { 

								if (series[y-1].getProcessCpuBurst() >= series[y].getProcessCpuBurst()) // Check if Process Burst is >= to (process Burst - 1)
								{
									if (series[y-1].getProcessCpuBurst() == series[y].getProcessCpuBurst()) // Checks whether process burst tie with another process burst 
									{
										if(series[y-1].getProcessArrival() > series[y].getProcessArrival()) // then we're going to sort it by Arrival Time
										{
											dump = series[y - 1].getProcessId();
											series[y - 1].setProcessId(series[y].getProcessId());
											series[y].setProcessId(dump);

											dump = series[y - 1].getProcessArrival();
											series[y - 1].setProcessArrival(series[y].getProcessArrival());
											series[y].setProcessArrival(dump);

											dump = series[y - 1].getProcessCpuBurst();
											series[y - 1].setProcessCpuBurst(series[y].getProcessCpuBurst());
											series[y].setProcessCpuBurst(dump);

											dump = series[y - 1].getProcessPriority();
											series[y - 1].setProcessPriority(series[y].getProcessPriority());
											series[y].setProcessPriority(dump);
										}
									}
									else { // If the condition of the first if didn't met then this will sort by Process CPU Burst
										dump = series[y - 1].getProcessId();
										series[y - 1].setProcessId(series[y].getProcessId());
										series[y].setProcessId(dump);

										dump = series[y - 1].getProcessArrival();
										series[y - 1].setProcessArrival(series[y].getProcessArrival());
										series[y].setProcessArrival(dump);

										dump = series[y - 1].getProcessCpuBurst();
										series[y - 1].setProcessCpuBurst(series[y].getProcessCpuBurst());
										series[y].setProcessCpuBurst(dump);

										dump = series[y - 1].getProcessPriority();
										series[y - 1].setProcessPriority(series[y].getProcessPriority());
										series[y].setProcessPriority(dump);
									}
								}
							}
						}

						// End of sorting

						//Printing the result sorted by CPU Burst
						System.out.println("\n===================================");
						System.out.println("Sorted by CPU Burst");
						System.out.println("-----------------------------------------------------------------");
						System.out.println("|\t#ID\t|\tAT\t|\tCBT\t|\tPT\t|\t");	
						System.out.println("-----------------------------------------------------------------");
						for (int i = 0; i != series.length; i++) {
							System.out.println("|\t#" + series[i].getProcessId() +"\t|\t"+ series[i].getProcessArrival() +"\t|\t"+ series[i].getProcessCpuBurst() +"\t|\t"+ series[i].getProcessPriority() + "\t|\t"); 
						}
						System.out.println("-----------------------------------------------------------------");

						// Ask user if the user wanted to go back to the menu by typing exit
						System.out.println("Type 'exit' to go back to the menu.");
						System.out.print(": ");
						confirmation = userInput.nextLine();
						if(confirmation.equalsIgnoreCase("exit")) break; // if user typed 'exit' it will go back to the menu

					}
					break;

				case 5: // Sort by Priorty
					// Algorithm for Sorting by Priority
					dump = 0;
					if(countProcessAdded() == 0)
					{
						error("There were no Process found! Please enter the details of the processes first! (2 - Enter Processes)");
						break;
					}
					confirmation = userInput.nextLine();
					while(true)
					{
						for (int x = 0; x != series.length; x++) {

							for (int y = 1; y != series.length; y++) { 

								if (series[y-1].getProcessPriority() >= series[y].getProcessPriority()) // Check if Priorty is >= to (process Burst - 1)
								{
									if (series[y-1].getProcessPriority() == series[y].getProcessPriority()) // Checks whether process burst tie with another process burst 
									{
										if(series[y-1].getProcessCpuBurst() >= series[y].getProcessCpuBurst()) // If true check if CPU burst >= to (CPU burst - 1)
										{
											if (series[y-1].getProcessCpuBurst() == series[y].getProcessCpuBurst()) // Checks whether process burst tie with another process burst 
											{
												if(series[y-1].getProcessArrival() > series[y].getProcessArrival()) // then we're going to sort it by Arrival Time
												{
													// Sorting by Arival Time
													dump = series[y - 1].getProcessId();
													series[y - 1].setProcessId(series[y].getProcessId());
													series[y].setProcessId(dump);

													dump = series[y - 1].getProcessArrival();
													series[y - 1].setProcessArrival(series[y].getProcessArrival());
													series[y].setProcessArrival(dump);

													dump = series[y - 1].getProcessCpuBurst();
													series[y - 1].setProcessCpuBurst(series[y].getProcessCpuBurst());
													series[y].setProcessCpuBurst(dump);

													dump = series[y - 1].getProcessPriority();
													series[y - 1].setProcessPriority(series[y].getProcessPriority());
													series[y].setProcessPriority(dump);
												}
											}
											else { // If CPU Burst is not with tie with another burst sort it.
												dump = series[y - 1].getProcessId();
												series[y - 1].setProcessId(series[y].getProcessId());
												series[y].setProcessId(dump);

												dump = series[y - 1].getProcessArrival();
												series[y - 1].setProcessArrival(series[y].getProcessArrival());
												series[y].setProcessArrival(dump);

												dump = series[y - 1].getProcessCpuBurst();
												series[y - 1].setProcessCpuBurst(series[y].getProcessCpuBurst());
												series[y].setProcessCpuBurst(dump);

												dump = series[y - 1].getProcessPriority();
												series[y - 1].setProcessPriority(series[y].getProcessPriority());
												series[y].setProcessPriority(dump);
											}
										}
									}
									else { // If Process Priorty is not with tie with another priority sort it.
										dump = series[y - 1].getProcessId();
										series[y - 1].setProcessId(series[y].getProcessId());
										series[y].setProcessId(dump);

										dump = series[y - 1].getProcessArrival();
										series[y - 1].setProcessArrival(series[y].getProcessArrival());
										series[y].setProcessArrival(dump);

										dump = series[y - 1].getProcessCpuBurst();
										series[y - 1].setProcessCpuBurst(series[y].getProcessCpuBurst());
										series[y].setProcessCpuBurst(dump);

										dump = series[y - 1].getProcessPriority();
										series[y - 1].setProcessPriority(series[y].getProcessPriority());
										series[y].setProcessPriority(dump);
									}
								}
							}
						}

						// End of sorting

						//Printing the result sorted by Arrival Time
						System.out.println("\n===================================");
						System.out.println("Sorted by Priorty");
						System.out.println("-----------------------------------------------------------------");
						System.out.println("|\t#ID\t|\tAT\t|\tCBT\t|\tPT\t|\t");	
						System.out.println("-----------------------------------------------------------------");
						for (int i = 0; i != series.length; i++) {
							System.out.println("|\t#" + series[i].getProcessId() +"\t|\t"+ series[i].getProcessArrival() +"\t|\t"+ series[i].getProcessCpuBurst() +"\t|\t"+ series[i].getProcessPriority() + "\t|\t"); 
						}
						System.out.println("-----------------------------------------------------------------");

						// Ask user if the user wanted to go back to the menu by typing exit
						System.out.println("Type 'exit' to go back to the menu.");
						System.out.print(": ");
						confirmation = userInput.nextLine();
						if(confirmation.equalsIgnoreCase("exit")) break; // if user typed 'exit' it will go back to the menu
					}
					break;
				
			}
			
		}
	}
	static void error(String error)
	{
		dialogMsg = "\n==================================================================================";
		dialogMsg += "\nAN ERROR OCCURED: " + error;
		dialogMsg += "\n==================================================================================";
	}
	
	static void message(String msg)
	{
		dialogMsg = "\n==================================================================================";
		dialogMsg += "\nEXECUTION SUCCESS: " + msg;
		dialogMsg += "\n==================================================================================";
	}
	static boolean IsValudPriorityValue(int value)
	{
		return (value < 1 || value > 5) ? false : true;
	}
	static boolean IsProcessIdTaken(int id) {
		for(int i = 0; i != series.length; i++)
		{
	
			if(series[i].getProcessId() == id || id < 1)
			{
				return true;
			}
		}
		return false;
	}
	static boolean IsValidNumberProcessInput(int num)	
	{	
		return (num < 5 || num > 7) ? false : true; // checks if it is Valid Input Process of by the user
	}
	static boolean IsValidNumberInput(int num)
	{
		return (num < 1) ? false : true; // checks if it is Valid number input  by the user
	}
	static int countProcessAdded()
	{
		int count = 0;
		for(int i = 0; i != series.length; i++)
		{
			if(series[i].getProcessId() != 0)
			{
				count++;
			}
		}
		return count;
	}
	static boolean IsArrivalIdTaken(int id)
	{
		for(int i = 0; i != series.length; i++)
		{
			if(series[i].getProcessArrival() == id  || id < 1)
			{
				return true;
			}
		}
		return false;
	}
	static void setProcess(int length)
	{
		for (int i = 0; i != length; i++) {
			System.out.println("===============================================");
			System.out.println("Adding new process to Process ID " + i);

			while(true)
			{
				System.out.print("Enter ID: ");
				try {
					int temp = userInput.nextInt(), error = 0;
					while(true)
					{
						if(error == 2) temp = userInput.nextInt();
						if(IsValidNumberInput(temp))
						{
							
							while(true)
							{
								if(error == 1) temp = userInput.nextInt();
								if(!IsProcessIdTaken(temp)) // Check if the Process ID inputted by the user is available
								{
									series[i].setProcessId(temp);
									break;
								}
								else {
									System.out.println("ERROR: Process ID has been taken or a negative value! Please input another ID");
									System.out.print("Enter ID: ");
									error = 1;
								}
							}
							
							break;
						}	
						else {
							System.out.println("ERROR: Invalid input must not 0 or negative value");
							System.out.print("Enter ID: ");
							error = 2;
							
						}
					}
					break;
				}
				catch(InputMismatchException e)
				{
					System.out.println("ERROR: Invalid input must be integer!");
					userInput = new Scanner (System.in);
				}
			}
			while(true)
			{
				System.out.print("Enter Arrival: ");
				try {
				
					int temp =  userInput.nextInt(), error = 0;
					while(true)
					{
						if(error == 2)  temp =  userInput.nextInt();
						if(IsValidNumberInput(temp))
						{
							while(true)
							{
								if(error == 1) temp =  userInput.nextInt();
								if(!IsArrivalIdTaken(temp))
								{
									series[i].setProcessArrival(temp);
									break;
								}
								else {
									System.out.println("ERROR: That Arrival ID is already taken or a negative value, please input different Arrival ID");
									System.out.print("Enter Arrival: ");
									error = 1;
								}
							}
							break;
						}
						else {
							System.out.println("ERROR: Input must not be below 0 or negative value!");
							System.out.print("Enter Arrival: ");
							error = 2;
						}
					}
					
					break;
				}
				catch(InputMismatchException e)
				{
					System.out.println("ERROR: Invalid input must be integer!");
					userInput = new Scanner (System.in);
				}
			}
			while(true)
			{
				System.out.print("Enter CPU Burst: ");
				try {
					while(true)
					{
						int temp = userInput.nextInt();
						if(IsValidNumberInput(temp))
						{
							series[i].setProcessCpuBurst(temp);
							break;	
						}
						else {
							System.out.println("ERROR: Input value must not be 0 or negative value!");	
							System.out.print("Enter CPU Burst: ");
						}
					}
					break;
					
				}
				catch(InputMismatchException e)
				{
					System.out.println("ERROR: Invalid input must be integer!");
					userInput = new Scanner (System.in);
				}
			}
			while(true)
			{
				System.out.print("Enter Priority (1 Highest Priority - 5 Lowest Priority): ");
				try {
					int temp = userInput.nextInt(), error = 0;
					while(true)
					{
						if(error == 1) temp = userInput.nextInt();
						if(IsValudPriorityValue(temp))
						{
							series[i].setProcessPriority(temp);
							break;
						}
						else {
							System.out.println("ERROR: Invalid Priority input must be (1 Highest Priority - 5 Lowest Priority)!");
							System.out.print("Enter Priority (1 Highest Priority - 5 Lowest Priority): ");
							error = 1;
						}
					}
					break;
				}
				catch(InputMismatchException e)
				{
					System.out.println("ERROR: Invalid input must be integer!");
					userInput = new Scanner (System.in);
				}
			}
			
		}
		message("Processes details has been updated!");
	}
}

class Process // Method
{
	private int 
		IdProcess, 
		arrivalProcess, 
		cpuBurstProcess,
		priorityProcess
	;
	
	Process() // Default Constructor
	{
		IdProcess = 0;
		arrivalProcess = 0;
		cpuBurstProcess = 0;
		priorityProcess = 0;
	}
	public Process(int id, int arrival, int cpuburst, int priorty) // Constructor
	{
		this.IdProcess = id;
		this.arrivalProcess = arrival;
		this.cpuBurstProcess = cpuburst;
		this.priorityProcess = priorty;
	}
	
	/*================ Setter ==================*/
	public void setProcessId(int value)
	{
		 this.IdProcess = value;
	}
	public void setProcessArrival(int value)
	{
		 this.arrivalProcess = value;
	}
	public void setProcessCpuBurst(int value)
	{
		 this.cpuBurstProcess = value;
	}
	public void setProcessPriority(int value)
	{
	 	this.priorityProcess = value;
	}
	/*================ Getter ==================*/
	public  int getProcessId()
	{
		return this.IdProcess;
	}
	public int getProcessArrival()
	{
		return this.arrivalProcess;
	}
	public int getProcessCpuBurst()
	{
		return this.cpuBurstProcess;
	}
	public int getProcessPriority()
	{
		return this.priorityProcess;
	}
	
}
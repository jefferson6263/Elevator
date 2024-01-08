package com.fdmgroup.ElevatorProject;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Runner {
	private final static String configFilePath = "../ElevatorProject/src/main/java/com/fdmgroup/ElevatorProject/Configurations.txt";
	
    public static void main(String[] args) {
    	;
    	int numElevators, maxFloor, minFloor = 0;
    	
    
  
		Configurations configs = ReadConfiguration.getConfiguration(configFilePath);
			
		numElevators = configs.getNumOfElevators();
		maxFloor = configs.getMaxFloor();
		minFloor = configs.getMinFloor();
		
		System.out.println(numElevators);
			
		
    	
        // Create elevators
    	System.out.println("Creating elevators...");
        Elevator elevator1 = new Elevator();
        Elevator elevator2 = new Elevator();
        Elevator elevator3 = new Elevator();

        // Add elevators to a list
        ArrayList<Elevator> elevators = new ArrayList<>();
        elevators.add(elevator1);
        elevators.add(elevator2);
        elevators.add(elevator3);

        // Create a Scheduler and assign the elevators to it
        Scheduler scheduler = new Scheduler(elevators);

        // Create a Controller and assign the Scheduler to it
        Controller controller = new Controller(scheduler);

        // Start elevator threads
        System.out.println("Starting elevators...");
        scheduler.RunElevators();

        // Simulate some elevator requests
        System.out.println("Simulating elevator requests...");
        
        controller.addPersonToQueue(new Person(1, 4));
        System.out.println("Person 1 to 4 created");
        
        controller.addPersonToQueue(new Person(3, 7));
        System.out.println("Person 3 to 7 created");
        
        controller.addPersonToQueue(new Person(6, 2));
        System.out.println("Person 6 to 2 created");

        // Assign elevators to the people in the queue
        try {
        	System.out.println("Assigning elevators to queued requests...");
			controller.assignElevator();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        // TODO we want to know if all the elevators were utilized for all three people because theoretically, it should use all elevators.


    }
}

package business;

import utils.BoundedPriorityQueueSet;
import utils.DuplicateElementException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice = 0;
//        boolean running = true;

//        Random boolean to decide what version of method to use(risky vs safe).
        boolean useSafeMethod = new Random().nextBoolean();
        BoundedPriorityQueueSet list = new BoundedPriorityQueueSet();


//        Menu Loop
        while (true){
//            Print the menu
            printMenu();
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number between 0 and 5!");
                sc.nextLine();
                continue;
            }

//            Logic of choices
            switch (choice){

//                Add a task to the queue
                case 1:
//                    Prompt the user details for Task and create it
                    Task task = createTask();

                    if (useSafeMethod){
                        list.offer(task);
                    }
                    else {
                        try {
                            list.add(task);
                        } catch (DuplicateElementException | IllegalStateException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

//                    View the next task
                case 2:
                    if (useSafeMethod){
                        Task peeked = list.peek();
                        if (peeked == null){
                            System.out.println("The list is empty!");
                        } else {
                            System.out.println("Next task is: " +peeked);
                        }
                    }
                    else {
                        try {
                            System.out.println("Next task is: " + list.element());
                        } catch (NoSuchElementException e) {
                            System.out.println("No more tasks in the list!");
                        }
                    }
                    break;

//                    Mark the next task as done (remove)
                case 3:
                    if (useSafeMethod){
                        Task retrieved = list.poll();
                        if (retrieved == null){
                            System.out.println("The list is empty");
                        } else {
                            System.out.println("The task: " + retrieved + " was successfully deleted");
//                            If user has done the last task output congrats message
                            if (list.isEmpty()){
                                System.out.println("You have done all the tasks! Congratulations!");
                            }
                        }
                    }
                    else {
                        try {
                            Task retrieved = list.remove();
                            System.out.println("The task: " + retrieved + " was successfully deleted");
                            if (list.isEmpty()){
                                System.out.println("You have done all the tasks!");
                            }
                        } catch (NoSuchElementException e) {
                            System.out.println("The list is empty");

                        }
                    }
                    break;

//                    View the number of Tasks remaining
                case 4:
                        System.out.println("Numbers of task in the list: " + list.size());
                    break;
//                    View the space remaining in the queue
                case 5:
                        System.out.println("Space remaining in the list: " + (list.maxSize - list.size()) );
                    break;
//                    Exit
                case 0:
                    return;
            }
        }
    }


    /**
     * Helper method to print out the menu.
     */
    public static void printMenu() {
        System.out.println("Add a task to the queue - 1");
        System.out.println("View the next task - 2");
        System.out.println("Mark the next task as done (remove) - 3");
//            If the queue has been emptied by this action, the user should see a congratulations message!
        System.out.println("View the number of Tasks remaining - 4");
        System.out.println("View the space remaining in the queue - 5");
        System.out.println("Exit - 0");
        System.out.println();
    }


    /**
     * Prints the prompts for new Task, creates and returns this Task.
     * @return created Task.
     */
    public static Task createTask(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the name of the owner of the task: ");
        String owner = sc.nextLine();

        System.out.println("Enter the description of the task: ");
        String description = sc.nextLine();

        LocalDate dateOfTask = null;

        boolean dateValid = false;
        while (!dateValid){
            try {
                System.out.println("Enter the date of the task (yyyy-mm-dd): ");
                String date = sc.nextLine();

                dateOfTask = LocalDate.parse(date);
                dateValid = true;

            } catch (DateTimeParseException | IllegalArgumentException e) {
                sc.nextLine();
                System.out.println("Please enter a valid year/month/day. Date has to be later than today's date.");
            }
        }

        return new Task(owner, description, dateOfTask);



    }

}

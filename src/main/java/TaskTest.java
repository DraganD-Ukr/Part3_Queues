import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TaskTest {

    public static void main(String[] args) {

        LocalDate date1 = LocalDate.of(2024, 4, 14);
        LocalDate date2 = LocalDate.of(2024, 4, 13);
        LocalDate date3 = LocalDate.of(2024, 4, 20);
        LocalDate date4 = LocalDate.of(2024, 4, 28);
        LocalDate date5 = LocalDate.of(2024, 4, 16);


        Task task1 = new Task("Dmytro", "Do the dsa today", date1);
        Task task2 = new Task("Jan", "Give the link for github", date2);
        Task task3 = new Task("Abdi", "Finish presentation", date3);
        Task task4 = new Task("Sophie", "Finish the website", date4);
        Task task5 = new Task("Jo", "Play some games", date5);

        ArrayList<Task> tasksList = new ArrayList<>();
        tasksList.add(task1);
        tasksList.add(task2);
        tasksList.add(task3);
        tasksList.add(task4);
        tasksList.add(task5);

        System.out.println(tasksList);

        Collections.sort(tasksList);

        System.out.println(tasksList);
    }

}

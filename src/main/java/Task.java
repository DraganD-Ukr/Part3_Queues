import java.time.LocalDate;
import java.util.Objects;

public class Task implements Comparable<Task>{

    private String owner;
    private String description;

    private LocalDate deadline;


    public Task(String owner, String description, LocalDate deadline) {
        this.owner = owner;
        this.description = description;
        if (validateDeadline(deadline)){
            this.deadline = deadline;
        }
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        if (validateDeadline(deadline)){
            this.deadline = deadline;
        }
    }

    private boolean validateDeadline(LocalDate deadline){
        if (deadline.compareTo(LocalDate.now()) <= 0){
            throw new IllegalArgumentException("Deadline can not be equal or less less than today's date");
        } else {
            return true;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(owner, task.owner) && Objects.equals(description, task.description) && Objects.equals(deadline, task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, description, deadline);
    }

    @Override
    public String toString() {
        return "Task{" +
                "owner='" + owner + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                '}';
    }


    @Override
    public int compareTo(Task o) {
        if (this.deadline.isEqual(o.getDeadline())){
            return 0;
        } else if (this.deadline.isAfter(o.getDeadline())) {
            return 1;
        } else {
            return -1;
        }
    }

}

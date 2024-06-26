package business;

import java.time.LocalDate;
import java.util.Objects;

public class Task implements Comparable<Task>{

    /**
     * Owner of the task.
     */
    private String owner;

    /**
     * Description of the task.
     */
    private String description;

    /**
     * Deadline of the task.
     */
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

    /**
     * Helper method to check if deadline is valid (Date can not be equal or less than today's date).
     *
     * @param deadline deadline to validate.
     * @return boolean indicating validation.
     */
    private boolean validateDeadline(LocalDate deadline){
        if (!deadline.isAfter(LocalDate.now())){
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
        return this.compareTo(o.getDeadline);
    }

}

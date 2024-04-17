package utils;

import business.Task;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BoundedPriorityQueueSet {

    /**
     * Maximum size of the queue.
     */
    public final int maxSize;

    /**
     * Queue, implemented using composition (Java's LinkedList).
     */
    private final LinkedList<Task> queue;

    public BoundedPriorityQueueSet(int maxSize){
        this.maxSize = maxSize;
        queue = new LinkedList<>();
    }

    public BoundedPriorityQueueSet(){
        this.maxSize = 10;
        queue = new LinkedList<>();
    }

    /**
     * Gets the size of the queue.
     * @return the size of the queue.
     */
    public int size(){
        return queue.size();
    }

    /**
     * Checks if the queue is empty (size = 0).
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty(){
        return queue.isEmpty();
    }

    /**
     * Checks if the queue is full (Equals or more than specified size).
     * @return true if the queue is full, false otherwise.
     */
    public boolean isFull(){
        return queue.size() >= maxSize;
    }

    /**
     * Calculates position of the task to be inserted into the queue based on priority(deadline).
     * If there is a same task in a queue throws DuplicateElementException.
     * @param task Task to be inserted.
     * @return index of task to be inserted.
     */
    private int calcPosition(Task task){

        int pos = 0;

        for (Task t : queue) {
            if (t.equals(task)) {
                throw new DuplicateElementException();
            }
            if (task.compareTo(t) < 0) {
                break;
            }
            pos++;
        }
        return pos;
    }

    /**
     * Adds a task to the queue, if it's not full and does not contain duplicates.
     * @param task task to be added to the queue.
     */
    public void add(Task task){
        if (isFull()){
            throw new IllegalStateException("Queue is full");
        }
        queue.add(calcPosition(task), task);
    }

    /**
     * Safe version of add(), returns boolean indicating success of an action.
     * @param task task to be added.
     * @return false if there are duplicates or the queue is full, true otherwise.
     */
    public boolean offer(Task task){

        if (isFull()){
            return false;
        }

        int pos = 0;

        for (Task t : queue) {
            if (task.compareTo(t) >= 0) {
                break;
            }
            pos++;
        }
        queue.add(pos, task);
        return true;
    }

    /**
     * "Get" method: returns first element in a queue without deleting it.
     * @return first Task object in a queue, if it's not empty.
     */
    public Task element(){
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return queue.getFirst();
    }

    /**
     * Gets a first Task in a queue without deleting it.
     * @return first Task object in a queue, if it's not empty.
     */
    public Task peek(){
        if (isEmpty()) {
           return null;
        }
        return queue.getFirst();
    }

    /**
     * Removes and returns first element in a queue.
     * @return first Task in a queue.
     */
    public Task remove(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return queue.removeFirst();
    }

    /**
     * Safe version of remove(): Removes and returns first element in a queue OR null if it's empty.
     * @return first Task in a queue.
     */
    public Task poll(){
        if (isEmpty()){
            return null;
        }
        return queue.removeFirst();
    }



}

package utils;

public class DuplicateElementException extends IllegalArgumentException {

    DuplicateElementException(){
        super("A second copy of the same element is attempted to be added to a structure");
    }

    DuplicateElementException(String message){
        super(message);
    }

}

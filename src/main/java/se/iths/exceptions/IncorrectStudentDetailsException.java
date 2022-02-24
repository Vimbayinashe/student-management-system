package se.iths.exceptions;

public class IncorrectStudentDetailsException extends RuntimeException {

    private String customMessage;

    public IncorrectStudentDetailsException(){
    }

    public IncorrectStudentDetailsException(String message){
        this.customMessage = message;
    }

    public String getCustomMessage() {
        return customMessage;
    }
}

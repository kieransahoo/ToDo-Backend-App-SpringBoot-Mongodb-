package io.github.kieransahoo.springbootmongodb.Exception;

public class ToDoException extends Exception{

    private static final long serialVersionUID = 1L;

    public ToDoException(String message) {
        super(message);
    }

    public static String NotFoundException(String id){
        return "Todo with "+id+" Not Found!";
    }

    public static String TodoAlreadyExists(){
        return "Todo with given name already exists";
    }
}

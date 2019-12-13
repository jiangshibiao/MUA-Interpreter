package src.mua;

public class MyError extends Exception{
    public enum ErrorType {TypeError, NameError, ConvertError, SyntaxError, ValueError, RuntimeError, UnexpectedError};
    private ErrorType curErrorType;
    private String ErrorMessage = null;
    public MyError(ErrorType curErrorType, String ErrorMessage) {
        this.curErrorType = curErrorType;
        this.ErrorMessage = ErrorMessage;
    }
    public void addPrefixFunction(String name){
        ErrorMessage = name + "(): " + ErrorMessage;
    }
    @Override
    public String toString(){
        return curErrorType + "!\t" + ErrorMessage;
    }
}

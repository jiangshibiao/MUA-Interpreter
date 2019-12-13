package src.mua;
import src.mua.Operation.Operation;
import src.mua.OperationRun;
import src.mua.MyError;
import src.mua.NameSpace;
import java.util.Stack;
import java.util.Scanner;

public class Parse {
    private NameSpace space;
    private Stack<Operation> opt = new Stack<>();
    public Data restValue = null;       //The final value of an expression

    public Parse(NameSpace space){
        this.space = space;
    }
    public Operation peek(){return opt.isEmpty() ? null : opt.peek();}

    public void push(Data in) throws MyError{
        /*
            Return False: Normal execute;
            Return True: The function is stopped somewhere.
        */
        if (in == null) return;
        //System.out.println("Push: " + in);
        if (in.type == Data.Type.OP){
            restValue = null;
            if (space.operationRun.isOperationName(in.getOp())) {
                //It is the function defined by system.
                try {
                    Operation nowOperation = space.operationRun.newOperation(in.getOp());
                    opt.push(nowOperation);
                }
                catch (MyError e){
                    e.addPrefixFunction(in.getOp());
                    throw e;
                    //The error message will be located through functions.
                }
            }
            else if (space.wholeSpaceContainsName(in.getOp())){
                //It is the function defined by user.
                try {
                    Function nowFunction = new Function(in.getOp(), space);
                    opt.push(nowFunction);
                }
                catch (MyError e){
                    e.addPrefixFunction(in.getOp());
                    throw e;
                }
            }
            else
                throw new MyError(MyError.ErrorType.NameError,
                        "Can not find the function [" + in.getOp() + "].");
        }
        else{
            //Data

            if (opt.isEmpty()) {
                /*if (in.type == Data.Type.LIST){
                    //If there is a list in stack alone, it will be executed.
                    for (Data each: in.getList())
                        push(each);
                }*/
                if (restValue != null)
                    throw new MyError(MyError.ErrorType.RuntimeError,
                            "Put two pieces of data but no operation in stack.");
                restValue = in;
            }
            else {
                if (restValue != null)
                    throw new MyError(MyError.ErrorType.RuntimeError,
                            "There is a piece of data before an operation.");
                opt.peek().addArgs(in); //add a parameter to the top function.
            }
        }
        if (!opt.isEmpty() && opt.peek().isReady()){
            Operation FunctionOrOperation = opt.pop();
            try {
                FunctionOrOperation.exec(space);
            }
            catch (MyError e){
                e.addPrefixFunction(FunctionOrOperation.getName());
                throw e;
            }
            if (FunctionOrOperation.hasReturnValue)
                push(FunctionOrOperation.ReturnValue);
        }
    }
}

package src.mua.Operation;
import src.mua.MyError;
import src.mua.NameSpace;
import src.mua.Data;

public class print extends Operation {
    public print() {
        name = "print";
        hasReturnValue = false;
        arg_num = 1;
    }
    public void exec(NameSpace Space) throws MyError{
        System.out.println(args.get(0));
    }
}
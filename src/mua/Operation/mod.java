package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class mod extends Operation{
    public mod(){
        name = "mod";
        hasReturnValue = true;
        arg_num = 2;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0), v = args.get(1);
        /*if (u.type != Data.Type.NUMBER && v.type != Data.Type.NUMBER)
            throw new MyError(MyError.ErrorType.TypeError,
                    "One of two parameters in function [mod] should be NUMBER.");*/
        if (v.getNumber() == 0)
            throw new MyError(MyError.ErrorType.ValueError,
                    "Divided by zero!");
        ReturnValue = new Data(u.getNumber() % v.getNumber());
    }
}


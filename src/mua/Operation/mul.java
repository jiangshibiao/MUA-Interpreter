package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class mul extends Operation{
    public mul(){
        name = "mul";
        hasReturnValue = true;
        arg_num = 2;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0), v = args.get(1);
        /*if (u.type != Data.Type.NUMBER && v.type != Data.Type.NUMBER)
            throw new MyError(MyError.ErrorType.TypeError,
                    "One of two parameters in function [mul] should be NUMBER.");*/
        ReturnValue = new Data(u.getNumber() * v.getNumber());
    }
}

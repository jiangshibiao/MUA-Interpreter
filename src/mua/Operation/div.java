package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class div extends Operation{
    public div(){
        name = "div";
        hasReturnValue = true;
        arg_num = 2;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0), v = args.get(1);
        /*if (u.type != Data.Type.NUMBER && v.type != Data.Type.NUMBER)
            throw new MyError(MyError.ErrorType.TypeError,
                    "One of two parameters in function [div] should be NUMBER.");*/
        if (Math.abs(v.getNumber()) <= 1e-9)
            throw new MyError(MyError.ErrorType.ValueError,
                    "Divided by zero!");
        ReturnValue = new Data(u.getNumber() / v.getNumber());
    }
}

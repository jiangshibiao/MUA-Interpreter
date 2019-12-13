package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class sub extends Operation{
    public sub(){
        name = "sub";
        hasReturnValue = true;
        arg_num = 2;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0), v = args.get(1);
        /*if (u.type != Data.Type.NUMBER && v.type != Data.Type.NUMBER)
            throw new MyError(MyError.ErrorType.TypeError,
                    name + "One of two parameters in function [sub] should be NUMBER");*/
        ReturnValue = new Data(u.getNumber() - v.getNumber());
    }
}

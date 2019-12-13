package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class or extends Operation{
    public or(){
        name = "or";
        hasReturnValue = true;
        arg_num = 2;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0), v = args.get(1);
        if (u.type != Data.Type.BOOLEAN && v.type != Data.Type.BOOLEAN)
            throw new MyError(MyError.ErrorType.TypeError,
                    name + "One of two parameters in function [or] should be BOOLEAN");
        ReturnValue = new Data(u.getBoolean() || v.getBoolean());
    }
}

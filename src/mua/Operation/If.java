package src.mua.Operation;
import src.mua.Data;
import src.mua.Expression;
import src.mua.MyError;
import src.mua.NameSpace;

public class If extends Operation{
    public If(){
        name = "If";
        hasReturnValue = false;
        arg_num = 3;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0), v = args.get(1), w = args.get(2);
        if (u.type != Data.Type.BOOLEAN)
            throw new MyError(MyError.ErrorType.TypeError,
                    "The return value of condition in function [if] must be boolean.");
        Expression expr = new Expression(space);
        expr.runList(u.getBoolean() ? v : w);
    }

}

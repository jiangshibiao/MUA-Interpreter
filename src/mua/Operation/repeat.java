package src.mua.Operation;
import src.mua.*;

import java.util.HashMap;

public class repeat extends Operation{
    public repeat(){
        name = "repeat";
        hasReturnValue = false;
        arg_num = 2;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0), v = args.get(1);
        double repeatNumbers = u.getInt();
        for (int i = 0; i < repeatNumbers; i++){
            Expression expr = new Expression(space);
            expr.runList(v);
        }
    }
}

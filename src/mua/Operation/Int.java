package src.mua.Operation;
import src.mua.*;

import java.util.HashMap;

public class Int extends Operation{
    public Int(){
        name = "int";
        hasReturnValue = true;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0), v = args.get(1);
        int val = (int)Math.floor(u.getNumber());
        ReturnValue = new Data(val);
    }
}

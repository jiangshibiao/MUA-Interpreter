package src.mua.Operation;

import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

import java.util.Vector;

public class join extends Operation{
    public join(){
        name = "join";
        hasReturnValue = true;
        arg_num = 2;
    }
    public void exec(NameSpace space) throws MyError {
        Data u = args.get(0), v = args.get(1);
        if (u.type != Data.Type.LIST)
            throw new MyError(MyError.ErrorType.TypeError,
                    "The first parameter in [join] must be list.");
        Data ret = new Data(u);
        ret.listAdd(v);
        ReturnValue = ret;
    }
}

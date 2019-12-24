package src.mua.Operation;

import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

import java.util.Vector;

public class first extends Operation{
    public first(){
        name = "first";
        hasReturnValue = true;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError {
        Data u = args.get(0);
        if (u.type == Data.Type.LIST)
            ReturnValue = new Data(u.getList().get(0));
        else
            ReturnValue =  new Data(("" + u).substring(0, 1), false);
        //Although the parameter in [first] must be list or word, if it is not list, it can be converted into word.
    }
}

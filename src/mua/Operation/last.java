package src.mua.Operation;

import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class last extends Operation{
    public last(){
        name = "last";
        hasReturnValue = true;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError {
        Data u = args.get(0);
        if (u.type == Data.Type.LIST){
            Data cur = u.getList().lastElement();
            ReturnValue = new Data(cur.getList().lastElement());
        }
        else
            ReturnValue =  new Data(("" + u).substring(0, 1), false);
        //Although the parameter in [last] must be list or word, if it is not list, it can be converted into word.
    }
}

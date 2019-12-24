package src.mua.Operation;

import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

import java.util.Vector;

public class list extends Operation{
    public list(){
        name = "list";
        hasReturnValue = true;
        arg_num = 2;
    }
    public void exec(NameSpace space) throws MyError {
        Data u = args.get(0), v = args.get(1);
        Vector<Data> ret = new Vector<Data>();
        ret.add(new Data(u)); ret.add(new Data(v));
        ReturnValue = new Data(ret);
    }
}


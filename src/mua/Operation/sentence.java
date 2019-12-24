package src.mua.Operation;

import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

import java.util.Vector;

public class sentence extends Operation{
    public sentence(){
        name = "sentence";
        hasReturnValue = true;
        arg_num = 2;
    }
    public void exec(NameSpace space) throws MyError {
        Data u = new Data(args.get(0)), v = new Data(args.get(1));
        Vector<Data> ret = new Vector<Data>();
        if (u.type == Data.Type.LIST)
            ret.addAll(u.getList());
        else ret.add(u);
        if (v.type == Data.Type.LIST)
            ret.addAll(v.getList());
        else ret.add(v);
        ReturnValue = new Data(ret);
    }
}


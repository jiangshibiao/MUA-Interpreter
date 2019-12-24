package src.mua.Operation;

import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

import java.util.Vector;

public class butfirst extends Operation{
    public butfirst(){
        name = "butfirst";
        hasReturnValue = true;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError {
        Data u = args.get(0);
        if (u.type == Data.Type.LIST) {
            Vector<Data> ret = new Vector<Data>();
            for (int i = 1; i < u.getList().size(); i++)
                ret.add(new Data(u.getList().get(i)));
            ReturnValue = new Data(ret);
        }
        else if (u.type == Data.Type.WORD)
            ReturnValue =  new Data(u.getWord().substring(1), false);
        else
            throw new MyError(MyError.ErrorType.TypeError,
                    "The parameter in [butfirst] must be list or word.");
    }
}

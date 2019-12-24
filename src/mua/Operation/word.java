package src.mua.Operation;

import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class word extends Operation{
    public word(){
        name = "word";
        hasReturnValue = true;
        arg_num = 2;
    }
    public void exec(NameSpace space) throws MyError {
        Data u = args.get(0), v = args.get(1);
        if (u.type != Data.Type.WORD)
            throw new MyError(MyError.ErrorType.TypeError,
                    "The first parameter in [word] must be word.");
        if (v.type != Data.Type.WORD && v.type != Data.Type.NUMBER && v.type != Data.Type.BOOLEAN)
            throw new MyError(MyError.ErrorType.TypeError,
                    "The first parameter in [word] must be word, number or boolean.");
        ReturnValue = new Data("" + u + v, false);
    }
}

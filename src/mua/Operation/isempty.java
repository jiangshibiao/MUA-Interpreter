package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class isempty extends Operation{
    public isempty(){
        name = "isempty";
        hasReturnValue = true;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0);
        if (u.type == Data.Type.WORD)
            ReturnValue = new Data(u.getWord().isEmpty());
        else if (u.type == Data.Type.LIST)
            ReturnValue = new Data(u.getList().isEmpty());
        else throw new MyError(MyError.ErrorType.TypeError,
                    "The parameter for [isempty] must be word|list.");
    }

}

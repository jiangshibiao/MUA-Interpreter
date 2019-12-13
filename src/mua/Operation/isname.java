package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class isname extends Operation{
    public isname(){
        name = "isname";
        hasReturnValue = true;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0);
        if (u.type != Data.Type.WORD)
            throw new MyError(MyError.ErrorType.TypeError,
                    "The parameter in [isname] must be word.");
        ReturnValue = new Data(space.thisSpaceContainsName(u.getWord()));
    }

}

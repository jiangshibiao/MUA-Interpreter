package src.mua.Operation;
import src.mua.MyError;
import src.mua.NameSpace;
import src.mua.Data;

public class thing extends Operation{
    public thing(){
        name = "thing";
        hasReturnValue = true;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0);
        if (u.type != Data.Type.WORD)
            throw new MyError(MyError.ErrorType.TypeError,
                    "The first parameter in [thing] must be word.");
        if (space.wholeSpaceContainsName(u.getWord()))
            ReturnValue = space.wholeSpaceGetName(u.getWord());
        else
            throw new MyError(MyError.ErrorType.NameError,
                    "Can not find the corresponding value for the parameter in [thing].");
    }
}

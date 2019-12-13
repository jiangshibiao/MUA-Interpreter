package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class erase extends Operation{
    public erase(){
        name = "erase";
        hasReturnValue = false;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0);
        if (u.type != Data.Type.WORD)
            throw new MyError(MyError.ErrorType.TypeError,
                    "The parameter for [erase] should be a word.");
        String name = u.getWord();
        if (!space.wholeSpaceContainsName(name))
            throw new MyError(MyError.ErrorType.NameError,
                    "Name [" + name + "] which has not been defined can not erase.");space.thisSpaceRemoveName(u.getWord());
        space.wholeSpaceRemoveName(name);
    }
}

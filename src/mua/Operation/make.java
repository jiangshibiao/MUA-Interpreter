package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class make extends Operation{
    public make(){
        name = "make";
        hasReturnValue = false;
        arg_num = 2;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0), v = args.get(1);
        if (u.type != Data.Type.WORD)
            throw new MyError(MyError.ErrorType.TypeError,
                    "The first parameter in [make] must be word.");
        //System.out.println("Doing make: " + u.getName() + " " + v);
        space.thisSpacePutName(u.getName(), v);
    }
}

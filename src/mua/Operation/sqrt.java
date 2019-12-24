package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class sqrt extends Operation{
    public sqrt(){
        name = "sqrt";
        hasReturnValue = true;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0);
        if (u.type != Data.Type.NUMBER)
            throw new MyError(MyError.ErrorType.TypeError,
                    "The parameter in [random] must be list or word.");
        double val = u.getNumber();
        if (val < 0)
            throw new MyError(MyError.ErrorType.ValueError,
                    "The parameter in [sqrt] must bigger than 0.");
        ReturnValue = new Data(Math.sqrt(val));
    }
}

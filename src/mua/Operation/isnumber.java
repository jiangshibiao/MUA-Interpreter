package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class isnumber extends Operation{
    public isnumber(){
        name = "isnumber";
        hasReturnValue = true;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError{
        ReturnValue = new Data(args.get(0).type == Data.Type.NUMBER);
    }

}

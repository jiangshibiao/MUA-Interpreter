package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class isword extends Operation{
    public isword(){
        name = "isword";
        hasReturnValue = true;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError{
        ReturnValue = new Data(args.get(0).type == Data.Type.WORD);
    }

}

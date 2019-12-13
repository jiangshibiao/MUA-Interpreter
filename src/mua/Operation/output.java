package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class output extends Operation{
    public output(){
        name = "output";
        hasReturnValue = false;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError{
        space.FinalResult = args.get(0);
    }
}

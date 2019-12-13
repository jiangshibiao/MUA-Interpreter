package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class not extends Operation{
    public not(){
        name = "not";
        hasReturnValue = true;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError{
        ReturnValue = new Data(!args.get(0).getBoolean());
    }
}

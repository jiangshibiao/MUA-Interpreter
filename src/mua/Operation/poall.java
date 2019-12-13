package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class poall extends Operation{
    public poall(){
        name = "poall";
        hasReturnValue = false;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError{
        space.thisSpaceRemoveAllName();
    }
}

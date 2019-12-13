package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class erall extends Operation{
    public erall(){
        name = "erall";
        hasReturnValue = false;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError{
        space.thisSpaceListName();
    }
}

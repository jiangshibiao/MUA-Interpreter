package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class stop extends Operation{
    public stop(){
        name = "stop";
        hasReturnValue = false;
        arg_num = 0;
    }
    public void exec(NameSpace space) throws MyError{
        //System.out.println("stop");
        space.stopFlag = true;
    }
}

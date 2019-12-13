package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;
import java.util.Scanner;

public class read extends Operation{
    public read(){
        name = "read";
        hasReturnValue = true;
        arg_num = 0;
    }
    public void exec(NameSpace space) throws MyError{
        //String debug = space.readNext(); System.out.println(debug);
        ReturnValue = new Data(space.readNext(), false);
    }
}

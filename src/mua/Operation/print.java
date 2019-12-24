package src.mua.Operation;
import src.mua.MyError;
import src.mua.NameSpace;
import src.mua.Data;

public class print extends Operation {
    public print() {
        name = "print";
        hasReturnValue = false;
        arg_num = 1;
    }
    public void exec(NameSpace Space) throws MyError{
        Data u = args.get(0);
        String uString = "" + u;
        //System.out.println("!!" + uString);
        uString = uString.replace("[ ", "[");
        uString = uString.replace(" ]", "]");
        System.out.println((u.type != Data.Type.LIST) ? u : uString.substring(1, uString.length() - 1));
    }
}
package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

public class eq extends Operation{
    public eq(){
        name = "eq";
        hasReturnValue = true;
        arg_num = 2;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0), v = args.get(1);
        if (u.type == Data.Type.NUMBER || v.type == Data.Type.NUMBER) {
            ReturnValue = new Data(u.getNumber() == v.getNumber());
        }
        else if (u.type == Data.Type.BOOLEAN || v.type == Data.Type.BOOLEAN) {
            ReturnValue = new Data(u.getBoolean() == v.getBoolean());
        }
        else if (u.type == Data.Type.WORD && v.type == Data.Type.WORD){
            ReturnValue = new Data(u.getWord().equals(v.getWord()));
        }
        else if (u.type == Data.Type.LIST && v.type == Data.Type.LIST){
            ReturnValue = new Data(u.getList().equals(v.getList()));
        }
        else
            throw new MyError(MyError.ErrorType.TypeError,
                    "Two parameters in function [eq] are not comparable.");
    }
}

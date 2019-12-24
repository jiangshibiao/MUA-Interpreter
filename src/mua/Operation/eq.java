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

        try {
            if (u.type == Data.Type.NUMBER || v.type == Data.Type.NUMBER) {
                ReturnValue = new Data(Math.abs(u.getNumber() - v.getNumber()) <= 1e-9);
            } else if (u.type == Data.Type.BOOLEAN || v.type == Data.Type.BOOLEAN) {
                ReturnValue = new Data(u.getBoolean() == v.getBoolean());
            } else if (u.type == Data.Type.WORD && v.type == Data.Type.WORD) {
                ReturnValue = new Data(u.getWord().equals(v.getWord()));
            } else if (u.type == Data.Type.LIST && v.type == Data.Type.LIST) {
                ReturnValue = new Data(u.getList().equals(v.getList()));
            } else ReturnValue = new Data(false);
        }
        catch (Exception e){
            //If their types are different, it means they are "not eq"
            ReturnValue = new Data(false);
        }
    }
}

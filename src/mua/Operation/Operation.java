package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;
import java.util.ArrayList;

public abstract class Operation{
    protected int arg_num;
    protected String name;
    public ArrayList<Data> args = new ArrayList<>();
    public Data ReturnValue = null;
    public boolean hasReturnValue;
    public Operation(){}
    public void clear(){
        ArrayList<Data>args = new ArrayList<>();
        ReturnValue = null;
    }
    public String getName(){return name;}
    public boolean isReady(){
        return args.size() == arg_num;
    }
    public void addArgs(Data curArg){
        args.add(curArg);
    }
    public abstract void exec(NameSpace space) throws MyError;
}

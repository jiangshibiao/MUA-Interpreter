package src.mua;
import src.mua.Operation.*;
import src.mua.MyError;
import java.util.HashMap;

public class OperationRun {
    private static final HashMap<String, Operation> map = new HashMap<String, Operation>() {{
        put("add", new src.mua.Operation.add());
        put("and", new and());
        //put("butfirst", 1);
        //put("butlast", 1);
        put("div", new div());
        put("eq", new eq());
        put("erall", new erall());
        put("erase", new erase());
        put("export", new export());
        //put("first", 1);
        put("gt", new gt());
        put("if", new If());
        //put("int", 1);
        put("isbool", new isbool());
        put("isempty", new isempty());
        put("islist", new islist());
        put("isname", new isname());
        put("isnumber", new isnumber());
        put("isword", new isword());
        //put("join", 2);
        //put("last", 1);
        //put("list", 2);
        //put("load", 1);
        put("lt", new lt());
        put("make", new make());
        put("mod", new mod());
        put("mul", new mul());
        put("not", new not());
        put("or", new or());
        put("output", new output());
        put("print", new print());
        put("poall", new poall());
        put("read", new read());
        put("readlist", new readlist());
        put("repeat", new repeat());
        //put("random", 1);
        //put("save", 1);
        //put("sentence", 2);
        //put("sqrt", 1);
        put("stop", new stop());
        put("sub", new sub());
        put("thing", new thing());
        //put("wait", 1);
        //put("word", 2);
    }};
    public boolean isOperationName(String operationName){
        return map.containsKey(operationName);
    }
    public Operation newOperation(String operationName) throws MyError{
        try {
            //System.out.println(operationName);
            Operation thisOperation = (Operation) map.get(operationName).getClass().newInstance();
            thisOperation.clear();
            return thisOperation;
        }
        catch (Exception e){
            throw new MyError(MyError.ErrorType.UnexpectedError,
                    "Can not build a new instance for class [" + operationName + "].");
        }
    }
}

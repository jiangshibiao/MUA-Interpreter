package src.mua.Operation;
import src.mua.*;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class save extends Operation{
    public save(){
        name = "save";
        hasReturnValue = false;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0);
        String uString = u.getWord();
        ObjectOutputStream oos;
        try{
            PrintStream Stdout = System.out;
            System.setOut(new PrintStream(uString));
            HashMap<String, Data> map = space.thisSpaceAllName();
            for (String key: map.keySet())
                System.out.println("make \"" + key + " " + map.get(key));
            System.setOut(Stdout);
        }
        catch(Exception e){
            e.printStackTrace();
            throw new MyError(MyError.ErrorType.RuntimeError,
                    "Error in [save]: ");
        }
    }
}

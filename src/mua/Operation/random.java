package src.mua.Operation;

import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;
import java.util.Random;

public class random extends Operation{
    public random(){
        name = "random";
        hasReturnValue = true;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError {
        Data u = args.get(0);
        int limit = (int)Math.ceil(u.getNumber());
        if (limit <= 0)
            throw new MyError(MyError.ErrorType.ValueError,
                    "The parameter in [random] must bigger than 0.");
        Random r = new Random();
        int number = r.nextInt(limit);
        ReturnValue =  new Data(number);
    }
}

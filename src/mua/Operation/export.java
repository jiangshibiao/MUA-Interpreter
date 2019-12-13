package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;

import java.util.HashMap;

public class export extends Operation{
    public export(){
        name = "export";
        hasReturnValue = false;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0);
        if (u.type != Data.Type.WORD)
            throw new MyError(MyError.ErrorType.TypeError,
                    "The parameter for [export] must be word.");
        /*HashMap<String, Data>allName = space.thisSpaceAllName();
        for (String name: allName.keySet()){
            //System.out.println(name + " " + allName.get(name));
            space.mainSpacePutName(name, allName.get(name));
        }*/
        String words = u.getWord();
        if (space.thisSpaceContainsName(words))
            space.mainSpacePutName(words, space.thisSpaceGetName(words));
        else throw new MyError(MyError.ErrorType.NameError,
                "The export name [" + words + "] doesn't exist.");
    }
}

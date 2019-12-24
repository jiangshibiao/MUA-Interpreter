package src.mua.Operation;
import src.mua.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;

public class load extends Operation{
    public load(){
        name = "load";
        hasReturnValue = false;
        arg_num = 1;
    }
    public void exec(NameSpace space) throws MyError{
        Data u = args.get(0);
        String uString = u.getWord();
        try{
            Scanner scan = new Scanner(new File(uString));
            StringBuffer content = new StringBuffer("[ ");
            while (scan.hasNextLine()){
                String curLine = scan.nextLine();
                if (curLine.equals("Break")) break;
                int findNote = curLine.indexOf("//");
                if (findNote != -1)
                    curLine = curLine.substring(0, findNote);
                content.append(curLine + " ");
            }
            content.append("]");
            Expression expr = new Expression(space);
            expr.runList(expr.generateList(content.toString()));
        }
        catch (Exception e){
            throw new MyError(MyError.ErrorType.RuntimeError,
                    "Error in [load].   " + e);
        }
    }
}

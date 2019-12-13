package src.mua.Operation;
import src.mua.Data;
import src.mua.MyError;
import src.mua.NameSpace;
import java.util.Scanner;
import java.util.Vector;

public class readlist extends Operation{
    public readlist(){
        name = "readlist";
        hasReturnValue = true;
        arg_num = 0;
    }
    public void exec(NameSpace space) throws MyError{
        String data[] = space.readNextLine().split("\\s+");
        Vector<Data>resultList = new Vector<>();
        for (int i = 0; i < data.length; i++)
            resultList.add(new Data(data[i], false));
        ReturnValue = new Data(resultList);
    }
}
